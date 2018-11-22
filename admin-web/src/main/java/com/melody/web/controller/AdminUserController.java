package com.melody.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminSKUService;
import com.melody.admin.api.AdminUserService;
import com.melody.admin.dto.SysUser;
import com.melody.annotation.PermInfo;
import com.melody.product.dto.*;
import com.melody.system.dto.SysCustomerLevel;
import com.melody.user.dto.Role;
import com.melody.user.dto.User;
import com.melody.util.PageUtils;
import com.melody.vo.Json;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@PermInfo(value = "用户管理模块", pval = "a:user")
@RestController
@RequestMapping("/adminUser")
public class AdminUserController {

    private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);


    @Reference(group = "adminUserService", timeout = 10000)
    private AdminUserService adminUserService;

    @PermInfo("根据分类和品牌查询SPU")
    @RequiresPermissions("a:user:userlevel:query")
    @GetMapping("/queryUserLevel")
    public Json queryByBC() {
        String oper = "query userlevel";
        List<SysCustomerLevel> customerLevelList = adminUserService.getSysCustomerLevel();
        return Json.succ(oper).data("customerLevelList", customerLevelList);
    }

    @PermInfo("添加用户")
    @RequiresPermissions("a:user:user:add")
    @PostMapping("/addUser")
    public Json addUser(@RequestBody String brandStr) {

        String oper = "add brand";
        log.info("{}, body: {}", oper, brandStr);

        User user = JSON.parseObject(brandStr, User.class);

        JSONObject jsonObject = JSON.parseObject(brandStr);
        Integer roleId = jsonObject.getInteger("roleId");
        Role role = new Role();
        role.setId(roleId);
        Long userId = adminUserService.addUser(user, role);
        return Json.result(oper, userId == -1 ? false : true)
                .data("userId", userId);
    }

    @PermInfo("添加用户")
    @RequiresPermissions("a:user:role:add")
    @PostMapping("/addRole")
    public Json addUserRole(@RequestBody String brandStr) {

        String oper = "add brand";
        log.info("{}, body: {}", oper, brandStr);

        Role role = JSON.parseObject(brandStr, Role.class);
        int roleId = adminUserService.addRole(role);
        return Json.result(oper, roleId == -1 ? false : true)
                .data("roleId", roleId);
    }

    @PermInfo("查询所有角色")
    @RequiresPermissions("a:user:role:query")
    @PostMapping("/role/query")
    public Json queryRole(@RequestBody String body) {
        String oper = "query brand";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);
        Page<Role> page = adminUserService.queryRoleList(PageUtils.getPageParam(json));
        return Json.succ(oper).data("page", page);
    }


    @PermInfo("查询所有用户")
    @RequiresPermissions("a:user:user:query")
    @PostMapping("/user/query")
    public Json query(@RequestBody String body) {
        String oper = "query brand";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);
        Page<User> page = adminUserService.queryUserList(PageUtils.getPageParam(json));
        return Json.succ(oper).data("page", page);
    }


    // TODO: 删除后，对应的SKU删除
    // 添加到日志里面
    @PermInfo("删除User")
    @RequiresPermissions("a:user:user:del")
    @DeleteMapping
    public Json delete(@RequestBody String body) {

        String oper = "delete user";
        log.info("{}, body: {}", oper, body);

        JSONObject jsonObj = JSON.parseObject(body);
        Long userId = jsonObj.getLong("userId");
        if (userId == null) {
            return Json.fail(oper, "无法删除User：参数为空（userId）");
        }

        //限制：不能删当前登录用户
        boolean success = adminUserService.deleteUserByUserId(userId) == 1 ? true : false;
        log.info("delete result: " + success);
        return Json.result(oper, success);
    }

    @PermInfo("根据用户名查询User")
    @RequiresPermissions("a:user:user:query")
    @GetMapping("/search")
    public Json queryUser(String username) {
        String oper = "query user";
        log.info("{}, body: {}", oper, username);
//
//        JSONObject jsonObj = JSON.parseObject(body);
//        String name = jsonObj.getString("name");
        List<User> userList = adminUserService.findUserByName(username);
        return Json.succ(oper).data("userList", userList);
    }

    @PermInfo("根据用户ID查询User")
    @RequiresPermissions("a:user:user:query")
    @GetMapping("/detail")
    public Json queryUserDetail(Long userId) {
        String oper = "query user detail";
        log.info("{}, body: {}", oper, userId);

        User user = adminUserService.findUserDetailByUserId(userId);
        return Json.succ(oper).data("user", user);
    }

    @PermInfo("更新系统用户的信息")
    @RequiresPermissions("a:user:info:update")
    @PatchMapping("/update")
    public Json update(@RequestBody String body) {

        String oper = "update user";
        log.info("{}, body: {}", oper, body);

        User user = JSON.parseObject(body, User.class);

        // 更新用户
        //boolean success = sysUserService.update(user,new EntityWrapper<User>().eq("uid",user.getUid()));
        boolean success = adminUserService.updateUser(user) == 1 ? true : false;
        return Json.result(oper, success).data("updated", "1");
    }
}