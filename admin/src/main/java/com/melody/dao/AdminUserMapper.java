package com.melody.dao;

import com.melody.product.dto.SKU;
import com.melody.product.dto.SPU;
import com.melody.system.dto.SysCustomerLevel;
import com.melody.user.dto.Role;
import com.melody.user.dto.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserMapper {


    List<SysCustomerLevel> getCustomerLevel();

    int insertUser(User user);

    int insertUserRole(@Param(value = "id") long id,
                       @Param(value = "userId") long userId,
                       @Param(value = "roleId") int roleId);

    int countAllUser(); //查询用户

    int insertRole(Role role); // 增加角色

    int countAllRole(); //查询角色

    List<User> queryUserList(@Param(value = "offset") Integer offset,
                           @Param(value = "pageSize") Integer pageSize,
                             @Param(value = "status") String status);

    List<Role> queryRoleList(@Param(value = "offset") Integer offset,
                             @Param(value = "pageSize") Integer pageSize);


    int updateUserStatus(@Param(value = "userId") long userId, @Param(value = "status") String status);

    List<User> findUserByName(@Param(value = "userName") String userName);

    int updateByPrimaryKeySelective(User user);

    int getUserByRoleId(@Param(value = "userId") long userId, @Param(value = "roleId") Integer roleId);
    int updateUserRole(@Param(value = "userId") long userId, @Param(value = "roleId") Integer roleId);

}
