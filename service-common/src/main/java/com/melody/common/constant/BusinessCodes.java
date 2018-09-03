package com.melody.common.constant;

/**
 * Created by liuyw on 2015/11/19.
 */
public class BusinessCodes {
    /**
     * 图形 or 手机验证码的万能密码
     */
    public final static String CHECKCODE = "8888";
    /**
     * 成功
     */
    public final static String SUCCESS = "0";

    public final static String ERROR = "1";

    /**
     * 联动优势成功代码
     */
    public final static String UMPAY_SUCCESS = "0000";

    /************** account *************************/

    public final static String ACCOUNT_VERIFYCODE = "10000001";// 图形验证码校验失败
    public final static String ACCOUNT_MOBILECODE = "10000002";// 手机验证码校验失败
    public final static String ACCOUNT_MOBILENO = "10000003";// 手机号码 错误
    public final static String ACCOUNT_PASSWORD = "10000004";// 原密码不对
    public final static String ACCOUNT_SESSIONKEY_ERROR = "10000005";// 没找到SESSIONKEY
    public final static String ACCOUNT_SESSIONKEY_TIMEOUT = "10000006";// 登录超时，没找到找个人
    public final static String ACCOUNT_READANDAGREE = "10000007";// 没选择已阅读按钮
    public static final String ACCOUNT_PASSWORD_REG = "10000008";// 密码校验有问题
    public static final String ACCOUNT_MOBILENO_REG = "10000009";// 手机号码校验有问题
    public static final String ACCOUNT_MOBILECODE_SEDNTYPE = "10000010";// 发送手机验证码错误
    public static final String ACCOUNT_REGOVERLOAD = "10000011";// 该用户已经注册
    public static final String ACCOUNT_NOREGOVERLOAD = "10000012";// 该用户未已经注册
    public static final String ACCOUNT_RETRIEVEUSERPWDERROR = "10000013";// 重置密码出错，请重新再试！
    public static final String ACCOUNT_IS_NULL = "10000014";// 查询账户信息出错！
    public static final String ACCOUNT_IS_COUPON = "10000015";// 查询返现券信息出错！
    public static final String ACCOUNT_UPDATEUSER_ERROR = "10000016";// 更新用户信息失败！
    public static final String ACCOUNT_IDCARD_ERROR = "10000017";// 身份证号码校验失败！
    public static final String ACCOUNT_NAMEAUTH_ERROR = "10000018";// 已经实名认证！
    public static final String ACCOUNT_NAMEAUTH_FIRST = "10000019";// 请先进行实名认证！
    public static final String ACCOUNT_GATEWAY_VERIFYERROR = "10000020";// 联动优势验签失败！
    public static final String ACCOUNT_BANKAUTH_REPEAT = "10000021";// 已经绑定过银行卡！
    public static final String MYINVESTMENT_IS_NULL = "10000022";// 查询账户信息出错！
    public static final String FINANCIALCOUPON_IS_NULL = "10000023";// 查询账户信息出错！
    public static final String ACCOUNT_INSTRT_WATERCOURSE_ERROR = "10000024";// 插入账户流水失败！
    public static final String ACCOUNT_FINDUSER_ERROR = "10000025";// 没有找到该用户！
    public static final String ACCOUNT_TOTALASSET_LOW = "10000026";// 余额不足，请充值！
    public static final String ACCOUNT_IDENTITYCODE_EXIST = "10000027";// 该身份证已经绑定
    public static final String ACCOUNT_BANKAUTH_FIRST = "10000028";// 请先绑定银行卡！
    // 金蛋
    public static final String GOLDEN_EGG_SUBMIT_ERROR = "10000030";
    public static final String GOLDEN_EGG_BONUS_UPDATE_ERROR = "10000029";
    public static final String ACCOUNT_APPVERSION_ERROR = "10000031";// 输入版本号不存在
    public static final String ACCOUNT_LASTVERSION_NULL = "10000032";// 最新版本不存在
    public static final String ACCOUNT_BIRDCOIN_NOMONEY = "10000033";// 鸟币余额不足,请重新选择手续费承担方!
    public static final String ACCOUNT_NAMEAUTH_NONE = "10000034";// 推荐人未进行实名认证
    public static final String ACCOUNT_PARTNER_NOSELF = "10000035";// 推荐人不能为本人
    public static final String ACCOUNT_SMS_ERROR = "10000036";// 发送短信失败
    public static final String ACCOUNT_PARTNER_NOEXSIT = "10000037";// 推荐人不存在
    public static final String ACCOUNT_PARTNER_AREADYEXSIT = "10000038";// 推荐人已存在
    public static final String ACCOUNT_PARTNER_FATHER_SELF = "10000039";// 你填写的号码推荐人是本人
    public static final String ACCOUNT_ORDERAMOUNT_MORETHAN = "10000040";// 超出单笔金额
    public final static String ACCOUNT_MOBILECODE_INVALID = "10000041";// 手机验证码已失效，请重新获取!
    public static final String ACCOUNT_ORDERAMOUNT_DAILY = "10000042";// 超出单日金额
    public static final String ACCOUNT_BELONGBANK_ISNULL = "10000043";// 请选择银行名称!
    public static final String ACCOUNT_CARDID_ISNULL = "10000044";// 请输入银行卡号!
    public static final String ACCOUNT_USER_IS_STAFF = "10000045";// 内部员工
    public static final String ACCOUNT_IMPORT_OFFLINE_ORDER_ISNULL = "10000046";// 线下数据导入出错 importOfflineOrder
    public static final String ACCOUNT_USER_WATERCOURSE_ISERROR = "10000047";// 实名认证插入流水失败

    /************** product(产品) *************************/
    public static final String PRODUCT_REPEAT_EXPERIENCE = "20000001";// 您已经使用过体验金券了！
    public static final String PRODUCT_USABLE_BALANCE = "20000002";// 订单金额大于可用余额!请充值！
    public static final String PRODUCT_MAX_BIRDCOIN = "20000003";// 鸟币不满足1：5的使用规范!
    public static final String PRODUCT_BUY_ERROR = "20000004";// 下单失败!
    public static final String PRODUCT_ORDER_ERROR_BIRD_COIN_PROCESS_FAILURE = "20000005";// 鸟币处理失败!
    public static final String PRODUCT_ORDER_ERROR_UPDATE_ACCOUNT_USABLE_BALANCE_FAILURE = "20000006";// 更新账户可用余额失败!
    public static final String PRODUCT_ORDER_ERROR_ORDER_AMOUNT_LESS_THAN_COUPON_LIMIT_AMOUNT = "20000007";// 订单金额小于返现券限定金额
    public static final String PRODUCT_ORDER_ERROR_ORDER_DURATION_LESS_THAN_COUPON_LIMIT_DURATION = "20000008";// 订单标的时常小于返现券最小时长
    public static final String PRODUCT_ORDER_ERROR_COUPON_STATUS_ERROR = "20000009";// 返现券状态错误
    public static final String PRODUCT_ORDER_ERROR_ORDER_USER_AND_COUPON_USER_MISMATCHING_ERROR = "200000010";// 订单用户和返现券用户不匹配
    public static final String PRODUCT_ORDER_ERROR_UPDATE_COUPON_FAILRE = "20000011";// 订单用户和返现券用户不匹配
    public static final String PRODUCT_ORDER_ERROR_ORDER_AMOUNT_LESS_THAN_PRODUCT_STA_INVEST_AMOUNT = "200000012";// 订单标的时常小于标的起投金额！
    public static final String PRODUCT_ORDER_ERROR_ORDER_AMOUNT_SHOULD_BE_MULTIPLE_OF_PRODUCT_STA_INVEST_AMOUNT = "200000013";// 订单金额应该为起投金额的倍数
    public static final String PRODUCT_ORDER_ERROR_ORDER_COUPON_NOT_EXISTS = "200000014";// 返现券不存在
    public static final String PRODUCT_ORDER_ERROR_PRODUCT_STATUS_ERROR = "200000015";// 产品状态错误
    public static final String PRODUCT_ORDER_ERROR_ORDER_AMOUNT_GREATER_THAN_PRODUCT_REMAIN_AMOUNT = "200000016";// 订单金额大于产品剩余可投金额
    public static final String PRODUCT_ORDER_ERROR_UPDATE_PRODUCT_INFO_FAILRE = "200000017";// 更新产品信息失败
    public static final String PRODUCT_ORDER_ERROR_PRODUCT_RAISE_DATE_IS_OVER = "200000018";// 产品募集日期已结束
    public static final String PRODUCT_BALANCEDETAILLIST_ERROR = "200000019";// 余额明细列表查询失败
    public static final String PRODUCT_IS_NOT_EXITS = "200000020";// 该标的不存在
    public static final String PRODUCT_ORDER_ERROR_ORDER_AMOUNT_ERROR = "200000021";// 订单金额不能小于10000元
    public static final String PRODUCT_ORDER_ERROR_PRODUCT_REMAIN_AMOUNT_LESS_THAN_ORDER_AMOUNT = "200000022"; // 产品剩余金额小于订单金额
    public static final String PRODUCT_CURRENT_ISNULL = "200000023"; // 没有找到相应的订单

    public static final String PRODUCT_ORDER_ERROR_REPEAT_ORDER_XSB = "200000024"; // 已经购买过新手单了
    public static final String PRODUCT_ORDER_ERROR_USER_NO_MEET_XSB_REQUIREMENT = "200000025"; // 用户不符合新手标要求

    public static final String PRODUCT_ORDER_ERROR_XSB_NOT_ALLOW_USE_COUPON = "200000026"; // 新手标不允许使用返现券
    public static final String PRODUCT_ORDER_ERROR_XSB_NOT_ALLOW_USE_BIRD_COIN = "200000027"; // 新手标不允许使用鸟币

    public static final String PRODUCT_ORDER_NO_PWD_PAY_FAILURE = "200000028"; // 免密支付失败
    public static final String PRODUCT_ORDER_ERROR_XSB_NOT_MORE_THAN_10000_YUAN = "200000029"; // 新手标投资金额不能大于10000元
    public static final String PRODUCT_ORDER_ERROR_XSB_NOT_MORE_THAN_200000_YUAN = "200000031"; // 新手标投资金额不能大于200000元

    public static final String PRODUCT_ORDER_ERROR_ORDER_AMOUNT_ERROR_FORMATE = "200000030"; // 输入的金额格式不正确或金额为0，请再次输入！

    public static final String CURRENT_PRODUCT_ORDER_UPDATE_CURRENT_PRODUCT_FAILURE = "201000000";// 更新活期产品账户失败
    public static final String CURRENT_PRODUCT_ORDER_OVER_CURRENT_DAY_LIMIT_AMOUNT = "201000001";// 超过今日限额
    public static final String CURRENT_PRODUCT_ORDER_UPDATE_USER_CURRENT_BALANCE_FAILURE = "201000002"; // 更新用户活期账户失败
    public static final String CURRENT_PRODUCT_ORDER_EXTRACT_CURRENT_BALANCE_LESS_THAN_EXTRACT_AMOUNT = "201000003"; // 活期账户余额小于提取金额
    public static final String CURRENT_PRODUCT_ORDER_SAVE_ORDER_FAILURE = "201000004"; // 保存订单失败
    public static final String CURRENT_PRODUCT_ORDER_ORDER_AMOUNT_FAILURE = "201000005"; // 订单金额错误

    /************** pc(官网) *************************/
    public static final String IDENTITYCODE_ERROR = "500000001"; // 身份证格式不正确
    public static final String GATEWAY_NAMEAUTH_MAX = "500000002"; // 超出每日可用实名认证次数
    public static final String GATEWAY_BINDCARD_MAX = "500000002"; // 超出每日可用绑卡认证次数


    /************** gateway(网关) *************************/
    public static final String GATEWAY_HTML_ERROR = "30000001";// 获取响应数据失败.原因为
    public static final String GATEWAY_HTML_NULL = "30000002";// 联动优势返回空的html代码
    public static final String GATEWAY_RESPCODEERROR = "30000002";// 响应码错误
    public static final String GATEWAY_RESPCODSUCCESS = "30000003";
    public static final String GATEWAY_SMS_MAX = "30000004";// 已超过当天发送短信总条数！

    public static final String GATEWAY_PUSH_CONNECTION_FAILURE = "40000001";// 推送连接失败！
    public static final String GATEWAY_PUSH_REQUEST_FAILURE = "40000002";// 推送请求失败！
    public static final String GATEWAY_PUSH_USER_ID_IS_NULL = "40000003";// 用户ID为空！


    /***************预订 *************/

    public static final String RESERVE_ERROR = "90000001";// 预订失败
    public static final String RESERVE_PRODUCT_ERROR = "90000002";// 预订单个服务失败

    /*************** 车辆 *************/
    public static final String CAR_ERROR = "90000003";// 添加车辆失败


    /*************** 家庭作业 *************/
    public static final String ACTIVE_HOMEWORK_NOT_EXIST = "60000001";// 没有未完成的作业
    public static final String HOMEWORK_USER_STORY_SCORE = "60000002";// 创建用户家庭作业绘本成绩表失败
    public static final String HOMEWORK_USER_STORY_PAGE_SCORE = "60000003";// 创建用户家庭作业绘本单页成绩失败
    public static final String HOMEWORK_USER_STORY_UPDATE_ERROR = "60000004";// 更新用户家庭作业绘本失败

    public static final String HOMEWORK_USER_QUESTION_ERROR = "60000005";// 更新用户家庭作业问题失败
    public static final String TR_WORD_USER_SCORE = "60000006";//添加单词失败


    /************ ALL TAG ID ******/
    public static final int TAG_ALL = 500006;// TAG ALL list


}
