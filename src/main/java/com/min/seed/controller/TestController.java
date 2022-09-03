package com.min.seed.controller;

/**
 * @className: TestController
 * @author: houqd
 * @date: 2022/7/16
 **/

import com.min.seed.core.annotation.NeedLogin;
import com.min.seed.core.config.TokenConfig;
import com.min.seed.core.error.exception.ServiceException;
import com.min.seed.core.result.Result;
import com.min.seed.core.result.ResultGenerator;
import com.min.seed.core.tool.SpringContextTool;
import com.min.seed.core.tool.TokenTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author:houqd
 * @time: 2022/7/16 9:11
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TokenConfig weChatTokenProperties;

    @GetMapping("/login")
    public Result login() {
        TokenTool tokenTool = SpringContextTool.getBean(TokenTool.class);
        return ResultGenerator.genSuccessResult(tokenTool.createToken("userId-1","userName-aa"));
    }

    @GetMapping("/test1")
    public Result test1() {
        int a = 1 / 0;
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/test2")
    @NeedLogin()
    public Result test2() {
        return ResultGenerator.genSuccessResult(weChatTokenProperties.getHeader());
    }

    @GetMapping("/test3")
//    @NeedLogin()
    public Result test3() {
        throw new ServiceException("不能获取数据");
//        return ResultGenerator.genSuccessResult(weChatTokenProperties.getHeader());
    }

}

