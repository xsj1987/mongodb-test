package com.test.mongodbtest.service;

import com.test.mongodbtest.entity.Users;
import org.json.simple.JSONObject;

import java.util.List;

public interface UserService {

    void insert(Users users);

    Users find(int id);

    Users findUserInfo(Users users);

    long updateUserInfo(Users users);

    long deleteUser(Integer id);

    List<JSONObject> findUsersByCondition(Users users);
}
