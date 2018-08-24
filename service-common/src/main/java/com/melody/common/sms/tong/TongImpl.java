package com.melody.common.sms.tong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.ctc.smscloud.json.JSONHttpClient;

/**
 * Created by liuyw on 2015/11/30.
 */
public class TongImpl {
    private static final Logger log = LoggerFactory.getLogger(TongImpl.class);

    private static String account = "dh28421";// 用户名（必填）
    private static String password = "*2eV~3lR";// 密码（必填）
    // private static String phone = "18516560317"; // 手机号码（必填,多条以英文逗号隔开）
    // private static String content = "修改签名后发送的短信。2222";// 短信内容（必填）
    public static String sign = "【领投鸟理财】"; // 短信签名（必填）
    public static String subcode = "85281234"; // 子号码（必填）
    public static String msgid = ""; // 短信id，查询短信状态报告时需要，（可选）
    public static String sendtime = ""; // 定时发送时间（可选）


    /**
     * 大汉三通发送短信的接口
     *
     * @param mobileNo    手机号码
     * @param messageData 短信内容
     * @return
     */
    public static boolean doSendSms(String mobileNo, String messageData) {
        /*
        JSONHttpClient jsonHttpClient = JSONHttpClient.getInstance("3tong.net");
        String sendRes = jsonHttpClient.sendSms(account, password, mobileNo, messageData, sign, subcode);
        List<Map<String, Object>> sendReslistMap = JSON.parseObject("["+sendRes+"]", new TypeReference<List<Map<String,Object>>>(){});
        String result= (String)sendReslistMap.get(0).get("result");
        String msgid= (String)sendReslistMap.get(0).get("msgid");

        if (log.isDebugEnabled()){
            log.debug("sendRes==========>>"+sendRes.toString());
        }

        if (StringUtils.equals("0",result)){
            String  reportRes=jsonHttpClient.getReport(account, password,msgid,mobileNo);
            List<Map<String, Object>> reportReslistMap = JSON.parseObject("["+reportRes+"]", new TypeReference<List<Map<String,Object>>>(){});
               String status=(String)reportReslistMap.get(0).get("result");
            if (!status.equals("0")){
                return Boolean.FALSE;
            }
        }else{
            return Boolean.FALSE;
        }
        */
        return Boolean.TRUE;
    }


    /**
     * 获取状态报告，请求无数据返回，客户端需休眠30秒再进行请求。
     *
     * @return
     */
    private static boolean getReport() {
        /*
        JSONHttpClient jsonHttpClient = JSONHttpClient.getInstance("wt.3tong.net");
        String reportRes = jsonHttpClient.getReport(    account, password);
        */
        return Boolean.FALSE;
    }


    /**
     * 获取短信余额
     *
     * @return
     */
    private static boolean getBalance() {
        /*
        JSONHttpClient jsonHttpClient = JSONHttpClient.getInstance("wt.3tong.net");
        String reportRes = jsonHttpClient.getBalance(    account, password);

*/

        return Boolean.FALSE;
    }
}
