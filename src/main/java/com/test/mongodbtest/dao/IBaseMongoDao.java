package com.test.mongodbtest.dao;

import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.util.List;

public interface IBaseMongoDao<T, ID extends Serializable> {
    T insert(T entity);

    void update(T entity);

    void delete(Serializable... ids);

    void delete(Query query);

    List<T> findAll();

    T findOne(Query query);

    T findById(ID id);

    long count(Query query);

}
