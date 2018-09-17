package com.melody.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.melody.admin.api.AdminSKUService;
import com.melody.admin.api.AdminUserService;
import com.melody.annotation.PermInfo;
import com.melody.product.dto.SPU;
import com.melody.system.dto.SysCustomerLevel;
import com.melody.vo.Json;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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

}