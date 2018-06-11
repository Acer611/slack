package com.style.slack.dao;

import com.style.slack.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户dao层处理类
 *  Created by Gaofei on 2018/6/11.
 */

@Mapper
public interface UserDao {

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
    public List<User> list();
}
