package com.example.jwtlearn.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

/**
 * @author zhanglei
 * @date 2021/11/15 22:55
 */
public class JWTService {

    public static void main(String[] args) {

        JWTService jwtService = new JWTService();
        jwtService.getToken();
        jwtService.decrypt();
    }

    public void getToken() {
        //获取日期
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 3600);

        //生成令牌
        String token = JWT.create()
                .withClaim("username", "张三")    //设置自定义用户名
                .withExpiresAt(instance.getTime())             //设置过期时间
                .sign(Algorithm.HMAC256("!Q2W#E$RW"));    //设置签名 保密 复杂

        //输出令牌
        System.out.println(token);
    }


    public void decrypt() {
        //创建验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!Q2W#E$RW")).build();

        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzY5OTQyODMsInVzZXJuYW1lIjoi5byg5LiJIn0.bPrTKoOZZjpoY1avk_tz4aoL-xWuVo2oJKfZ4CG_lYA");

        System.out.println(verify.getClaim("username"));
        System.out.println("过期时间：" + verify.getExpiresAt());
    }
}
