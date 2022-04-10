package org.elzacontiero.m3assessments.dao;

import org.elzacontiero.m3assessments.superheroes.dao.CharacterDao;
import org.elzacontiero.m3assessments.superheroes.dao.OrganizationDao;
import org.elzacontiero.m3assessments.superheroes.dto.Organization;
import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharacterDaoTest {

    CharacterDao characterDao;
    OrganizationDao orgDao;

    public CharacterDaoTest() throws IOException {
        DataSource ds = createDataSource();
        characterDao = new CharacterDao(ds);
        orgDao = new OrganizationDao(ds);
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
        SuperCharacter c1 = new SuperCharacter(0, "Spider-man", "The guy that was bitten by a radioactive spider. ", "crawl walls", "H");
        long id = characterDao.insert(c1);
        assertTrue(id>0);

        SuperCharacter c2 = characterDao.getById(id);
        assertEquals(c1.getName(), c2.getName());
        assertEquals(c1.getDescription(), c2.getDescription());
        assertEquals(c1.getSuperpower(), c2.getSuperpower());
        assertEquals(c1.getCharacterType(), c2.getCharacterType());
    }


    @Test
    public void testInsertGetDelete() throws Exception {
        SuperCharacter c1 = new SuperCharacter(0, "Superman", "The alien that looks like human.", "super strong, flies, laser vision", "H");
        long id = characterDao.insert(c1);
        assertTrue(id>0);
        SuperCharacter c2 = characterDao.getById(id);
        assertEquals(c1.getName(), c2.getName());
        assertEquals(c1.getDescription(), c2.getDescription());
        assertEquals(c1.getSuperpower(), c2.getSuperpower());
        assertEquals(c1.getCharacterType(), c2.getCharacterType());

        characterDao.delete(id);
        SuperCharacter c3 = characterDao.getById(id);
        assertEquals(null, c3);
    }

    @Test
    public void testOrgsAndChars() throws Exception {
        SuperCharacter c1 = new SuperCharacter(0, "Superman", "The alien that looks like human.", "super strong, flies, laser vision", "H");
        Organization org = new Organization(0, "Justice League",  "XYZ", "Somewhere");
        long id = characterDao.insert(c1);
        orgDao.insert(org);
        id = characterDao.insertRelationshipCharacterToOrganization(c1, org);
        assertTrue(id>0);
        List<SuperCharacter> chars = characterDao.getCharactersFromOrganization(org);
        assertEquals(1, chars.size());
        characterDao.delete(c1.getId());
        SuperCharacter c2 = characterDao.getById(c1.getId());
        assertEquals(null, c2);
        chars = characterDao.getCharactersFromOrganization(org);
        // No more characters, because it has been deleted.
        assertEquals(0, chars.size());

        orgDao.delete(org.getId());
        org = orgDao.getById(org.getId());
        assertEquals(null, org);
    }

}
