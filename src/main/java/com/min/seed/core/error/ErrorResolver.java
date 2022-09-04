package com.min.seed.core.error;

import com.min.seed.core.error.exception.AuthException;
import com.min.seed.core.error.exception.ServiceException;
import com.min.seed.core.result.Result;
import com.min.seed.core.result.ResultCode;
import com.min.seed.core.result.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ErrorResolver {

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public Result authException(ServiceException e) {
        return ResultGenerator.genFailResult(e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result serviceException(ServiceException e) {
        return ResultGenerator.genFailResult(e.getMessage());
    }

    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    public Result<String> handleValidatedException(Exception e) {
        String message = "";
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            message = ex.getBindingResult().getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(";"));
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) e;
            message = ex.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(";"));
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            message = ex.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(";"));
        }
        return ResultGenerator.genFailResult(message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error("request uri [ " + request.getRequestURI() + " ] occur error", e);
        return ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR, ResultCode.INTERNAL_SERVER_ERROR + ":" + e.getMessage());
    }

}
