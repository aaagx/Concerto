package com.example.concerto.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.concerto.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 16:00 2021/4/25
 * @Version: 1.0$
 */
public class TokenUtils {
    //生成token
    public static String CreateToken(User user) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(user.getUserId()))
                .sign(Algorithm.HMAC256(user.getUserPassword()));
        return token;
    }

    //从token中获取用户id
    public static  int getIdInToken(String  token) throws  JWTDecodeException {
            System.out.println((JWT.decode(token).getAudience().get(0)));
            int userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
            return userId;
    }

    //验证token正确性
    public static boolean checkToken(String token,String password)throws  JWTVerificationException{
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(password)).build();
        jwtVerifier.verify(token);
        return true;

    }

}
