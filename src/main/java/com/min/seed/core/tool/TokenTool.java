package com.min.seed.core.tool;

import com.min.seed.core.config.TokenConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenTool {

    private static final long MILLIS_MINUTE = 60 * 1000;

    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private TokenConfig weChatTokenProperties;

    public String createToken(String id, String subject) {
        long now = System.currentTimeMillis();
        long expired = now + weChatTokenProperties.getExpireTime() * MILLIS_MINUTE;

        return Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(expired))
                .signWith(SignatureAlgorithm.HS512, weChatTokenProperties.getSecret())
                .compact();
    }


    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(weChatTokenProperties.getHeader());
        if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "");
        }
        return token;
    }

    public Claims getClaimFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(weChatTokenProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

}
