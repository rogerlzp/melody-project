package com.melody.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by liuyw on 2015/11/19.
 */
public class TokenUtils {

    private static TokenUtils instance = new TokenUtils();

    private long previous;

    protected TokenUtils() {
    }

    public static TokenUtils getInstance() {
        return instance;
    }

    public synchronized String generateToken(String msg) {
        try {
            long current = System.currentTimeMillis();
            if (current == previous)                current++;
            previous = current;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());

            byte now[] = (new Long(current)).toString().getBytes();
            md.update(now);
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }


    /**
     * md5加密
     * @param msg 原字符
     * @return 加密后的字符
     */
    public  String MD5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }

        return sb.toString();
    }

    public static void main(String args[]){
        String token = TokenUtils.getInstance().MD5("123123");
        System.err.println(token);

    }
}

