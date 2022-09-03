package com.min.seed.core.error;

import com.min.seed.core.error.exception.AuthException;
import com.min.seed.core.error.exception.ServiceException;
import com.min.seed.core.result.Result;
import com.min.seed.core.result.ResultCode;
import com.min.seed.core.result.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ErrorResolver {

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public Result authException(ServiceException e) {
        log.error("authException", e);
        return ResultGenerator.genFailResult(e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceException(ServiceException e) {
        log.error("serviceException", e);
        return ResultGenerator.genFailResult(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(Exception e) {
        log.error("globalException", e);
        return ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR, ResultCode.INTERNAL_SERVER_ERROR + ":" + e.getMessage());
    }

}