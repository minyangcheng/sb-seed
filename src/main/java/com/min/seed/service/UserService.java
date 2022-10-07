package com.min.seed.service;

import com.min.seed.core.service.Service;
import com.min.seed.entity.User;


/**
 * Created by minych on 2022/09/03.
 */
public interface UserService extends Service<User> {

    User login(String username, String password);

    User getUserDetail(Integer userId);

}
