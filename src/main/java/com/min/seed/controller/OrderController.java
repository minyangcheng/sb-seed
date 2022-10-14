package com.min.seed.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.min.seed.core.annotation.RequestJson;
import com.min.seed.core.result.Result;
import com.min.seed.core.result.ResultGenerator;
import com.min.seed.dto.OrderDto;
import com.min.seed.entity.Order;
import com.min.seed.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by minych on 2022/09/04.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/add")
    public Result add(@RequestBody Order order) {
        orderService.save(order);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestJson Integer id) {
        orderService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Order order) {
        orderService.update(order);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestJson Integer id) {
        Order order = orderService.findById(id);
        return ResultGenerator.genSuccessResult(order);
    }

    @PostMapping("/list")
    public Result list(@RequestBody OrderDto orderDto) {
        PageHelper.startPage(orderDto.getPage(), orderDto.getSize());
        Condition condition = new Condition(Order.class);
        Example.Criteria criteria = condition.createCriteria();
        if (StringUtils.isNotEmpty(orderDto.getName())) {
            criteria.andLike("name", "%" + orderDto.getName() + "%");
        }
        List<Order> list = orderService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
