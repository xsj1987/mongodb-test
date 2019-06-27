package com.test.mongodbtest;

import com.test.mongodbtest.entity.Users;
import com.test.mongodbtest.enums.SexEnum;
import com.test.mongodbtest.service.UserService;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbTestApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
    }

    /**
     * 生成30条用户数据
     */
    @Test
    public void insertTest(){
        for (int i=0;i<10;i++){
            Users users = new Users();
            users.setId(i+1);
            users.setUserName("用户"+i);
            users.setPassword(UUID.randomUUID().toString());
            users.setCreateDate(new Date());
            int index = new Random().nextInt(SexEnum.values().length);
            Integer sex = SexEnum.values()[index].getIndex();
            users.setSex(sex);
            users.setAge(new Random().nextInt(50) + 18);
            userService.insert(users);
        }
    }

    @Test
    public void selectTest(){
        Users users = new Users();
        users.setId(25);
        users = userService.findUserInfo(users);
        System.out.println(users);
    }

    @Test
    public void updateTest(){
        Users users = new Users();
        users.setId(25);
        users.setPassword("123456");
        long result = userService.updateUserInfo(users);
        System.out.println(result);
    }

    @Test
    public void deleteTest(){
        Users users = new Users();
        users.setId(15);
        users = userService.findUserInfo(users);
        System.out.println("删除之前："+users);

        long result = userService.deleteUser(15);
        System.out.println(result);

        users = userService.findUserInfo(users);
        System.out.println("删除之后:"+users);
    }

    /**
     * 测试聚合函数，进行group by分组
     */
    @Test
    public void findByCondition(){
        Users users = new Users();
        List<JSONObject> list = userService.findUsersByCondition(users);
        for (JSONObject us : list){
            System.out.println(us);
        }
    }

}
