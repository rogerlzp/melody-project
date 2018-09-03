package com.melody.common.utils;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author konghang
 */
public class StringUtils {

    //顺序号产生器(当前设计按每秒1000单不重设计)
    private static Integer SEQ_NUM_MIN = 1000;
    private static Integer SEQ_NUM_MAX = 1000;



    /**
     * 随机产生一定长度的字符串,16位
     * @return
     */
    public static String generateRandomString(){
        return generateRandomString(16);
    }

    /**
     * 生成一定长度的字符串
     * @param length 生成字符串长度
     * @return
     */
    public static String generateRandomString(int length){
        String seekChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int seekLength = seekChars.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(seekChars.charAt((int) (Math.random() * seekLength)));
        }
        return sb.toString();
    }

    /**
     * 生成随机数字符串,默认6位
     * @return
     */
    public static String generateRandomNumber() {
        return generateRandomNumber(6);
    }

    /**
     * 生成随机数字符串
     * @param length 生成随机数的长度
     * @return
     */
    public static String generateRandomNumber(int length) {
        String seekChars = "0123456789";
        int seekLength = seekChars.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(seekChars.charAt((int) (Math.random() * seekLength)));
        }
        return sb.toString();
    }

    /**
     * 某个字符串按某个字符截取,默认字符","
     * @param str 字符串
     * @return
     */
    public static List<String> tokenizer(String str) {
        return tokenizer(str, ",");
    }

    /**
     * 某个字符串按某个字符截取
     * @param str   字符串
     * @param delim 字符
     * @return
     */
    public static List<String> tokenizer(String str, String delim) {
        if (str == null) {
            return null;
        }
        List<String> tokens = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str, delim);
        while (st.hasMoreElements()) {
            tokens.add(st.nextToken());
        }
        return tokens;
    }


    /**
     * 订单号产生器
     * @return
     */
    public static String generateOrderNumber() {
        Date now = new Date(DateUtils2.getUnixStamp());
        if (SEQ_NUM_MIN > SEQ_NUM_MAX) {
            SEQ_NUM_MIN = 1000;
        }
        SEQ_NUM_MIN++;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String outTradeNo = dateFormat.format(now) + SEQ_NUM_MIN.toString();
        return outTradeNo;
    }

    /**
     * 生成一个小于max随机数
     * @param max
     * @return
     */
    public static Integer generateRandomNum(int max){
        int num=(int)(Math.random() * max);
        return num;
    }

    /**
     * 获得UUID
     * @return
     */
    public static String getUUID() {
        String UUID = java.util.UUID.randomUUID().toString();
        return UUID.replaceAll("-", "");
    }

    /**
     * 非空判断
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

}
