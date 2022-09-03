package com.min.seed.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenConfig {

    @Value("${token.header}")
    private String header;

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    private long expireTime;

    public String getHeader() {
        return header;
    }

    public String getSecret() {
        return secret;
    }

    public long getExpireTime() {
        return expireTime;
    }
}
