package com.min.seed.service.impl;

import com.min.seed.dao.OrderMapper;
import com.min.seed.entity.Order;
import com.min.seed.service.OrderService;
import com.min.seed.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by minych on 2022/09/04.
 */
@Service
@Transactional
public class OrderServiceImpl extends AbstractService<Order> implements OrderService {

    @Resource
    private OrderMapper sysOrderMapper;

}
