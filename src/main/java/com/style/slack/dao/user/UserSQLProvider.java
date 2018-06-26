package com.style.slack.dao.user;

import org.apache.ibatis.jdbc.SQL;

/**
 * create by Gaofei 2018-06-26
 */
public class UserSQLProvider {

    /**
     * 根据用户名搜索用户信息
     * @param userName
     * @return
     */
    public String findUserByNameSQL(String userName){
        return new SQL() {{
            SELECT("id,user_name,password,nick_name,age,sex,phone,email,head_image,del_flag,create_time,update_time");
            FROM("s_user");
            if (userName != null) {
                WHERE("user_name LIKE '%"+userName+"%'");
            }
        }}.toString();
    }
}
