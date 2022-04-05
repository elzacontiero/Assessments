package org.elzacontiero.m3assessments.guessthenumber.service;

import org.elzacontiero.m3assessments.guessthenumber.GameStatus;
import org.elzacontiero.m3assessments.guessthenumber.GameUtils;
import org.elzacontiero.m3assessments.guessthenumber.dao.GameDao;
import org.elzacontiero.m3assessments.guessthenumber.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameService {

    @Autowired
    GameDao gameDao;

    public Game create(Game game) {
        game.setAnswer(GameUtils.generateRandomNumbers());
        game.setStatus(GameStatus.IN_PROGRESS);
        gameDao.createNew(game);
        return game;
    }
}
