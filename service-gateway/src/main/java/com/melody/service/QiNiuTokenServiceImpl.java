package com.melody.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.melody.common.constant.BusinessCodes;
import com.melody.gateway.api.QiNiuTokenService;
import com.melody.gateway.dto.QiNiuTokenEnter;
import com.melody.gateway.dto.QiNiuTokenResult;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;


/**
 * Created by zhengpingli on 2017/4/11.
 */

@Service(group = "qiNiuTokenService")
public class QiNiuTokenServiceImpl implements QiNiuTokenService {

    final String accessKey = "jDkoPQq2_SUGGlgvvoIvu9GRtEg9w9STodBPOPrT"; //个人账户
    //    final String accessKey =  "S7g5MyQgVs4NWS24VQlpIIF41d1vUSbFzR8cm0Vh"; //公司账号
    final String secretKey = "VTh1ELUoTDuK_2DT6wwXH8D9w3e4qb59cM6tSVOt"; //个人账号
//    final String secretKey = "G83_5K2Bg3VeKk3_LuLOOTxhIdSuLu9WuHVuuYY-";  //公司账号

    //  String bucket = "washcar";
    String bucket = "newegg";


    @Override
    public QiNiuTokenResult getQiNiuToken(QiNiuTokenEnter qiNiuTokenEnter) {

        QiNiuTokenResult qiNiuTokenResult = new QiNiuTokenResult();
        if (!StringUtils.isNullOrEmpty(qiNiuTokenEnter.getSpaceName())) {
            bucket = qiNiuTokenEnter.getSpaceName();
        }

        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket);
        //  String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        if (!StringUtils.isNullOrEmpty(upToken)) {

            qiNiuTokenResult.setUploadToken(upToken);
        } else {
            qiNiuTokenResult.setUploadToken("invalid upToken");
        }
        qiNiuTokenResult.setCode(BusinessCodes.SUCCESS);
        return qiNiuTokenResult;

    }

    public static void main(String[] args) {
        QiNiuTokenResult qiNiuTokenResult = new QiNiuTokenResult();
        final String accessKey = "S7g5MyQgVs4NWS24VQlpIIF41d1vUSbFzR8cm0Vh";
        final String secretKey = "G83_5K2Bg3VeKk3_LuLOOTxhIdSuLu9WuHVuuYY-";
        String bucket = "radio";
        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket);
        //  String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        if (!StringUtils.isNullOrEmpty(upToken)) {

            qiNiuTokenResult.setUploadToken(upToken);
        } else {
            qiNiuTokenResult.setUploadToken("invalid upToken");
        }
    }
}
