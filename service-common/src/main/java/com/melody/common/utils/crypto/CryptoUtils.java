package com.melody.common.utils.crypto;


import com.melody.common.utils.rfc.Rfc2898DeriveBytes;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

public class CryptoUtils {

    //以下设置是为了兼容C#的HashPassword算法
    private static int saltSize = 0x10;
    private static int iterations = 0x3e8;
    private static int subKeySize = 0x20;
    private static String SYSTEM_SALT = "com.iminling";
    private static Random random = new Random();
    private static final String ENCODING = "UTF-8";

    /**
     * 获取 Salt
     * Generate random Salt
     * @return
     */
    public static String getSalt() {
        byte[] salt = randomSalt(saltSize);
        return Base64.encodeBase64String(salt);
    }

    public static byte[] randomSalt(int size) {
        byte[] salt = new byte[size];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * 密码MD5加密方法
     * @param password
     * @return
     */
    public static String passwordMD5(String password) {
        return MD5(password + SYSTEM_SALT);
    }

    /**
     * 普通MD5加密方法
     * @param data
     * @return
     */
    public static String MD5(String data) {
        try {
            byte[] bytes = data.getBytes(ENCODING);
            String result = DigestUtils.md5Hex(bytes);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verifyPassword(String hashPassword, String password){
        return hashPassword.equals(passwordMD5(password));
    }

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) {
        try {
            byte[] data = encryptKey.getBytes(ENCODING);
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance("HmacSHA1");
            //用给定密钥初始化 Mac 对象
            mac.init(secretKey);
            byte[] result = mac.doFinal(encryptText.getBytes(ENCODING));
            //完成 Mac 操作
            //return Hex.encodeHexString(result);
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取hash后的密码
     *
     * @param password
     * @param salt
     * @return
     */
    public static String getHash(String password, String salt) {
        Rfc2898DeriveBytes keyGenerator = null;
        try {
            keyGenerator = new Rfc2898DeriveBytes(password, saltSize, iterations);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        byte[] subKey = keyGenerator.getBytes(subKeySize);
        byte[] bSalt = keyGenerator.getSalt();
        byte[] hashPassword = new byte[1 + saltSize + subKeySize];
        System.arraycopy(bSalt, 0, hashPassword, 1, saltSize);
        System.arraycopy(subKey, 0, hashPassword, saltSize + 1, subKeySize);
        return Base64.encodeBase64String(hashPassword);
    }

    /**
     * 验证密码
     *
     * @param hashedPassword
     * @param password
     * @param salt
     * @return
     */
    public static boolean verify(String hashedPassword, String password, String salt) {
        byte[] hashedPasswordBytes = Base64.decodeBase64(hashedPassword);
        if (hashedPasswordBytes.length != (1 + saltSize + subKeySize) || hashedPasswordBytes[0] != 0x00) {
            return false;
        }

        byte[] bSalt = new byte[saltSize];
        System.arraycopy(hashedPasswordBytes, 1, bSalt, 0, saltSize);
        byte[] storedSubkey = new byte[subKeySize];
        System.arraycopy(hashedPasswordBytes, 1 + saltSize, storedSubkey, 0, subKeySize);
        Rfc2898DeriveBytes deriveBytes = null;
        try {
            deriveBytes = new Rfc2898DeriveBytes(password, bSalt, iterations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] generatedSubkey = deriveBytes.getBytes(subKeySize);
        return byteArraysEqual(storedSubkey, generatedSubkey);
    }

    private static boolean byteArraysEqual(byte[] storedSubkey, byte[] generatedSubkey) {
        int size = storedSubkey.length;
        if (size != generatedSubkey.length) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (storedSubkey[i] != generatedSubkey[i]) {
                return false;
            }
        }
        return true;
    }


    public static String decriptor(MessageDigest digest, String decript) {
        digest.update(decript.getBytes());
        byte messageDigest[] = digest.digest();
        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString();
    }


    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA-1");
            return decriptor(digest, decript);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String SHA(String decript) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("SHA");
            return decriptor(digest, decript);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }



    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static byte[] encryptAES(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decryptAES(byte[] content, String password) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //产生一个有限时间稳定的密码
    //需要优化
    public static String genTemporaryPassword(String salt) {
        Integer salt1 = (int) (Calendar.getInstance().getTimeInMillis() / 1000) / 100 % 1000000;
        String salt2 = "" + salt.charAt(2) + salt.charAt(1) + salt.charAt(4) + salt.charAt(3) + salt.charAt(5) + salt.charAt(7);
        Integer salt3 = Integer.parseInt(salt2);
        Integer salt4 = salt1 + salt3;
        return salt4.toString();
    }


    /**
     * 有序MD5签名算法
     * 用途:微信支付使用该签名生成sign
     *
     * @param parameters
     * @return
     */
    public static String wxPaySortedMD5Sign(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        //所有参与传参的参数按照accsii排序（升序）
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        String key = parameters.get("key").toString();
        sb.append("key=" + key);
        String sign = CryptoUtils.MD5(sb.toString()).toUpperCase();
        return sign;
    }

}
