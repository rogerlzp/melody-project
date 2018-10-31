package com.melody.common.constant;

/**
 * ClassName: ProductEnum 
 * @Description: TODO
 * @author Ciro
 * @date 2015年12月21日
 */
public enum UserLoginEnum {
	UserLogin_INIT("0","初始化"),
	UserLogin_END("1","已过期");



	private String enName;
	private String zhName;

	UserLoginEnum(String enName, String zhName) {
		this.enName = enName;
		this.zhName = zhName;
	}

	public String getEnName() {
		return enName;
	}

	public String getZhName() {
		return zhName;
	}

	public static UserLoginEnum getEnumByEnName(String enName) {
		for (UserLoginEnum productEnum : UserLoginEnum.values()) {
			if (productEnum.enName.equalsIgnoreCase(enName)) {
				return productEnum;
			}
		}
		return null;
	}


}
