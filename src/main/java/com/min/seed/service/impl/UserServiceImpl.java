package com.min.seed.service.impl;

import com.min.seed.dao.UserMapper;
import com.min.seed.entity.User;
import com.min.seed.service.UserService;
import com.min.seed.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by minych on 2022/09/03.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper sysUserMapper;

}
