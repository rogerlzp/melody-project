package com.melody.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.melody.common.utils.IpUtils;
import com.melody.common.utils.StringUtils;
import com.melody.common.utils.crypto.CryptoUtils;
import com.melody.exception.AuthorizeException;
import com.melody.exception.BaseException;
import com.melody.exception.TxException;
import com.melody.result.ErrorCode;
import com.melody.result.JsonApi;
import com.melody.user.api.RegisterService;
import com.melody.user.api.UserService;
import com.melody.user.dto.RegisterEnter;
import com.melody.user.dto.RegisterResult;
import com.melody.user.dto.User;
import com.melody.web.base.BaseController;
import com.melody.web.config.shiro.JwtUtil;
import com.melody.web.vo.UserVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserController extends BaseController {

    @Reference(group = "userService")
    private UserService userService;

    @Reference(group = "registerService")
    private RegisterService registerService;


    @ApiOperation(value = "用户登录", notes = "用户登录,使用用户名和邮箱都可以")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, type = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, type = "String")
    })
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public JsonApi login(String username, String password) {
        User search = new User();
        search.setUserName(username);
        User user = userService.selectUserBy(search);
        if (StringUtils.isEmpty(user)) {
            search.setUserName(null);
            search.setEmail(username);
            user = userService.selectUserBy(search);
        }
        if (StringUtils.isEmpty(user)) {
            throw new AuthorizeException(ErrorCode.USER_UNKNOWN_ACCOUNT);
        }
        String token = "";
        if (CryptoUtils.verifyPassword(user.getPassword(), password + user.getSalt() + username)) {
            token = JwtUtil.createToken(username, user.getSalt());
        } else {
            throw new AuthorizeException(ErrorCode.USER_ERROR_PASSWORD);
        }
        JSONObject json = JSON.parseObject(JSON.toJSONString(user));
        json.remove("password");
        json.remove("salt");
        return JsonApi.isOk().message(token).data(json);
    }

    @ApiOperation(value = "用户注册", notes = "用户可以注册")
    @ApiImplicitParam(name = "userVO", value = "用户对象", required = true, type = "String")
    @PostMapping(value = "/register")
    public RegisterResult register(@Valid UserVO userVO, HttpServletRequest request, BindingResult bindingResult) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        user.setCreateIp(IpUtils.getRealIp(request));
        RegisterEnter registerEnter = new RegisterEnter();
        BeanUtils.copyProperties(userVO, registerEnter);

//        try {
        RegisterResult registerResult = registerService.registerUser(registerEnter);
//            userService.saveUser(user);
//        } catch (TxException e) {
//            throw new BaseException(e.getErrorCode());
//        }
//        return JsonApi.isOk();
        return registerResult;
    }

    @ApiOperation(value = "用户信息", notes = "获取用户信息")
    @ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, type = "String")
    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public JsonApi userInfo(@RequestHeader(name = "Authorization") String token) {
        String username = JwtUtil.getUsername(token);
        User user = new User();
        user.setUserName(username);
        user = userService.selectUserBy(user);
        JSONObject json = JSON.parseObject(JSON.toJSONString(user));
        json.remove("password");
        json.remove("salt");
        return JsonApi.isOk().data(json);
    }

}