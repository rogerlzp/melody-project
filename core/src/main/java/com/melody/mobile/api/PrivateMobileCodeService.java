package com.melody.mobile.api;

import com.melody.mobile.dto.MobileCodeResult;

public interface PrivateMobileCodeService {


    /**
     * 发送短信
     *
     * @param mobile      手机号码
     * @param messageData 内容
     * @param type        发送方式
     * @return
     */
    public MobileCodeResult sendMobileCode(String mobile, String messageData, String type);

    /**
     * 发送短信
     *
     * @param mobile      手机号码
     * @param messageData 内容
     * @param type        发送方式
     * @return
     */
    public MobileCodeResult sendMobileCode(String[] mobile, String messageData, String type);
}
