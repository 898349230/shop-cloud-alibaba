package io.ab.shop.user.service.impl;

import io.ab.shop.bean.User;
import io.ab.shop.user.mapper.UserMapper;
import io.ab.shop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }
}