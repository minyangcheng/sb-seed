package com.min.seed.core.exception;

import com.min.seed.core.result.Result;
import com.min.seed.core.result.ResultCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NotFoundErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result error(HttpServletRequest request) {
        Object obj = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        String uri = "***";
        if (obj != null) {
            uri = obj.toString();
        }
        return Result.newInstance(ResultCode.NOT_FOUND, "接口 [" + uri + "] 不存在");
    }

}
