package com.melody.dao;


import com.melody.user.dto.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


public interface UserMapper {
    @Delete({
        "delete from t_user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into t_user (ref_user_id, username, ",
        "nickname, salt, ",
        "`password`, sex, email, ",
        "head_image, create_ip, ",
        "create_date)",
        "values (#{refUserId,jdbcType=VARCHAR}, #{username,jdbcType=VARCHAR}, ",
        "#{nickname,jdbcType=VARCHAR}, #{salt,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{sex,jdbcType=TINYINT}, #{email,jdbcType=VARCHAR}, ",
        "#{headImage,jdbcType=VARCHAR}, #{createIp,jdbcType=VARCHAR}, ",
        "#{createDate,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userId", before=false, resultType=Integer.class)
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userId", before=false, resultType=Integer.class)
    int insertSelective(User record);

    @Select({
        "select",
        "user_id, ref_user_id, username, nickname, salt, `password`, sex, email, head_image, ",
        "create_ip, create_date",
        "from t_user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ref_user_id", property="refUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="salt", property="salt", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.TINYINT),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="head_image", property="headImage", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_ip", property="createIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.BIGINT)
    })
    User selectByPrimaryKey(Integer userId);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update t_user",
        "set ref_user_id = #{refUserId,jdbcType=VARCHAR},",
          "username = #{username,jdbcType=VARCHAR},",
          "nickname = #{nickname,jdbcType=VARCHAR},",
          "salt = #{salt,jdbcType=VARCHAR},",
          "`password` = #{password,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=TINYINT},",
          "email = #{email,jdbcType=VARCHAR},",
          "head_image = #{headImage,jdbcType=VARCHAR},",
          "create_ip = #{createIp,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=BIGINT}",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}