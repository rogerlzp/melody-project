package com.melody.common.sms.chuanglan;

import com.melody.utils.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by zhengpingli on 2017/3/16.
 */
public class ChuangImpl {

    private static final Logger log = LoggerFactory.getLogger(ChuangImpl.class);


    private static String url = "http://sms.253.com/msg/send";// 应用地址
    private static String un = "N8398193";// 账号
    private static String pw = "BgJTbalkSo2126";// 密码
    // private static String phone = "15800911536";// 手机号码，多个号码使用","分割
    //  private static String msg = "【253云通讯】您好，你的验证码是123456";// 短信内容
    private static String rd = "1";// 是否需要状态报告，需要1，不需要0
    private static String ex = "";// 扩展码

    public static boolean doSendSms(String mobileNo, String messageData) {

        HashMap<String, Object> valuePairs = new HashMap<>();
        valuePairs.put("un", un);
        valuePairs.put("pw", pw);
        valuePairs.put("phone", mobileNo);
        valuePairs.put("rd", rd);
        try {
            String message = URLEncoder.encode(messageData, "UTF-8");
            valuePairs.put("msg", message);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }        //   valuePairs.put("ex", ex);


        // http://sms.253.com/msg/send?msg=您的验证码为：2278, 请及时输入，五分钟后失效。&rd=1&phone=15800911536&pw=BgJTbalkSo2126&un=N8398193


        String sendRes = HttpUtil.doGet(url, valuePairs);


        if (log.isDebugEnabled()) {
            log.debug("sendRes==========>>" + sendRes.toString());
        }


        if (StringUtils.equals("0", StringUtils.substringAfter(sendRes, ","))) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }

    }

}
