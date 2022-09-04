package com.min.seed.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.seed.core.annotation.RequestJson;
import com.min.seed.core.result.Result;
import com.min.seed.core.result.ResultGenerator;
import com.min.seed.core.tool.TokenTool;
import com.min.seed.core.validation.ValidGroup;
import com.min.seed.entity.User;
import com.min.seed.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by minych on 2022/09/03.
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {

    @Resource
    private UserService userService;

    @Autowired
    private TokenTool tokenTool;

    @PostMapping("/login")
    public Result login(@RequestJson @NotNull(message = "用户名不能为空") String username,
                        @RequestJson @NotNull(message = "密码不能为空") String password) {
        log.info("login params: username={}, password={}", username, password);
        User user = userService.login(username, password);
        if (user == null) {
            return ResultGenerator.genFailResult("用户名或密码错误");
        }
        String token = tokenTool.createToken(String.valueOf(user.getUserId()), user.getUsername());
        return ResultGenerator.genSuccessResult(token);
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Validated(ValidGroup.Crud.Create.class) User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestJson Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Validated(ValidGroup.Crud.Update.class) User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/list")
    public Result list(@RequestJson(defaultValue = "0") Integer page, @RequestJson(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
