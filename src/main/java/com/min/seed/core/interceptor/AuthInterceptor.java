package com.min.seed.core.interceptor;

import com.min.seed.core.annotation.NeedLogin;
import com.min.seed.core.config.TokenConfig;
import com.min.seed.core.error.exception.AuthException;
import com.min.seed.core.tool.SpringContextTool;
import com.min.seed.core.tool.TokenTool;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenConfig tokenConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            // 如果不是映射到方法直接通过
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(NeedLogin.class)) {
            NeedLogin needLogin = method.getAnnotation(NeedLogin.class);
            if (!needLogin.value()) {
                //不需要检查登录权限
                return true;
            }
        }

        TokenTool tokenTool = SpringContextTool.getBean(TokenTool.class);
        String token = tokenTool.getToken(request);
        log.info("获取到token:{}", token);
        if (StringUtils.isEmpty(token)) {
            log.error("token不能为空");
            throw new AuthException("token不能为空");
        }
        try {
            Claims claims = tokenTool.getClaimFromToken(token);
            request.setAttribute("userId", claims.getId());
        } catch (ExpiredJwtException e) {
            throw new AuthException("token过期");
        } catch (Exception e) {
            log.error("token解析失败：", e.getMessage());
            throw new AuthException("token无效");
        }

        return true;
    }
}


