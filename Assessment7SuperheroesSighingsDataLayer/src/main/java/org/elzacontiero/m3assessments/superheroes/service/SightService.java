package org.elzacontiero.m3assessments.superheroes.service;

import java.util.List;

import org.elzacontiero.m3assessments.superheroes.dao.RecordingDao;
import org.elzacontiero.m3assessments.superheroes.dto.Recording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SightService implements ServiceInterface<Recording> {

    @Autowired
    RecordingDao dao;

    @Override
    public List<Recording> listAll() {
        return dao.getAll();
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public void update(Recording rec) {
        dao.update(rec);
    }

    @Override
    public void insert(Recording rec) {
        dao.insert(rec);
    }

    @Override
    public Recording getById(long id) {
        return dao.getById(id);
    }

}
