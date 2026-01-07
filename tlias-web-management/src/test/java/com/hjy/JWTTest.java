package com.hjy;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {
    @Test
    // 生成JWT令牌
    public void testBuild(){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("id",1);
        dataMap.put("username","admin");

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "aXRoank=") // 加密算法和密钥
                .addClaims(dataMap)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))  //设置过期时间
                .compact();
        System.out.println(jwt);
    }
    // eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc2NzAwNTk2OH0.ufj0CeZO2DAyT6tU8gxtbCFNSoR63_IA5uOvlR2VVt4
    @Test
    public void testParse(){
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc2NzAwNTk2OH0.ufj0CeZO2DAyT6tU8gxtbCFNSoR63_IA5uOvlR2VVt4";
        Claims body = Jwts.parser().setSigningKey("aXRoank=")
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(body);
    }
}
