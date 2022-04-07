package org.elzacontiero.m3assessments.guessthenumber.controller;

/*
You will need several REST endpoints for this:

"begin" - POST – Starts a game, generates an answer, and sets the correct status. Should return a 201 CREATED message as well as the created gameId.
"guess" – POST – Makes a guess by passing the guess and gameId in as JSON. The program must calculate the results of the guess and mark the game finished if the guess is correct. It returns the Round object with the results filled in.
"game" – GET – Returns a list of all games. Be sure in-progress games do not display their answer.
"game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
"rounds/{gameId} – GET – Returns a list of rounds for the specified game sorted by time.
 */


import org.elzacontiero.m3assessments.guessthenumber.dto.Game;
import org.elzacontiero.m3assessments.guessthenumber.dto.Round;
import org.elzacontiero.m3assessments.guessthenumber.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/bulls")
public class GameController {

    @Autowired
    GameService service;

    @PostMapping("/begin")
    public ResponseEntity<Game> begin() {
        Game game = service.create();
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PostMapping("/guess")
    public ResponseEntity<Round> guess(@RequestBody Round guess) {
        Round reply = service.guess(guess);
        return new ResponseEntity<Round>(reply, HttpStatus.OK);
    }

    @GetMapping("/game")
    public ResponseEntity<List<Game>> game() {
        List<Game> games = service.getAll();
        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }

    // "game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
    @GetMapping("/game/{id}")
    public ResponseEntity<Game> game(@PathVariable Integer id) {
        Game game = service.get(id);
        return new ResponseEntity<Game>(game, HttpStatus.FOUND);
    }

}
