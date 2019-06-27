package com.test.mongodbtest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractMongoDao<T, ID extends Serializable> implements IBaseMongoDao<T, ID> {

    @Autowired
    protected MongoTemplate mongoTemplate;

    protected Class<T> entityClass;

    public AbstractMongoDao(){
        entityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T insert(T entity) {
        mongoTemplate.insert(entity);
        return entity;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(Serializable... ids) {

    }

    @Override
    public void delete(Query query) {

    }

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(entityClass);
    }

    @Override
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, entityClass);
    }

    @Override
    public T findById(ID id) {
        return mongoTemplate.findById(id, entityClass);
    }

    @Override
    public long count(Query query) {
        return mongoTemplate.count(query, entityClass);
    }

    public MongoTemplate getMongoTemplate(){
        return mongoTemplate;
    }
}
