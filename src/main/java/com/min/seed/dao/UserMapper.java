package com.min.seed.dao;

import com.min.seed.core.mapper.Mapper;
import com.min.seed.entity.User;

public interface UserMapper extends Mapper<User> {

    User selectByUsernameAndPassword(String username, String password);

    User selectById(Integer userId);

}