package com.melody.common.constant;

/**
 * 用户等级
 * Created by znz on 2016/01/03.
 */
public enum UserLevelEnum {
    //用户等级
	NO_LEVEL("1000", "普通用户"),
    REGISTER_LEVEL("1001", "普通合伙人"),
    GOLD_LEVEL("1002", "金牌合伙人"),
    DIAMOND_LEVEL("1003", "钻石合伙人"),
    HIGHEST_LEVEL("1004", "最强合伙人");


    private String enName;
    private String zhName;

    UserLevelEnum(String enName, String zhName) {
        this.enName = enName;
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public String getZhName() {
        return zhName;
    }

    public static UserLevelEnum getEnName(String enName) {
        for (UserLevelEnum couponEnum : UserLevelEnum.values()) {
            if (couponEnum.enName.equalsIgnoreCase(enName)) {
                return couponEnum;
            }
        }
        return null;
    }
    public static void main(String[] args) {
    	System.out.println(UserLevelEnum.getEnName("1000").zhName);
	}
}
