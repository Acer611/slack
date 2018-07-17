package com.style.slack.dao.role;

import com.style.slack.model.po.Role;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.HashMap;
import java.util.List;


/**
 * 角色Dao层数据处理类
 * @author  Gaofei
 * @Date 2018-07-13
 */
public interface RoleDao {

    /**
     * 查询所有角色
     * @return
     */

    @Select("SELECT id,role,`desc`,category,create_time,update_time,del_flag FROM t_role ")
    @Results(value ={
            @Result(id=true, property="id",column="id"),
            @Result(property="role",column="role"),
            @Result(property="desc",column="desc"),
            @Result(property="category",column="category"),
            @Result(property="createTime",column="create_time"),
            @Result(property="updateTime",column="update_time"),
            @Result(property="delFlag",column="del_flag")
    })
    public List<Role> selectRoles();


    /**
     * 根据角色Id查询所有角色信息
     * @return
     */
    @SelectProvider(type=RoleSQLProvider.class, method="queryRoleById")
    @Results(value ={
            @Result(id=true, property="id",column="id"),
            @Result(property="role",column="role"),
            @Result(property="desc",column="desc"),
            @Result(property="category",column="category"),
            @Result(property="createTime",column="create_time"),
            @Result(property="updateTime",column="update_time"),
            @Result(property="delFlag",column="del_flag")
    })
    public Role queryRoleById(HashMap paraMap);
}
