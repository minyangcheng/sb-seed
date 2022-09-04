package com.min.seed.core.config.resolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.min.seed.core.annotation.RequestJson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.rmi.ServerException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class RequestJsonMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String JSON_BODY_KEY = "JSON_BODY_KEY";

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestJson.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        JSONObject jsonObject = getJsonObject(nativeWebRequest);

        RequestJson requestJson = methodParameter.getParameterAnnotation(RequestJson.class);
        String paramName = requestJson.value();
        if (StringUtils.isEmpty(paramName)) {
            paramName = methodParameter.getParameterName();
        }
        Object paramValue = jsonObject.get(paramName);
        boolean required = requestJson.required();
        if (!requestJson.defaultValue().equals(ValueConstants.DEFAULT_NONE)) {
            //defaultValue有值的时候，隐式required为false
            required = false;
        }
        if (required && paramValue == null) {
            throw new ServerException("parameter [" + paramName + "] 不能为空");
        }
        if (!required && !requestJson.defaultValue().equals(ValueConstants.DEFAULT_NONE)) {
            paramValue = requestJson.defaultValue();
        }
        if (paramValue == null) {
            return null;
        }
        log.info("resolveArgument jsonObject={} paramName={}, paramValue={}", jsonObject, paramName, paramValue);
        return convertParameter(methodParameter, paramName, paramValue);
    }

    private Object convertParameter(MethodParameter methodParameter, String paramName, Object obj) throws Exception {
        try {
            String typeName = methodParameter.getParameterType().getTypeName();
            if (typeName.equals(String.class.getTypeName())) {
                return obj.toString();
            } else if (typeName.equals(Integer.class.getTypeName())) {
                return TypeUtils.castToInt(obj);
            } else if (typeName.equals(Float.class.getTypeName())) {
                return TypeUtils.castToFloat(obj);
            } else if (typeName.equals(List.class.getTypeName()) || typeName.equals(Set.class.getTypeName())) {
                Type actualTypeArgument = ((ParameterizedTypeImpl) methodParameter.getGenericParameterType()).getActualTypeArguments()[0];
                return JSON.parseArray(obj.toString(), Class.forName(actualTypeArgument.getTypeName()));
            } else if (typeName.equals(Map.class.getTypeName())) {
                return JSON.parseObject(obj.toString(), Map.class);
            } else {
                return JSON.parseObject(obj.toString(), methodParameter.getParameterType());
            }
        } catch (Exception e) {
            log.error("convert json params occur error", e);
            throw new ServerException("convert json param " + paramName + " occur error");
        }
    }

    private JSONObject getJsonObject(NativeWebRequest webRequest) throws IOException {
        String jsonBody = (String) webRequest.getAttribute(JSON_BODY_KEY, NativeWebRequest.SCOPE_REQUEST);
        if (jsonBody == null) {
            HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(servletRequest.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            jsonBody = responseStrBuilder.toString();
            webRequest.setAttribute(JSON_BODY_KEY, jsonBody, NativeWebRequest.SCOPE_REQUEST);
        }
        JSONObject jsonObject = JSON.parseObject(jsonBody);
        return jsonObject;
    }
}
