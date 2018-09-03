package com.melody.web.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 获取资源文字工具类型
 */
@Component
public class MessageUtils implements ApplicationContextAware {

	@Autowired
	private MessageSource messageSource;

	private static ApplicationContext applicationContext; // Spring应用上下文环境

         /*

          * 实现了ApplicationContextAware 接口，必须实现该方法；

          *通过传递applicationContext参数初始化成员变量applicationContext

          */

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		MessageUtils.applicationContext = applicationContext;
	}



	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}




	/**
	 * 获取资源文字
	 * 
	 * @param code 资源码
	 * @param variables 变量，null-不包含变量
	 * @param defaultMessage 默认信息，null-不使用默认提示
	 * @return 最终解析后的资源文字
	 */
	public static String getMessage(String code, Object[] variables, String defaultMessage) {

		if (defaultMessage == null) {
			return applicationContext.getMessage(code, variables, null);
		} else {
			return applicationContext.getMessage(code, variables, defaultMessage, null);
		}
	}
	
	/**
	 * 获取资源文字
	 * 
	 * @param code 资源码
	 * @return 最终解析后的信息
	 */
	public static String getMessage(String code) {
		return getMessage(code, null, null);
	}
	
	/**
	 * 获取资源文字
	 * 
	 * @param code 资源码
	 * @param variables 变量，null-不包含变量
	 * @return 最终解析后的信息
	 */
	public static String getMessage(String code, String[] variables) {
		return getMessage(code, variables, null);
	}
	
	/**
	 * 获取资源文字
	 * 
	 * @param code 资源码
	 * @param defaultMessage 默认信息，null-不使用默认提示
	 * @return 最终解析后的信息
	 */
	public static String getMessage(String code, String defaultMessage) {
		return getMessage(code, null, defaultMessage);
	}

	/**
	 * 指定代码是否有资源文字
	 * 
	 * @param code 代码
	 * @param message 根据代码获取到到的资源文字
	 * @return true-存在对应的代码码对应的资源文字
	 */
	public static boolean isMessageConfiged(String code, String message) {
		return !(code).equals(message);
	}


	public String getMessage(String result, Object[] params) {
		String message = "";
		try {
			Locale locale = LocaleContextHolder.getLocale();
			message = messageSource.getMessage(result, params, locale);
		} catch (Exception e) {
//			LOGGER.error("parse message error! ", e);
		}
		return message;
	}

}
