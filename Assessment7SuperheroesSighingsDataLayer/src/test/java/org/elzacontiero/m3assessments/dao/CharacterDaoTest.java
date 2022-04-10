package org.elzacontiero.m3assessments.dao;

import org.elzacontiero.m3assessments.superheroes.dao.CharacterDao;
import org.elzacontiero.m3assessments.superheroes.dto.Character;

import java.io.IOException;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharacterDaoTest {

    CharacterDao characterDao;

    public CharacterDaoTest() throws IOException {
        characterDao = new CharacterDao(createDataSource());
    }

    public static DataSource createDataSource() throws IOException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setDatabaseName("superheroes");
        ds.setServerName("192.168.0.23");
        ds.setUser("elza");
        ds.setPassword("some very secret password here");
        return ds;
    }


    @Test
    public void testInsertAndGetById() throws Exception {
        Character c1 = new Character(0, "Spider-man", "The guy that was bitten by a radioactive spider. ", "crawl walls", "H");
        long id = characterDao.insert(c1);
        assertTrue(id>0);

        Character c2 = characterDao.getById(id);
        assertEquals(c1.getName(), c2.getName());
        assertEquals(c1.getDescription(), c2.getDescription());
        assertEquals(c1.getSuperpower(), c2.getSuperpower());
        assertEquals(c1.getCharacterType(), c2.getCharacterType());
    }


    @Test
    public void testInsertGetDelete() throws Exception {
        Character c1 = new Character(0, "Superman", "The alien that looks like human.", "super strong, flies, laser vision", "H");
        long id = characterDao.insert(c1);
        assertTrue(id>0);
        Character c2 = characterDao.getById(id);
        assertEquals(c1.getName(), c2.getName());
        assertEquals(c1.getDescription(), c2.getDescription());
        assertEquals(c1.getSuperpower(), c2.getSuperpower());
        assertEquals(c1.getCharacterType(), c2.getCharacterType());

        characterDao.delete(id);
        Character c3 = characterDao.getById(id);
        assertEquals(null, c3);
    }

}

