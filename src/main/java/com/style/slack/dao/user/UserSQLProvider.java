package com.style.slack.dao.user;

import com.style.slack.model.po.User;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

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


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public String updateUserByIdSQL(User user){
        return new SQL() {{
            {
                UPDATE("s_user");
                if(!StringUtils.isEmpty(user.getUserName())){
                    SET("user_name=#{userName}");
                }
                if(!StringUtils.isEmpty(user.getPassword())){
                    SET("password=#{password}");
                }
                if(!StringUtils.isEmpty(user.getNickName())){
                    SET("nick_name=#{nickName}");
                }
                if(!StringUtils.isEmpty(user.getAge())&&0!=user.getAge()){
                    SET("age=#{age}");
                }
                if(!StringUtils.isEmpty(user.getSex())){
                    SET("sex=#{sex}");
                }
                if(!StringUtils.isEmpty(user.getPhone())){
                    SET("phone=#{phone}");
                }
                if(!StringUtils.isEmpty(user.getEmail())){
                    SET("email=#{email}");
                }
                if(!StringUtils.isEmpty(user.getHeadImage())){
                    SET("head_image=#{headImage}");
                }
                if(!StringUtils.isEmpty(user.getCreateTime())){
                    SET("create_time=#{createTime}");
                }
                if(!StringUtils.isEmpty(user.getUpdateTime())){
                    SET("update_time=#{updateTime}");
                }
                if(!StringUtils.isEmpty(user.getDelFlag())){
                    SET("del_flag=#{delFlag}");
                }
                WHERE("id=#{id}");
            }
        }}.toString();
    }


    /**
     * 根据用户Id查询用户信息
     * @param id
     * @return
     */
    public String selectUserById(String id){
        return new SQL() {{
            SELECT("id,user_name,password,nick_name,age,sex,phone,email,head_image,del_flag,create_time,update_time");
            FROM("s_user");
            if (id != null) {
                WHERE("id ='"+id+"'");
            }
        }}.toString();
    }


    /**
     * 根据用户id查询用户信息包含角色信息
     * @param id
     * @return
     */

    public String queryUserByUserId(String id){
        return new SQL() {{
            SELECT("s.id,s.age,s.create_time,s.del_flag,s.email,s.head_image,s.nick_name,s.`PASSWORD`,s.phone,s.sex,s.update_time,s.user_name");
            SELECT("r.id,r.role_id,r.user_id,");
            SELECT("t.id,t.category,t.create_time,t.del_flag,t.`desc`,t.role,t.role,t.update_time");

            //FROM s_user s LEFT JOIN t_user_role r ON s.id = r.user_id LEFT JOIN  t_role t ON t.id = r.role_id
            FROM("s_user s");
            LEFT_OUTER_JOIN("t_user_role r ON s.id = r.user_id");
            LEFT_OUTER_JOIN(" t_role t ON t.id = r.role_id");
            if (id != null) {
                WHERE("s.id ='"+id+"'");
            }
        }}.toString();
    }

}
