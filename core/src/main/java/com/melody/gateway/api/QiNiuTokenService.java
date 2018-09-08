package com.melody.gateway.api;

import com.melody.gateway.dto.QiNiuTokenEnter;
import com.melody.gateway.dto.QiNiuTokenResult;

public interface QiNiuTokenService {
    QiNiuTokenResult getQiNiuToken(QiNiuTokenEnter var1);
}
