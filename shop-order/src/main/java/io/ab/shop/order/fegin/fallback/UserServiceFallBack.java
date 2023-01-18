package io.ab.shop.order.fegin.fallback;

import io.ab.shop.bean.User;
import io.ab.shop.order.fegin.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description 用户服务容错类
 * @Return
 * @Date 2023/1/18 13:51
 */
@Component
@Slf4j
public class UserServiceFallBack implements UserService {
    @Override
    public User getUser(Long uid) {
        User user = new User();
        user.setId(-1L);
        log.info("[UserServiceFallBack] 。。。");
        return user;
    }
}
