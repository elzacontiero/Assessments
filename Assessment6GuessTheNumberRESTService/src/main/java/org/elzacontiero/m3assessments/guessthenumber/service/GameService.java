package org.elzacontiero.m3assessments.guessthenumber.service;

import org.elzacontiero.m3assessments.guessthenumber.GameStatus;
import org.elzacontiero.m3assessments.guessthenumber.GameUtils;
import org.elzacontiero.m3assessments.guessthenumber.dao.GameDao;
import org.elzacontiero.m3assessments.guessthenumber.dto.Game;
import org.elzacontiero.m3assessments.guessthenumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class GameService {

    @Autowired
    GameDao gameDao;

    public Game create() {
        Game game = new Game();
        game.setAnswer(GameUtils.generateRandomNumbers());
        game.setStatus("INPROGRESS");
        gameDao.createNew(game);
        game.setAnswer(null);
        return game;
    }

    public Round guess(Round guess) {
        guess.setTstamp(new Timestamp(System.currentTimeMillis()));
        Game game = gameDao.get(guess.getGame_id());
        if (game == null) {
            return null;
        }

        char[] result = GameUtils.checkingForEAndP(
                            game.getAnswer().toCharArray(),
                            guess.getAttempt().toCharArray());
        guess.setResult(String.copyValueOf(result));
        gameDao.save(guess);
        return guess;
    }


}
