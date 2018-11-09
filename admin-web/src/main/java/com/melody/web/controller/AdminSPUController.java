package com.melody.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.melody.admin.api.AdminSKUService;
import com.melody.admin.api.AdminSPUService;
import com.melody.admin.dto.SysUser;
import com.melody.admin.dto.SysUserRole;
import com.melody.annotation.PermInfo;
import com.melody.constant.Root;
import com.melody.product.dto.*;
import com.melody.util.PageUtils;
import com.melody.vo.Json;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@PermInfo(value = "创造产品模块", pval = "a:sku")
@RestController
@RequestMapping("/adminSPU")
public class AdminSPUController {

    private static final Logger log = LoggerFactory.getLogger(AdminSPUController.class);


    @Reference(group = "adminSKUService", timeout = 10000)
    private AdminSKUService adminSKUService;

    @Reference(group = "adminSPUService", timeout = 10000)
    private AdminSPUService adminSPUService;

    @PermInfo("添加SPU")
    @RequiresPermissions("a:sku:spu:add")
    @PostMapping("/add")
    public Json add(@RequestBody String brandStr) {

        String oper = "add brand";
        log.info("{}, body: {}", oper, brandStr);
        SPU brandObj = JSON.parseObject(brandStr, SPU.class);

        // 获取SpuAttr属性
        // 因为传来的Image 只有 picUrl, 不能自动转化为SkuImage, 所以手动转化一下，在添加到 sku中
        JSONObject jsonObject = JSON.parseObject(brandStr);
        JSONArray jsonArray = jsonObject.getJSONArray("spuAttrList");

        if (jsonArray != null) {
            List<SpuAttr> spuAttrList = jsonArray.toJavaList(SpuAttr.class);
            brandObj.setSpuAttrList(spuAttrList);
        }

        Long spuId = adminSPUService.addSPU(brandObj);
        return Json.result(oper, spuId != -1 ? true : false)
                .data("spuId", spuId);
    }


    @PermInfo("查询所有品牌")
    @RequiresPermissions("a:sku:spu:query")
    @PostMapping("/query")
    public Json query(@RequestBody String body) {
        String oper = "query brand";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);

        Page<SPU> page = adminSPUService.querySPUList(PageUtils.getPageParam(json));
        // 每个SPU查询属性
        // 变化属性
        return Json.succ(oper).data("page", page);
    }

    @PermInfo("查询所有品牌")
    @RequiresPermissions("a:sku:spu:list")
    @GetMapping("/list")
    public Json list(String spuCode) {
        String oper = "query spu";
        log.info("{}, body: {}", oper, spuCode);

        SPU spu = adminSPUService.querySPUBySpuCode(spuCode);
        // 每个SPU查询属性
        // 变化属性
        return Json.succ(oper).data("spu", spu);
    }


    @PermInfo("根据分类和品牌查询SPU")
    @RequiresPermissions("a:sku:spu:query")
    @PostMapping("/queryByBC")
    public Json queryByBC(@RequestBody String body) {
        String oper = "query brand";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);
        String categoryCode = json.getString("categoryCode");
        String brandCode = json.getString("brandCode");

        List<SPU> spuList = adminSPUService.querySPUByBC(categoryCode, brandCode);
        return Json.succ(oper).data("spuList", spuList);
    }

    @PermInfo("根据分类和品牌查询SPU")
    @RequiresPermissions("a:sku:spu:delete")
    @PostMapping("/deleteAttr")
    public Json deleteAttr(@RequestBody String body) {
        String oper = "delete attr";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);
        Long attrId = json.getLong("attrId");
        String spuCode = json.getString("spuCode");

        int deleteResult = adminSPUService.deleteAttr(attrId, spuCode);
        return Json.succ(oper).data("deleteResult", deleteResult);
    }


    // TODO: 删除后，对应的SKU删除
    // 添加到日志里面
    @PermInfo("删除SPU")
    @RequiresPermissions("a:sku:spu:del")
    @DeleteMapping
    public Json delete(@RequestBody String body) {

        String oper = "delete spu";
        log.info("{}, body: {}", oper, body);

        JSONObject jsonObj = JSON.parseObject(body);
        String id = jsonObj.getString("id");
        if (StringUtils.isEmpty(id)) {
            return Json.fail(oper, "无法删除SPU：参数为空（用户id）");
        }

        //限制：不能删当前登录用户
        boolean success = adminSPUService.deleteById(Long.valueOf(id)) == 1 ? true : false;
        log.info("delete result: " + success);
        if (success) {
            adminSKUService.deleteBySPUId(Long.valueOf(id));
        }
        return Json.result(oper, success);
    }


    @PatchMapping("/update")
    public Json updateSPU(@RequestBody String body) {

        String oper = "update SPU";
        log.info("{}, body: {}", oper, body);
        SPU brandObj = JSON.parseObject(body, SPU.class);

        // 获取SpuAttr属性
        JSONObject jsonObject = JSON.parseObject(body);
        JSONArray jsonArray = jsonObject.getJSONArray("spuAttrList");

        if (jsonArray != null) {
            List<SpuAttr> spuAttrList = jsonArray.toJavaList(SpuAttr.class);
            brandObj.setSpuAttrList(spuAttrList);
        }

        int updateResult = adminSPUService.updateSPU(brandObj);
        return Json.result(oper, updateResult == 1 ? true : false)
                .data("updateResult", updateResult);

    }
}
