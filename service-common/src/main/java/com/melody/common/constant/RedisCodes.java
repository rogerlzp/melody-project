package com.melody.common.constant;

/**
 * Created by liuyw on 2015/11/19.
 */
public class RedisCodes {
    /**
     * 验证码过期时间
     */
    public final static Integer VERIFYCODE_TIME = 5*60;
    public final static String VERIFYCODE = "VERIFYCODE_";

    /**
     * 短信验证码en'd
     */
    public final static Integer SMS_CODE_EXPIRE_TIME = 5*60;

    /**
     * 七牛上传Token
     */
    public final static Integer QINIU_TOKEN_EXPIRE_TIME = 1*60*60;

    /**
     * SESSION
     */
    public final static String SESSION = "SESSION_";
    /**
     * 用户
     */
    public final static String USER = "USER_";


    /**
     * 微信小程序用户
     */
    public final static String WX_USER = "WXUSER_";

    /**
     * 短信
     */
    public final static String MOBILE_SMS="SMS_";

    public final static String MOBILE_SMS_VERIFY="SMS_VERIFY_";
    
    public final static String PRODUCT_LOCK = "POL";


    /**
     * 七牛TOKEN
     */
    public final static String QINIU_UPTOKEN="QINIU_UPTOKEN_";


    /**
     * Shop
     */
    public final static String SHOP_LOCK = "SHOPL";

    /**
     * 参数缓存
     */
    public final static String DICTCACHE = "DictCache";

    /**
     * 实名次数
     */
    public final static String NAMEAUTH_VERIFY="NAMEAUTH_VERIFY_";
    /**
     * 绑卡次数
     */
    public final static String BINDCARD_VERIFY="BINDCARD_VERIFY";
    /**
     * PC活期产品
     */
    public final static String PC_SXT="PC_SXT";



}
