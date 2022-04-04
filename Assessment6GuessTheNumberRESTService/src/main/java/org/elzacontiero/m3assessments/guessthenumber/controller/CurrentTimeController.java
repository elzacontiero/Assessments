package org.elzacontiero.m3assessments.guessthenumber.controller;

import org.elzacontiero.m3assessments.guessthenumber.dao.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/time")
public class CurrentTimeController {

    @Autowired
    GameDao gameDao;

    @GetMapping("/now")
    public ResponseEntity<String> now() {
        return new ResponseEntity<String>(gameDao.dbNow().toString(), HttpStatus.OK);
    }

}
