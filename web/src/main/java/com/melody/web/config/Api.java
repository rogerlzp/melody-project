package com.melody.web.config;

import java.lang.annotation.*;

@Target({ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface Api {
	/*接口名称*/
	String name();
	/*每天上限*/
	int accessLimit() default 10000;
	/*接口版本*/	
	String version() default "v1";
	/*接口禁用*/	
	boolean disabled() default false;
	/*参数解密算法*/	
	String algorithm() default "none";
}
