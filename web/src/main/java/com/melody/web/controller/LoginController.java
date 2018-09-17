package com.melody.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.melody.result.JsonApi;
import com.melody.user.api.LoginService;
import com.melody.user.dto.UserQueryEnter;
import com.melody.user.dto.UserQueryResult;
import com.melody.web.base.BaseController;
import com.melody.web.util.JsonHelper;
import com.melody.web.vo.LoginForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by liuyw on 2015/11/25.
 */

@RestController
@RequestMapping(value = "/user/login")
public class LoginController extends BaseController {

    @Reference(group = "loginService", timeout = 20000)
    LoginService loginService;

    @RequestMapping
    public  Map<String, Object> login(LoginForm loginFrom, HttpServletRequest request, BindingResult bindingResult) throws IOException {

        String registrationId = request.getHeader("registration-id");

        UserQueryEnter para = new UserQueryEnter();
        BeanUtils.copyProperties(loginFrom, para);
        para.setIp(getIpAddr(request));//获取登录的ip地址
        para.setRegistrationId(registrationId);

        UserQueryResult result = loginService.doLogin(para);

        Map<String, Object> results = JsonHelper.toRespJson(result);
        results.put("data", result);
//        return JsonApi.isOk().data(result);
//        return result;
//        return new ModelAndView("registerResult", results);
        return results;
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
