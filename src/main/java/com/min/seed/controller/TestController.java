package com.min.seed.controller;

import com.min.seed.core.annotation.CurrentUser;
import com.min.seed.core.annotation.NeedLogin;
import com.min.seed.core.annotation.RequestJson;
import com.min.seed.core.config.TokenConfig;
import com.min.seed.core.error.exception.ServiceException;
import com.min.seed.core.result.Result;
import com.min.seed.core.result.ResultGenerator;
import com.min.seed.core.tool.SpringContextTool;
import com.min.seed.core.tool.TokenTool;
import com.min.seed.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TokenConfig weChatTokenProperties;

    @PostMapping("/test")
    public Result test(@RequestJson String name, @CurrentUser User user) {
        log.info("test params: name={}, user={}", name, user);
        return ResultGenerator.genSuccessResult();
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

