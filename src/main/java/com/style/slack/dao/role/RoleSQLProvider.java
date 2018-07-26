package com.style.slack.dao.role;

import com.style.slack.model.po.Role;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * 角色sql提供类
 * @author  Gaofei
 */
public class RoleSQLProvider {


    /**
     * 根据角色ID查询角色信息
     * @param paraMap
     * @return
     */
    public String queryRoleById(HashMap paraMap){
        return new SQL() {{
            SELECT("id,role,`desc`,category,create_time,update_time,del_flag ");
            FROM("t_role");
            if (paraMap.containsKey("id")) {
                WHERE("id ='"+paraMap.get("id")+"'");
            }
        }}.toString();
    }

    public String insertRole(Role role){
        return new SQL() {{
            INSERT_INTO("t_role");

            if (role.getRole() != null) {
                VALUES("role", "#{role}");
            }

            if (role.getDesc() != null) {
                VALUES("`desc`", "#{desc}");
            }
            if (role.getCategory() != null) {
                VALUES("category", "#{category}");
            }

            if (role.getCreateTime() != null) {
                VALUES("create_time", "#{createTime}");
            }
            if (role.getDelFlag() != null) {
                VALUES("del_flag", "#{delFlag}");
            }
        }}.toString();
    }


}
