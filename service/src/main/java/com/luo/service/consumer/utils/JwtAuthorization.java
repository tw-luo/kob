package com.luo.service.consumer.utils;

import com.luo.service.utils.JWT.JwtUtil;
import io.jsonwebtoken.Claims;

public class JwtAuthorization {
    public static int getUserId(String token){
        int userId = -1;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userId;
    }
}
