package com.min.seed.core.exception;

import com.min.seed.core.result.Result;
import com.min.seed.core.result.ResultGenerator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorResolver {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceException(ServiceException e) {
        return ResultGenerator.genFailResult("serviceException:" + e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Result noHandlerException(NoHandlerFoundException e, HttpServletRequest request) {
        return ResultGenerator.genFailResult("noHandlerException:" + e.getMessage());
    }

    @ExceptionHandler(ServletException.class)
    @ResponseBody
    public Result servletException(ServletException e) {
        return ResultGenerator.genFailResult("servletException:" + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(Exception e) {
        return ResultGenerator.genFailResult("globalException:" + e.getMessage());
    }

}
