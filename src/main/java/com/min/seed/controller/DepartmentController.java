package com.min.seed.controller;

import com.min.seed.core.result.Result;
import com.min.seed.core.result.ResultGenerator;
import com.min.seed.entity.Department;
import com.min.seed.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.min.seed.core.annotation.RequestJson;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by minych on 2022/10/07.
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @PostMapping("/add")
    public Result add(@RequestBody Department department) {
        departmentService.save(department);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestJson Integer id) {
        departmentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Department department) {
        departmentService.update(department);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestJson Integer id) {
        Department department = departmentService.getDepartmentDetail(id);
        return ResultGenerator.genSuccessResult(department);
    }

    @PostMapping("/list")
    public Result list(@RequestJson(defaultValue = "0") Integer page, @RequestJson(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Department> list = departmentService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
