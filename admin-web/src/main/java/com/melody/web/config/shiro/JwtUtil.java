package com.melody.web.config.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.melody.utils.DateUtils;


import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * jwt工具类
 * @author konghang
 */
public class JwtUtil {

    // 过期时间
    private static final long EXPIRE_TIME = 5 * 24 * 60 * 60 * 1000;
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK32234545fdf>?N<:{LWPW";


    /**
     * 生成签名,以及设置过期时间
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String username,String salt) {
        try {
            Date date = new Date(DateUtils.getUnixStamp() * 1000 + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    .withClaim("salt", salt)
                    .withIssuedAt(new Date())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验token是否正确
     * @param token
     * @param username 用户名
     * @return 是否正确
     */
    public static boolean verifyToken(String token, String username, String salt) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim("username", username)
                .withClaim("salt", salt)
                .build();
        verifier.verify(token);
        return true;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
