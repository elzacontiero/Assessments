package org.elzacontiero.m3assessments.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.Instant;

import javax.sql.DataSource;

import org.elzacontiero.m3assessments.superheroes.dao.CharacterDao;
import org.elzacontiero.m3assessments.superheroes.dao.RecordingDao;
import org.elzacontiero.m3assessments.superheroes.dto.Recording;
import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;
import org.junit.jupiter.api.Test;

public class RecordingTest {

    RecordingDao recDao;
    CharacterDao charDao;

    public RecordingTest() throws Exception {
        DataSource ds = CharacterDaoTest.createDataSource();
        recDao = new RecordingDao(ds);
        charDao = new CharacterDao(ds);
    }

    @Test
    public void testInsertGet() throws Exception {
        SuperCharacter c1 = new SuperCharacter(0, "Superman", "The alien that looks like human.", "super strong, flies, laser vision", "H");
        long characterId = charDao.insert(c1);
        Recording rec = new Recording(0, characterId, "address", 0.0, 0.0, Timestamp.from(Instant.now()));
        recDao.insert(rec);
        assertTrue(rec.getId()>0);
        recDao.delete(rec.getId());
        charDao.delete(c1.getId());
    }

}
