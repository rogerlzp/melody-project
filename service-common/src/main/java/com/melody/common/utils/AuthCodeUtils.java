package com.melody.common.utils;

import java.util.Random;

/**
 * Created by liuyw on 2015/11/19.
 */
public class AuthCodeUtils {

    protected   static int codeLengths=4;//验证码长度
    protected   static String codeType = "N";//验证码类型
    /**
     * 生成验证码
     *
     * @return 验证码
     */
    public static String createAuthCode() {
        if ("NL".equals(codeType)) { // 数字中文
            return createMixAuthCode(true);
        } else if ("L".equals(codeType)) { // 字母
            return createNoramlAuthCode(true, 1);
        } else { // 数字
            return createNoramlAuthCode(true, 0);
        }
    }


    /**
     * 生成数字字符混合验证码
     *
     * @param upperCase true-需要大写
     * @return 验证码
     */
    protected static String createMixAuthCode(boolean upperCase) {
          Random random = new Random();
        int codeLength = codeLengths;
        int lastType = 0;// 记住上一个字符是什么类型
        int type = 0;
        StringBuilder builder = new StringBuilder();

        while(builder.length() < codeLength) {
            type = random.nextInt(2);
            if (lastType != type && builder.length() > 0) {
                // 确定了验证码里肯定有不同类型的字符了
                lastType = -1;
            }
            if (lastType != -1) {
                // 如果还是一样，则记录上一个类型
                lastType = type;
            }
            if (lastType != -1 && builder.length() == codeLength - 1) {
                // 最后一个字符了，仍然发现生成的类型都一样，则继续取类型，直到取一个不同的类型为止
                while (lastType == type) {
                    type = random.nextInt(2);
                }
            }
            addAuthCodeChar(builder, type);
        }
        return upperCase ? builder.toString().toUpperCase() : builder.toString();
    }

    /**
     * 生成单一验证码
     *
     * @param upperCase 需要大写
     * @param type 验证码类型：1-字母，0-数字
     * @return 验证码
     */
    protected static String createNoramlAuthCode(boolean upperCase, int type) {
        int codeLength = codeLengths;
        StringBuilder builder = new StringBuilder();
        while(builder.length() < codeLength) {
            addAuthCodeChar(builder, type);
        }
        return upperCase ? builder.toString().toUpperCase() : builder.toString();
    }

    /**
     * 根据类型增加验证码
     *
     * @param builder 防止验证码的StringBuilder
     * @param type  验证码类型：1-字母，0-数字
     */
    protected static void addAuthCodeChar(StringBuilder builder, int type) {
        Random random = new Random();
        if (type == 0) {
            int j = random.nextInt(10);
            if(j != 0) { // 从用户体验角度，0和O无法区分，所以去掉
                builder.append(j);
            }
        } else {
            int index = random.nextInt(26);
            char ch = (char) ('a' + index);
            if(ch != 'o' && ch != 'O' && ch != '0') {  // 从用户体验角度，0和O无法区分，所以去掉
                builder.append(ch);
            }
        }
    }

    public static void main(String args[]){
        System.err.println(AuthCodeUtils.createAuthCode());

    }



}
