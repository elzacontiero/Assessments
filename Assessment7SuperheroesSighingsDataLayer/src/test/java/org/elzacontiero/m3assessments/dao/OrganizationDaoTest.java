package org.elzacontiero.m3assessments.dao;

import org.elzacontiero.m3assessments.superheroes.dao.OrganizationDao;
import org.elzacontiero.m3assessments.superheroes.dto.Organization;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class OrganizationDaoTest {

    OrganizationDao organizationDao;

    public OrganizationDaoTest() throws Exception {
        organizationDao = new OrganizationDao(CharacterDaoTest.createDataSource());
    }

    @Test
    public void testInsertGetDelete() throws Exception {
        Organization org1= new Organization(0, "The Masters of Evil", "abcdefg", "666 Evil Street, EvilTown.");
        long id = organizationDao.insert(org1);
        assertTrue(id>0);
        Organization org2 = organizationDao.getById(id);
        assertEquals(org1.getId(), org2.getId());
        assertEquals(org1.getAddress(), org2.getAddress());
        assertEquals(org1.getName(), org2.getName());
        assertEquals(org1.getDescription(), org2.getDescription());

        organizationDao.delete(id);
        Organization org3 = organizationDao.getById(id);
        assertEquals(org3, null);
    }
}
