package com.melody.common.sms.yunpian;

import com.alibaba.fastjson.JSONObject;
import com.wy.common.utils.AuthCodeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengpingli on 2017/6/26.
 */
public class YunpianImpl {

    private static final Logger log = LoggerFactory.getLogger(YunpianImpl.class);

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    private static String apikey = "012db32acd79936f515b7783ee7563e7";
    //查账户信息的http地址
    private static String URI_GET_USER_INFO = "https://sms.yunpian.com/v2/user/get.json";

    //智能匹配模版发送接口的http地址
    private static String URI_SEND_SMS = "https://sms.yunpian.com/v2/sms/single_send.json";

    //模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";

    //发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "https://voice.yunpian.com/v2/voice/send.json";

    /**
     * 智能匹配模版接口发短信
     *
     * @param apikey apikey
     * @param text   　短信内容
     * @param mobile 　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String sendSms(String apikey, String text, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }


    /**
     * 通过接口发送语音验证码
     *
     * @param apikey apikey
     * @param mobile 接收的手机号
     * @param code   验证码
     * @return
     */

    public static String sendVoice(String apikey, String mobile, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        params.put("code", code);
        return post(URI_SEND_VOICE, params);
    }

    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("message:" + responseText);
        return responseText;
    }


    public static boolean doSendSms(String mobileNo, String messageData) throws Exception {

        String mobile = URLEncoder.encode(mobileNo, ENCODING);
        String responseText = sendSms(apikey, messageData, mobile);

        log.info("doSendSms2017: " + responseText);

        return JSONObject.parseObject(responseText).getString("code").equals("0");


        // check responseText
        /*
        MTPack pack = new MTPack();
        pack.setBatchID(UUID.randomUUID());
        pack.setMsgType(MTPack.MsgType.SMS);
        pack.setBizType(0);
        pack.setDistinctFlag(false);
        ArrayList<MessageData> msgs = new ArrayList<MessageData>();
//		/** 单发，一号码一内容 */
        /*
        pack.setSendType(MTPack.SendType.MASS);
     	msgs.add(new MessageData(mobileNo, messageData));
        pack.setMsgs(msgs);

        GsmsResponse resp = postMsg.post(account, pack);

        if (log.isDebugEnabled()){
            log.debug("GsmsResponse==========>>"+resp.toString());
        }

        return resp.getResult()==0;
        */

    }

    public static void main(String[] args) throws Exception {
        //   String MESSAGE_DATA = "您的验证码为：%s, 请及时输入，五分钟后失效。";
        // String MESSAGE_DATA = "【云片网】您的验证码是%s";
         String MESSAGE_DATA = "【云片网】您的验证码是%s";
        String mobileCode = AuthCodeUtils.createAuthCode();
        System.out.println("mobileCode: " +mobileCode);
        System.out.println( String.format(MESSAGE_DATA, mobileCode));
        boolean a = doSendSms("15800911536", String.format(MESSAGE_DATA, mobileCode));


    }
}
