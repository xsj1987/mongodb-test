package com.test.mongodbtest.service.impl;

import com.test.mongodbtest.dao.UserDao;
import com.test.mongodbtest.entity.Users;
import com.test.mongodbtest.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSerivceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void insert(Users users) {
        Users us = userDao.insert(users);
    }

    @Override
    public Users find(int id) {
        Users users = userDao.findById(id);
        return users;
    }

    @Override
    public Users findUserInfo(Users users) {
        return userDao.findUserInfo(users);
    }

    @Override
    public long updateUserInfo(Users users) {
        return userDao.updateUserInfo(users);
    }

    @Override
    public long deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    public List<JSONObject> findUsersByCondition(Users users) {
        return userDao.findUsersByCondition(users);
    }
}
