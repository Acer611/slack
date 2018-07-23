package com.style.slack.dao.user;

import com.style.slack.model.po.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.annotations.ResultMap;
import java.util.List;
import java.util.Map;

/**
 * 用户dao层处理类
 *  Created by Gaofei on 2018/6/11.
 */

public interface UserDao  {


    /**
     * 查询所有用户信息
     * @return
     */

    @Select("SELECT id,user_name,password,nick_name,age,sex,phone,email,head_image,del_flag,create_time,update_time FROM s_user")
    @Results(value ={
            @Result(id=true, property="id",column="id"),
            @Result(property="userName",column="user_name"),
            @Result(property="password",column="PASSWORD"),
            @Result(property="nickName",column="nick_name"),
            @Result(property="age",column="age"),
            @Result(property="sex",column="sex"),
            @Result(property="phone",column="phone"),
            @Result(property="email",column="email"),
            @Result(property="headImage",column="head_image"),
            @Result(property="createTime",column="create_time"),
            @Result(property="updateTime",column="update_time"),
            @Result(property="delFlag",column="del_flag")
    })
    public List<User> selectUsers();



    /**
     * 添加用户信息
     * @param userInfo
     * @return
     */
    @Insert("insert into s_user(id,user_name,password,nick_name,age,sex,phone,email,head_image,del_flag,create_time,update_time) " +
            "values(#{id},#{userName},#{password},#{nickName},#{age},#{sex},#{phone},#{email},#{headImage}," +
            "#{delFlag},#{createTime},#{updateTime})" )
    @SelectKey(before=false,keyProperty="id",resultType=String.class,statementType= StatementType.STATEMENT,statement="SELECT LAST_INSERT_ID() AS id")
    public int insert(User userInfo);



    /**
     * 根据用户名模糊查询用户信息
     * @param name
     * @return
     */
    @SelectProvider(type=UserSQLProvider.class, method="findUserByNameSQL")
    @Results(value ={
            @Result(id=true, property="id",column="id"),
            @Result(property="userName",column="user_name"),
            @Result(property="password",column="PASSWORD"),
            @Result(property="nickName",column="nick_name"),
            @Result(property="age",column="age"),
            @Result(property="sex",column="sex"),
            @Result(property="phone",column="phone"),
            @Result(property="email",column="email"),
            @Result(property="headImage",column="head_image"),
            @Result(property="createTime",column="create_time"),
            @Result(property="updateTime",column="update_time"),
            @Result(property="delFlag",column="del_flag")
    })
    public List<User> findUserByName(String name);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @SelectProvider(type=UserSQLProvider.class, method="updateUserByIdSQL")
    @Results(value ={
            @Result(id=true, property="id",column="id"),
            @Result(property="userName",column="user_name"),
            @Result(property="password",column="PASSWORD"),
            @Result(property="nickName",column="nick_name"),
            @Result(property="age",column="age"),
            @Result(property="sex",column="sex"),
            @Result(property="phone",column="phone"),
            @Result(property="email",column="email"),
            @Result(property="headImage",column="head_image"),
            @Result(property="createTime",column="create_time"),
            @Result(property="updateTime",column="update_time"),
            @Result(property="delFlag",column="del_flag")
    })
    public User updateUser(User user);


    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */

    @SelectProvider(type=UserSQLProvider.class, method="selectUserById")
    @Results(value ={
            @Result(id=true, property="id",column="id"),
            @Result(property="userName",column="user_name"),
            @Result(property="password",column="PASSWORD"),
            @Result(property="nickName",column="nick_name"),
            @Result(property="age",column="age"),
            @Result(property="sex",column="sex"),
            @Result(property="phone",column="phone"),
            @Result(property="email",column="email"),
            @Result(property="headImage",column="head_image"),
            @Result(property="createTime",column="create_time"),
            @Result(property="updateTime",column="update_time"),
            @Result(property="delFlag",column="del_flag")
    })
    public User getUserById(String id);


    /**
     * 根据用户ID查询用户信息(包含角色信息)
     * @param id
     * @return
     */
     @SelectProvider(type=UserSQLProvider.class, method="queryUserByUserId")
     //@ResultMap("UserResult")
    public User queryUserByUserId(String id);


   @Select("select s.id,s.age,s.create_time,s.del_flag,s.email,s.head_image,s.nick_name,s.`PASSWORD`,s.phone,s.sex,s.update_time,s.user_name, " +
           "r.id,r.role_id,r.user_id," +
           "t.id,t.category,t.create_time,t.del_flag,t.`desc`,t.role,t.role,t.update_time " +
           " from s_user s " +
           "left join t_user_role r ON s.id = r.user_id " +
           "left join t_role t ON t.id = r.role_id " +
           "where s.id=#{id}")
    List<Map<String,Object>> testQuery(String id);
}
