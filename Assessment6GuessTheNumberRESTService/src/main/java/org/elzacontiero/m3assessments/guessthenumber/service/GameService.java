package org.elzacontiero.m3assessments.guessthenumber.service;

import org.elzacontiero.m3assessments.guessthenumber.GameUtils;
import org.elzacontiero.m3assessments.guessthenumber.dao.GameDao;
import org.elzacontiero.m3assessments.guessthenumber.dto.Game;
import org.elzacontiero.m3assessments.guessthenumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class GameService {

    @Autowired
    GameDao dao;

    public Game create() {
        Game game = new Game();
        game.setAnswer(GameUtils.generateRandomNumbers());
        game.setStatus("INPROGRESS");
        dao.createNew(game);
        game.setAnswer(null);
        return game;
    }

    /**
     * Calculates the round of a guess for a game.
     * @param guess The guess from player.
     * @return A Round instance with the results.
     */
    public Round guess(Round guess) {
        // Try find the game.
        Game game = dao.get(guess.getGame_id());
        if (game == null) {
            // Didn't find game id.
            return null;
        }

        // Do not answer for finished games.
        if (game.getStatus().equals("FINISHED")) {
            return null;
        }

        // If attempt is equals to game answer, update DB with FINISHED status.
        if (game.getAnswer().equals(guess.getAttempt())) {
            game.setStatus("FINISHED");
            guess.setResult("EEEE");
            dao.update(game);
            dao.insert(guess);
            return guess;
        }
        else {
            // Here go ahead and calculate result for player for her guess.
            char[] result = GameUtils.checkingForEAndP(
                                // need to convert answer from String to char[]
                                game.getAnswer().toCharArray(),
                                guess.getAttempt().toCharArray());

            // Need to store result, but before need to convert from char[] back to String.
            guess.setResult(String.copyValueOf(result));
            // Save attempt to DB
            dao.insert(guess);
            return guess;
        }
    }

    public Game get(long id) {
        Game game = dao.get(id);
        game.setAnswer(null);
        return game;
    }

    public List<Game> getAll() {
        List<Game> games = dao.getAll();
        for (Game game : games) {
            game.setAnswer(null);
        }
        return games;
    }


}
