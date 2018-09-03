package com.melody.dao;

import com.melody.user.dto.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * 用户查询动态sql
 * @author konghang
 */
public class UserDaoSqlProvider {

    /**
     * 查询用户
     * @param user
     * @return
     */
    public String selectUserBy(User user){
        SQL sql = new SQL();
        sql.SELECT("user_id","ref_user_id","username","salt","password","nickname","sex","email");
        sql.FROM("t_user");
//        if (user.getRefUserId() != null){
//            sql.WHERE("ref_user_id = #{refUserId}");
//        }
//        if (user.getEmail() != null){
//            sql.WHERE("email = #{email}");
//        }
//        if (user.getUsername() != null){
//            sql.WHERE("username = #{username}");
//        }
        return sql.toString();
    }

}
