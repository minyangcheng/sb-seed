package com.min.seed.core.config.resolver;

import com.min.seed.core.annotation.CurrentUser;
import com.min.seed.core.error.exception.AuthException;
import com.min.seed.core.tool.SpringContextTool;
import com.min.seed.dao.UserMapper;
import com.min.seed.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType().isAssignableFrom(User.class) && parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
        if (currentUserAnnotation == null) {
            return null;
        }
        String userId = webRequest.getAttribute("user_id", NativeWebRequest.SCOPE_REQUEST).toString();
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        User query = new User();
        query.setUserId(Long.parseLong(userId));
        User user = SpringContextTool.getBean(UserMapper.class).selectOne(query);
        if (user == null) {
            throw new AuthException("用户不存在");
        }
        return user;
    }

}
