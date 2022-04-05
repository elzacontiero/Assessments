package org.elzacontiero.m3assessments.guessthenumber.controller;

/*
You will need several REST endpoints for this:

"begin" - POST – Starts a game, generates an answer, and sets the correct status. Should return a 201 CREATED message as well as the created gameId.
"guess" – POST – Makes a guess by passing the guess and gameId in as JSON. The program must calculate the results of the guess and mark the game finished if the guess is correct. It returns the Round object with the results filled in.
"game" – GET – Returns a list of all games. Be sure in-progress games do not display their answer.
"game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
"rounds/{gameId} – GET – Returns a list of rounds for the specified game sorted by time.
 */


import org.elzacontiero.m3assessments.guessthenumber.GameStatus;
import org.elzacontiero.m3assessments.guessthenumber.dto.Game;
import org.elzacontiero.m3assessments.guessthenumber.dto.Round;
import org.elzacontiero.m3assessments.guessthenumber.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/begin")
    public ResponseEntity<Game> begin(@RequestBody Game game) {
        // TODO: Call service
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PostMapping("/guess")
    public ResponseEntity<Round> guess(@RequestBody Round guess) {
        // TODO: Call service
        guess.setId(666);
        guess.setGame_id(123);
        guess.setTstamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<Round>(guess, HttpStatus.OK);
    }

    @GetMapping("/game")
    public ResponseEntity<List<Game>> game() {
        // TODO: call service
        List<Game> games = new ArrayList<>();
        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }

    // "game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
    @GetMapping("/game/{id}")
    public ResponseEntity<Game> game(@PathVariable Integer id) {
        Game game = new Game(id, "dude", GameStatus.IN_PROGRESS);
        return new ResponseEntity<Game>(game, HttpStatus.FOUND);
    }

}
