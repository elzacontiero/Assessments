package org.elzacontiero.m3assessments.superheroes.service;

import java.util.List;

import org.elzacontiero.m3assessments.superheroes.dao.CharacterDao;
import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterService implements ServiceInterface<SuperCharacter> {

    @Autowired
    CharacterDao charDao;

    @Override
    public List<SuperCharacter> listAll() {
        List<SuperCharacter> chars = charDao.getAll();
        return chars;
    }

    @Override
    public void delete(long id) {
        charDao.delete(id);
    }

    @Override
    public void update(SuperCharacter updateChar) {
        charDao.update(updateChar);
    }

    @Override
    public void insert(SuperCharacter character) {
        Long id = charDao.insert(character);
        System.out.println("CharacterService.insert: "+character+" got id="+id);
    }

    @Override
    public SuperCharacter getById(long id) {
        return charDao.getById(id);
    }
}
