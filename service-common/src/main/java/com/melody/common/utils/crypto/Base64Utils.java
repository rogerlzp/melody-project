package com.melody.common.utils.crypto;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Utils {

    /**
     * 获取安全的url编码
     * @param url url
     * @return
     */
    public static String safeUrlEncode(String url){
        try {
            byte[] result = url.getBytes("UTF-8");
            return safeUrlEncode(result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取安全的url编码
     * @param data  url二进制数据
     * @return
     */
    public static String safeUrlEncode(byte[] data){
        String base64 = Base64.encodeBase64String(data);
        String urlSafeStr = base64.replace('+', '-');
        urlSafeStr = urlSafeStr.replace('/', '_');
        //urlSafeStr = urlSafeStr.replaceAll("=", "");
        return urlSafeStr;
    }

    /**
     * 解码安全的url
     * @param safeBase64Str
     * @return
     */
    public static String safeUrlDecode(final String safeBase64Str) {
        String base64Str = safeBase64Str.replace('-', '+');
        base64Str = base64Str.replace('_', '/');
        /*int mod4 = base64Str.length() % 4;
        if(mod4 > 0){
            base64Str = base64Str + "====".substring(mod4);
        }*/
        byte[] str = Base64.decodeBase64(base64Str);
        return new String(str);
    }

    /**
     * base64编码文件转二进制数据
     * @param base64Data
     * @return
     */
    public static byte[] base64FileToByte(String base64Data) {
        if (base64Data != null && (!base64Data.equals("undefined"))) {
            String base64Encode = base64Data.substring(base64Data.indexOf(',') + 1);
            if (base64Encode != null || base64Encode != "") {
                if (Base64.isBase64(base64Encode) == true) {
                    byte[] imageByte = Base64.decodeBase64(base64Encode);
                    return imageByte;
                }
            }
        }
        return null;
    }

}
