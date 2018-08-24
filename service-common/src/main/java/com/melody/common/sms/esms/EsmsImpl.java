package com.melody.common.sms.esms;

//import com.esms.MessageData;
//import com.esms.PostMsg;
//import com.esms.common.entity.Account;
//import com.esms.common.entity.GsmsResponse;
//import com.esms.common.entity.MTPack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liuyw on 2015/11/30.
 * 玄武科技实现类
 */
public class EsmsImpl {
    private static final Logger log = LoggerFactory.getLogger(EsmsImpl.class);

    /*
    private static Account account=new Account("wyhlw@wyhlw", ";)Ws0!h9*");
    private static PostMsg postMsg;
    */

    private static EsmsImpl instance;
    private EsmsImpl (){}
    public static synchronized EsmsImpl getInstance() {
        if (instance == null) {
         /*   postMsg = new PostMsg();
            postMsg.getCmHost().setHost("211.147.239.62", 9080);//设置网关的IP和port，用于发送信息
            postMsg.getWsHost().setHost("211.147.239.62", 9070);//设置网关的 IP和port，用于获取账号信息、上行、状态报告等等
            */

            instance = new EsmsImpl();
        }
        return instance;
    }

    @SuppressWarnings("static-access")
	public static void main(String[] args){
        try {
            EsmsImpl.getInstance().doSendSms("13764545612","[test]");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 短信下发范例
     */
    public static boolean doSendSms(String mobileNo,String messageData) throws Exception{
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
        return true;

    }




}
