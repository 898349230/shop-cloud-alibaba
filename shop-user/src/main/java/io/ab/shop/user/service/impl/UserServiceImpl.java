package io.ab.shop.user.service.impl;

import io.ab.shop.bean.User;
import io.ab.shop.user.mapper.UserMapper;
import io.ab.shop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Async
    @Override
    public void asyncMethod() {
        // 线程池内异步执行的 spanId 会改变，traceId 不变
        log.info("[UserServiceImpl] 执行了异步任务...");
    }
}