package com.example.jwtlearn.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author zhanglei
 * @date 2021/11/15 23:43
 */
public class JWTUtil {

    private static String TOKEN = "token!leileilll";

    /**
     * 生成token
     * @param map
     * @return
     */
    public static String getToken(Map<String,String> map) {
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 3600);
        return builder.sign(Algorithm.HMAC256(TOKEN));
    }

    /**
     * 验证token
     * @param token
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }

    /**
     * 获取token中payload
     * @param token
     * @return
     */
    public static DecodedJWT getToken(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
}
