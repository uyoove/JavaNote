package com.gxt;

import com.google.gson.Gson;
import com.gxt.pojo.Emp;
import com.gxt.pojo.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TliasWebManagementApplicationTests {

    @Autowired
    private Gson gson; //测试bean的自动注入

    //秘钥
    String key = "gxttttttttttttttttttttttttttttttttttttttttttttttttttt";
    String Token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiZ3h0IiwiaWQiOjEsImV4cCI6MTc1OTkzMDIzMH0.dLQKlYMK9iCF92OseooCnarEglNVGjtAqsb1P33hVcQ";
    @Test
    public void testGenJwt(){ //生成令牌

        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","gxt");
        String fuck = gson.toJson(Result.success());
        System.out.println(fuck);
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,key) //数字签名算法,秘钥
                .setClaims(claims) //自定义数据(载荷)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置有效期为1小时
                .compact();//返回字符串类型

        System.out.println(jwt);
    }


    @Test
    public void testParseJwt(){ //解析

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(Token)
                .getBody();
        System.out.println(claims);
    }

}
