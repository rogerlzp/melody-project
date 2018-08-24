package com.melody.common.sms;

import com.melody.common.sms.esms.EsmsImpl;
import com.melody.common.sms.tong.TongImpl;
import com.melody.common.sms.yunpian.YunpianImpl;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by liuyw on 2015/11/30.
 */
public class SMSManager {

    public static final String DHST = "DHST";// 大汉三通！
    public static final String XWKJ = "XWKJ";// 玄武科技！

    //  public static final String CL = "CL";// 创蓝科技！

    public static final String YP = "YP";// 云片科技！


    public static void doSendSms(String mobileNo, String messageData, String type) throws Exception {
        // TODO: 2016/1/4 发送短信的时候首先取模. 

        if (StringUtils.equals(YP, type)) {
            //boolean isSend=EsmsImpl.getInstance().doSendSms(mobileNo,messageData);
            //  boolean isSend=TongImpl.doSendSms(mobileNo,messageData);;
            boolean isSend = YunpianImpl.doSendSms(mobileNo, messageData);
            //TODO: 添加其他接口
        }
//        } else if (StringUtils.equals(CL, type)) {
//            //boolean isSend=EsmsImpl.getInstance().doSendSms(mobileNo,messageData);
//            //  boolean isSend=TongImpl.doSendSms(mobileNo,messageData);;
//            boolean isSend = ChuangImpl.doSendSms(mobileNo, messageData);
//        }
        else if (StringUtils.equals(DHST, type)) {
            //boolean isSend=EsmsImpl.getInstance().doSendSms(mobileNo,messageData);
            boolean isSend = TongImpl.doSendSms(mobileNo, messageData);
            ;
            //优先使用玄武科技
            if (!isSend) {
                //再用大汉三通发送
                // TongImpl.doSendSms(mobileNo,messageData);
                EsmsImpl.getInstance().doSendSms(mobileNo, messageData);
            }
        } else {
            boolean isSend = EsmsImpl.getInstance().doSendSms(mobileNo, messageData);
            //优先使用玄武科技
            if (!isSend) {
                //再用大汉三通发送
                TongImpl.doSendSms(mobileNo, messageData);
            }
        }


    }

    public static void main(String[] agrs) throws Exception {


     //   for (int i = 0; i < 10; i++) {
            SMSManager.doSendSms("15800911536", " 【神马贷款】您的验证码是1234 " , YP);
        //    SMSManager.doSendSms("15268110219", "XWKJ" + i, XWKJ);
       // }

    }

}
