package com.melody.user.api;

import com.melody.user.dto.UserQueryResult;

public interface WxService {

    // 根据微信的结果，得到session, 该session 和用户登录的session 一样。
    UserQueryResult create3rdSession(String wxOpenId, String wxSessionKey, Long expires);

}
