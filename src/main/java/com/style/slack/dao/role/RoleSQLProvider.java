package com.style.slack.dao.role;

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
}
