package com.min.seed;

import com.min.seed.core.tool.TokenTool;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class JwtTest {

    @Test
    public void testIoJsonWebToken() {
////        long now = System.currentTimeMillis();
////        long exp = now+1000*60;//设置过期时间为1分钟
////
////        JwtBuilder builder = Jwts.builder().setId("103")//用户id
////                .setSubject("张三丰") //用户名称
////                .setIssuedAt(new Date())//token签发时间
////                .signWith(SignatureAlgorithm.HS256, "kefei")
////                .setExpiration(new Date(exp))//token过期时间
////                .claim("roles", "admin")//用户角色信息
////                .claim("logo", "logo.png");//用户图像
////        System.out.println("token:"+builder.compact());
//
//        Date nowDate = new Date();
//        // 过期时间
//        Date expireDate = new Date(nowDate.getTime() + 1000 * 60);
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("expireDateqqq", expireDate);
//
//        String s = Jwts.builder()
//                .signWith(SignatureAlgorithm.HS256, "abcdefghijklmnopqrstuvwxyz")
//                .setId("aaa")
//                .setSubject("asdfasdf")
//                .setIssuedAt(nowDate)
//                .setExpiration(expireDate)
//                .addClaims(claims)
//                .compact();
//        System.out.println(s);

        TokenTool tokenTool = new TokenTool();
        String token = tokenTool.createToken("1","minych");
        log.debug(token);

    }

    @Test
    public void parseJsonWebToken() {
//        String token=
//                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMDMiLCJzdWIiOiLlvKDkuInkuLAiLCJpYXQiOjE2NjIxMjQ4ODYsImV4cCI6MTY2MjEyNDk0Niwicm9sZXMiOiJhZG1pbiIsImxvZ28iOiJsb2dvLnBuZyJ9.wWm54M5QvOCSzuv6bVnqoheB_7gz7zO2fJPcBHgSQcE";
//        Claims claims =
//                Jwts.parser().setSigningKey("kefei").parseClaimsJws(token).getBody();
//        System.out.println("用户id:"+claims.getId());
//        System.out.println("Subject用户名："+claims.getSubject());
//        System.out.println("roles:"+claims.get("roles"));
//        System.out.println("logo:"+claims.get("logo"));
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
//        System.out.println("签发时间:"+sdf.format(claims.getIssuedAt()));
//        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
//        System.out.println("当前时间:"+sdf.format(new Date()));


        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYWEiLCJzdWIiOiJhc2RmYXNkZiIsImlhdCI6MTY2MjEyNTc0OSwiZXhwIjoxNjYyMTI1ODA5LCJleHBpcmVEYXRlcXFxIjoxNjYyMTI1ODA5NDg4fQ.cT_zxr4uqFm23oh7SPQ96TPuHw34t5QZ8-r1HsL_EF8";
        Claims claims = Jwts.parser()
                .setSigningKey("abcdefghijklmnopqrstuvwxyz")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("subject:" + claims.getSubject());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        System.out.println("签发时间:" + sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:" + sdf.format(claims.getExpiration()));
        System.out.println("当前时间:" + sdf.format(new Date()));
    }

}
