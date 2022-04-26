package org.elzacontiero.m3assessments.superheroes.service;

import java.util.List;

import org.elzacontiero.m3assessments.superheroes.dao.OrganizationDao;
import org.elzacontiero.m3assessments.superheroes.dto.Organization;
import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationService implements ServiceInterface<Organization> {

    @Autowired
    OrganizationDao dao;

    @Override
    public List<Organization> listAll() {
        List<Organization> orgs = dao.getAll();
        return orgs;
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public void update(Organization org) {
        dao.update(org);
    }

    @Override
    public void insert(Organization org) {
        Long id = dao.insert(org);
    }

    @Override
    public Organization getById(long id) {
        return dao.getById(id);
    }

    /**
     * Get all organizations that this character belongs to.
     * @return
     */
    public List<Organization> getOrganizationsForCharacter(SuperCharacter character) {
        return dao.getOrganizations(character);
    }

    public List<Organization> getAll() {
        return dao.getAll();
    }

}
