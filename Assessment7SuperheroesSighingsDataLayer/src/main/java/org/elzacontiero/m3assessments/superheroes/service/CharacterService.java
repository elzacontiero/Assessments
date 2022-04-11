package org.elzacontiero.m3assessments.superheroes.service;

import java.util.List;

import org.elzacontiero.m3assessments.superheroes.dao.CharacterDao;
import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterService {

    @Autowired
    CharacterDao charDao;

    public List<SuperCharacter> listAll() {
        List<SuperCharacter> chars = charDao.getAll();
        return chars;
    }
}
