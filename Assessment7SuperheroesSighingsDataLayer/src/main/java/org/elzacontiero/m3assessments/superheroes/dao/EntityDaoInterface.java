package org.elzacontiero.m3assessments.superheroes.dao;

import java.util.List;

public interface EntityDaoInterface<T> {
    /**
     * Retrieve this resource by its id.
     */
    T getById(long id);

    /** Update all fields from this object */
    // void update(T x);

    /**
     * Delete this resource given its ID;
     * @param id
     */
    void delete(long id);

    /**
     * Insert this entity into database. ID, if populated, will be ignored.
     * @param x
     * @return the ID of this record.
     */
    long insert(T x);

    /**
     * Retrieve all entities.
     * @return
     */
    List<T> getAll();

}
