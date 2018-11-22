package com.melody.admin.api;


import com.baomidou.mybatisplus.plugins.Page;
import com.melody.product.dto.Brand;
import com.melody.system.dto.SysCustomerLevel;
import com.melody.user.dto.Role;
import com.melody.user.dto.User;
import com.melody.user.dto.UserLevel;

import java.util.List;

/**
 * 管理用户，设置会员体系，会员折扣
 */
public interface AdminUserService {

    // 获取会员分类
    List<SysCustomerLevel>  getSysCustomerLevel();

    // 增加设计师
    Long addUser(User user, Role role);

    // 增加角色
    int addRole(Role role);

    // 获取角色
    Page<Role> queryRoleList(Page page);

    // 获取用户
    Page<User> queryUserList(Page page);

    // 删除用户
    int deleteUserByUserId(Long userId);

    // 获取用户
    List<User> findUserByName(String username);

    int updateUser(User user);

    User findUserDetailByUserId(Long userId);

}

