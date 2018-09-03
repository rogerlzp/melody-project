package com.melody.web.util;

import com.alibaba.fastjson.JSON;
import com.melody.base.GeneralResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyw on 2015/11/17.
 */
public class JsonHelper {
	
    /**
     * 转为JSON字符串
     *
     * @param target 源对象
     * @return json字符串，null-如果无法解析
     */
    public static String toJson(Object target) {
        try {
            return JSON.toJSONString(target);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * JSON字符串转为对象
     *
     * @param json json字符串
     * @param clazz 转换后的类型
     * @param <T> 数据类型
     * @return 转换后的对象。null-如果无法解析
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * JSON返回的基础数据格式
     *
     * @param code 错误码，传null则采用默认错误码
     * @param msg 错误信息
     * @return 用于输出的Map对象
     */
    public static Map<String, Object> toRespJson(String code, String msg) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();

        if(StringUtils.isBlank(code)) {
            code=SUCCESS;
        }
        jsonMap.put("resultCode", code);
        jsonMap.put("resultMessage", msg);

        return jsonMap;
    }
    
    public static final String SUCCESS = "0";
    
    public static Map<String, Object> toSuccessRespJson() {
    	return toRespJson(SUCCESS,"");
    }
    

    /**
     * 将对象转成Map
     *
     * @param source 原对象
     * @param <S> 数据类型
     * @return 反射后的Map对象
     */
    public static <S> Map<String, Object> toJsonMap(S source) {
        Map<String, Object> result = new HashMap<String, Object>();
        PropertyDescriptor[] sourcePds = BeanUtils.getPropertyDescriptors(source.getClass());
        for(PropertyDescriptor pd : sourcePds) {
            Method readMethod = pd.getReadMethod();
            if(readMethod != null) {
                readMethod.setAccessible(true);
                try {
                    result.put(pd.getName(), readMethod.invoke(source));
                } catch (Throwable ex) {
                    result.put(pd.getName(), null);
                }
            }
        }
        return  result;
    }


    public static Map<String, Object> toRespJson(GeneralResult result) {

        Map<String, Object> jsonMap = new HashMap<String, Object>();

        jsonMap.put("resultCode", result.getCode());
        jsonMap.put("resultMessage", result.getMessage());

        result.setCode(null);
        result.setMessage(null);
        return jsonMap;

    }
}
