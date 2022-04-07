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

    public Round guess(Round guess) {
        guess.setTstamp(new Timestamp(System.currentTimeMillis()));
        Game game = dao.get(guess.getGame_id());
        if (game == null) {
            return null;
        }

        if (game.getStatus().equals("FINISHED")) {
            return null;
        }

        if (game.getAnswer().equals(guess.getAttempt())) {
            game.setStatus("FINISHED");
            guess.setResult("EEEE");
            dao.update(game);
            dao.insert(guess);
            return guess;
        }
        else {
            char[] result = GameUtils.checkingForEAndP(
                                game.getAnswer().toCharArray(),
                                guess.getAttempt().toCharArray());
            guess.setResult(String.copyValueOf(result));
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
