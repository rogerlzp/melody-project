package com.melody.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.melody.annotation.PermInfo;
import com.melody.exception.BusinessException;
import com.melody.gateway.api.WxAuthService;
import com.melody.gateway.api.WxPayService;
import com.melody.result.JsonApi;
import com.melody.user.api.WxService;
import com.melody.user.dto.UserQueryResult;
import com.melody.vo.Json;
import com.melody.web.base.BaseController;
import com.melody.web.config.Api;
import com.melody.web.config.ApiConstant;
import com.melody.web.config.WxAuthConfig;
import com.melody.web.util.HttpRequest;
import com.melody.web.util.JsonHelper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/wx")
public class WxAuthController extends BaseController {


    @Autowired
    WxAuthConfig wxAuthConfig;

    @Reference(group = "wxService", timeout = 10000)
    WxService wxService;

    @Reference(group = "wxAuthService", timeout = 10000)
    WxAuthService wxAuthService;

    @Reference(group = "wxPayService", timeout = 10000)
    WxPayService wxPayService;

    public static final String USER = "/api/v1/user";
    private static final Logger log = LoggerFactory.getLogger(WxAuthController.class);


    public Map<String, Object> getWxSession(String wxCode) {
        StringBuffer sb = new StringBuffer();
        sb.append("appid=").append(wxAuthConfig.getAppId());
        sb.append("&secret=").append(wxAuthConfig.getSecret());
        sb.append("&js_code=").append(wxCode);
        sb.append("&grant_type=").append(wxAuthConfig.getGrantType());
        String res = HttpRequest.sendGet(wxAuthConfig.getSessionHost(), sb.toString());
        if (res == null || "".equals(res)) {
            return null;
        }
        return JSON.parseObject(res, Map.class);
    }

    /**
     * 根据客户端传过来的code从微信服务器获取appid和session_key，然后生成3rdkey返回给客户端，后续请求客户端传3rdkey来维护客户端登录态
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取sessionId", notes = "小用户允许登录后，使用code 换取 session_key api，将 code 换成 openid 和 session_key")
    @ApiImplicitParam(name = "code", value = "用户登录回调内容会带上 ", required = true, dataType = "String")
    @Api(name = ApiConstant.WX_CODE)
    @RequestMapping(value = "/auth2", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object> createSssion(@RequestParam(required = true, value = "code") String wxCode) {
        Map<String, Object> wxSessionMap = this.getWxSession(wxCode);

        if (null == wxSessionMap) {
//            throw  BusinessException(50010);
//            return  JsonApi.isFail(50010); //TODO: 将返回代码和错误放到统一配置
        }
        //获取异常
        if (wxSessionMap.containsKey(ApiConstant.ERROR_CODE)) {
//            return  JsonApi.isFail(50020);//TODO: 将返回代码和错误放到统一配置
        }
        String wxOpenId = (String) wxSessionMap.get("openid");

        String wxSessionKey = (String) wxSessionMap.get("session_key");
        System.out.println(wxSessionKey);
        Long expires = 1000L;
        if (wxSessionMap.get("expires_in") != null) {
            expires = Long.valueOf(String.valueOf(wxSessionMap.get("expires_in")));
        }


        // 将结果上传到服务器后台，如果已经有用户，则更新用户的关系，并更新SESSION_KEY, 如果没有用户，则返回页面，弹出用户注册界面。
        UserQueryResult userQueryResult = wxService.create3rdSession(wxOpenId, wxSessionKey, expires);
        userQueryResult.setOpenid(wxOpenId);
        Map<String, Object> results = JsonHelper.toRespJson(userQueryResult);
        results.put("data", userQueryResult);
//        return JsonApi.isOk().data(result);
//        return result;
//        return new ModelAndView("registerResult", results);
        return results;
//        return userQueryResult;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET, produces = "application/json")
    public Map<String, Object> createSssion2(@RequestParam(required = true, value = "code") String wxCode) throws Exception {

        Map<String, Object> wxSessionMap = JSON.parseObject(wxAuthService.wxSpOAuth2AccessToken(wxCode), Map.class);


        if (null == wxSessionMap) {
//            throw  BusinessException(50010);
//            return  JsonApi.isFail(50010); //TODO: 将返回代码和错误放到统一配置
        }
        //获取异常
        if (wxSessionMap.containsKey(ApiConstant.ERROR_CODE)) {
//            return  JsonApi.isFail(50020);//TODO: 将返回代码和错误放到统一配置
        }
        String wxOpenId = (String) wxSessionMap.get("openid");

        String wxSessionKey = (String) wxSessionMap.get("session_key");
        System.out.println(wxSessionKey);
        Long expires = 1000L;
        if (wxSessionMap.get("expires_in") != null) {
            expires = Long.valueOf(String.valueOf(wxSessionMap.get("expires_in")));
        }


        // 将结果上传到服务器后台，如果已经有用户，则更新用户的关系，并更新SESSION_KEY, 如果没有用户，则返回页面，弹出用户注册界面。
        UserQueryResult userQueryResult = wxService.create3rdSession(wxOpenId, wxSessionKey, expires);
        userQueryResult.setOpenid(wxOpenId);
        Map<String, Object> results = JsonHelper.toRespJson(userQueryResult);
        results.put("data", userQueryResult);
//        return JsonApi.isOk().data(result);
//        return result;
//        return new ModelAndView("registerResult", results);
        return results;

    }

    @RequestMapping(value = "/prePay", method = RequestMethod.GET, produces = "application/json")
    public JsonApi requestPay(@RequestParam(required = true, value = "openId") String openId,
                                          @RequestParam(required = true, value = "payAmount") Double payAmount,
                                          @RequestParam(required = true, value = "orderId") String orderId)
            throws Exception {

        Map map = wxPayService.wxSpPay(orderId, payAmount, openId);
        log.info(map.toString());
        return JsonApi.isOk().data(map);
    }

    @PostMapping("/xcx/callback")
    public JsonApi login(@RequestBody String body) {


        String oper = "xiaochengxu callback";
        log.info("{}, get result from Tencent:: {}",oper,body);
        return JsonApi.isOk();
    }


    }
