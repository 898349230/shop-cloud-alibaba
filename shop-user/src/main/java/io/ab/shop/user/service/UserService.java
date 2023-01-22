package io.ab.shop.user.service;

import io.ab.shop.bean.User;

public interface UserService {

    /**
     * 根据id获取用户信息
     */
    User getUserById(Long userId);

    void asyncMethod();
}