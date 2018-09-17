package com.melody.web.base;

import com.google.gson.Gson;

import com.melody.common.constant.BusinessCodes;
import com.melody.exception.BusinessException;
import com.melody.result.JsonApi;
import com.melody.web.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

import com.melody.web.util.MessageUtils;

@Controller
public class BaseController {


//    @Autowired
//    private MessageSource messageSource;

    @Autowired
    ReloadableResourceBundleMessageSource messageSource;


    private static final String UNKNOWN_ERROR = "9999";

    /**
     * 异常页面控制
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public JsonApi runtimeExceptionHandler(Exception ex) {
        Map<String, Object> resp = JsonHelper.toRespJson(UNKNOWN_ERROR, "服务器异常");
        // 根据不同错误转向不同页面
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;

            String message = getMessage("errorCode." + businessException.getErrorCode(), null);

            return JsonApi.isFail(Integer.valueOf(businessException.getErrorCode()), message);

        } else if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            BindingResult result = bindException.getBindingResult();
            FieldError error = result.getFieldError();
            String field = error.getField();
            String code = error.getDefaultMessage();
            String message = String.format("%s:%s", field, code);
            return JsonApi.isFail(Integer.valueOf(BusinessCodes.ERROR), "参数验证失败=" + message);
        } else {
            return JsonApi.unknowError();
        }

    }

    protected String renderString(HttpServletResponse response, Object object) {
        return renderString(response, new Gson().toJson(object), "application/json");
    }

    /**
     * 客户端返回字符串
     *
     * @param response
     * @param string
     * @return
     */
    protected String renderString(HttpServletResponse response, String string, String type) {
        try {
            response.reset();
            response.setContentType(type);
            response.setCharacterEncoding("utf-8");
            //解决跨域问题
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.getWriter().print(string);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public String getMessage(String result, Object[] params) {
        String message = "";
        try {
            Locale locale = LocaleContextHolder.getLocale();
            message = messageSource.getMessage(result, params, null);
        } catch (Exception e) {
//			LOGGER.error("parse message error! ", e);
        }
        return message;
    }

    public String getMessage2(String result, Object[] params) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames("/i18n/messages");

        String message = "";
        try {
            Locale locale = LocaleContextHolder.getLocale();
            message = messageSource.getMessage(result, params, locale);
        } catch (Exception e) {
//            log.error("parse message error! ", e);
        }
        return message;
    }
}