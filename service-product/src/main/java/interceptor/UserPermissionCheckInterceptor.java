package com.wy.interceptor;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.wy.ServiceContext;
import com.wy.common.annotation.Role;
import com.wy.common.constants.BusinessCodes;
import com.wy.common.constants.RedisCodes;
import com.wy.common.exception.BusinessException;
import com.wy.common.model.GeneralEnter;
import com.wy.common.redis.RedisCache;
import com.wy.user.dto.User;

/**
 * 针对角色校验的AOP拦截方法
 * 
 *  Created by liuyw on 2015/11/17.
 * 
 */
@Component
@Aspect
public class UserPermissionCheckInterceptor {

	@Autowired
	RedisCache redisCache;

	/**
	 * 标准的controller切面定义
	 *
	 */
	@Pointcut("execution(* com.wy.services..*.*(..)))")
	public void sercicesPointcut() {
	}

	/**
	 * 检测是否符合登录权限（基于AspectJ框架）
	 * 
	 * @param pjp ProceedingJoinPoint
	 * @return 方法调用的结果
	 * @throws Throwable 反射调用发生异常
	 */
	@Around("sercicesPointcut()")
	public Object checkLoginRole(ProceedingJoinPoint pjp) throws Throwable {

		Signature signature = pjp.getSignature();
		if (!(signature instanceof MethodSignature)) {
			return pjp.proceed();
		}

		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();

		if(pjp.getArgs() != null && pjp.getArgs().length > 0){
			Object object=pjp.getArgs()[0];
			if (!(object instanceof GeneralEnter)) {
				return pjp.proceed();
			}
			GeneralEnter generalPara=((GeneralEnter)pjp.getArgs()[0]);
			
			Role role = getAnnotation(method);
			// 如果无相应配置则不进行角色识别
			if (role == null) {
				return pjp.proceed();
			}
			checkLogin(role,generalPara);
		}



		return pjp.proceed();

	}

	/**
	 * 获取角色判断配置，优先取method上的配置，如果没有，则取Controller上的配置，如果都没则认为没有。
	 * 基于业务考虑，对@RequestMapping的方法才进行权限校验
	 * 
	 * @param method 执行的切向方法
	 * @return 此方法实际需要被使用的role配置。null-如果未配置
	 */
	private Role getAnnotation(Method method) {
		/*// 不存在RequestMapping的请求不认为是web的直接请求，不做处理
		if(!method.isAnnotationPresent(RequestMapping.class)) {
			return null;
		}*/
		
		Role role = method.getAnnotation(Role.class);
		if(role != null) {
			return role;
		}
		
		return method.getDeclaringClass().getAnnotation(Role.class);
	}

	/**
	 * 判断是否登录超时
	 * 
	 * @param methodSignature	角色配置
	 */
	/**
	 * 判断是否登录超时
	 *
	 * @param role	角色配置
	 */
	private void checkLogin(Role role,GeneralEnter generalPara) {
 		if (StringUtils.isBlank(generalPara.getSessionKey())) {
			throw new BusinessException(BusinessCodes.ACCOUNT_SESSIONKEY_ERROR,"Please enter sessionkey");
		}

		String mobileNo=redisCache.getString(RedisCodes.SESSION+generalPara.getSessionKey());

		User redisUser=redisCache.getObject(RedisCodes.USER+mobileNo,User.class);
		if (ObjectUtils.isEmpty(redisUser)){
			throw new BusinessException(BusinessCodes.ACCOUNT_SESSIONKEY_TIMEOUT,"Login timeout, please log in again");
		}
		ServiceContext.initServiceContext(redisUser);
	}
}
