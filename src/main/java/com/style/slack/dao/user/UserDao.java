package com.style.slack.dao.user;

import com.style.slack.model.po.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * 用户dao层处理类
 *  Created by Gaofei on 2018/6/11.
 */


public interface UserDao  {


    /**
     * 查询所有用户信息
     * @return
     */

    @Select("SELECT id,name,password FROM users")
    @Results(value ={
            @Result(id=true, property="id",column="id"),
            @Result(property="userName",column="name"),
            @Result(property="password",column="password")
    })
    public List<User> selectUsers();


    /**
     * 添加用户信息
     * @param userInfo
     * @return
     */
    @Insert("insert into users(id,name,password) " +
            "values(#{id},#{userName},#{password})" )
    @SelectKey(before=false,keyProperty="id",resultType=String.class,statementType= StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
    int insert(User userInfo);



}
