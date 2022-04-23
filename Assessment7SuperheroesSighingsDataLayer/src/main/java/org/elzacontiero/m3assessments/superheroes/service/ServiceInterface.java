package org.elzacontiero.m3assessments.superheroes.service;

import java.util.List;

public interface ServiceInterface<T> {

    List<T> listAll();
    void delete(long id);
    void update(T x);
    void insert(T x);
    T getById(long id);

}
