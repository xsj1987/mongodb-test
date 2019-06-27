package com.test.mongodbtest.dao;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.test.mongodbtest.entity.Users;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends AbstractMongoDao<Users, Integer> {

    /**
     * 根据用户信息查找用户
     * Query 查询对象
     * Criteria 查询条件 is-等于 ne-不等于
     * @param users
     * @return
     */
    public Users findUserInfo(Users users){
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("id").is(users.getId());
        query.addCriteria(criteria);
        return getMongoTemplate().findOne(query, Users.class);
    }

    /**
     * 更新用户信息
     * SQL - update Users set password=#{password} where id=#{id}
     * Query 查询对象，相当于where语句
     * Update 更新对象，相当于set语句
     *
     * **********
     * updateMulti-更新多行
     * updateFirst-更新匹配的第一行
     * **********
     * @param users
     * @return 修改的数据量，更新返回一个UpdateResult对象
     */
    public long updateUserInfo(Users users){
        Query query = new Query();
        Criteria criteria = new Criteria();
        // 添加查询的条件
        criteria.and("id").is(users.getId());
        query.addCriteria(criteria);

        Update upt = new Update();
        // set表示要更新的字段
        upt.set("password", users.getPassword());

        UpdateResult result = getMongoTemplate().updateMulti(query, upt, Users.class);
        return result.getModifiedCount();
    }

    /**
     * 根据ID删除数据
     *      1 获取查询条件
     *      2 根据条件删除数据
     * @param id
     * @return 删除行数
     */
    public long deleteUser(Integer id){
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("id").is(id);
        query.addCriteria(criteria);

        DeleteResult result = getMongoTemplate().remove(query, Users.class);
        return result.getDeletedCount();
    }

    /**
     * 聚合查询
     * @param users
     * @return
     */
    public List<JSONObject> findUsersByCondition(Users users){
        Aggregation agg = Aggregation.newAggregation(
                // 第一步：挑选所需的字段，类似select *，*所代表的字段内容
                Aggregation.project("age", "sex", "count"),
                // 第二步：sql where 语句筛选符合条件的记录
                //Aggregation.match(Criteria.where("id").is(users.getId())),
                // 第三步：分组条件，设置分组字段
                Aggregation.group("sex").count().as("count"),
                // 第四部：排序（根据某字段排序 倒序）
                Aggregation.sort(Sort.Direction.DESC,"count"),
                // 第五步：数量(分页)
                //Aggregation.limit(Integer.parseInt(map.get("pagesCount"))),
                // 第刘步：重新挑选字段
                Aggregation.project("count", "sex")

        );
        AggregationResults<JSONObject> result = mongoTemplate.aggregate(agg, "user", JSONObject.class);
        return result.getMappedResults();
    }
}
