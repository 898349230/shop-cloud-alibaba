package io.ab.shop.order.fegin.fallback;

import feign.hystrix.FallbackFactory;
import io.ab.shop.bean.User;
import io.ab.shop.order.fegin.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @version 1.0.0
 * @description 用户微服务容错Factory，
 */
@Component
@Slf4j
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        log.info("[UserServiceFallBackFactory] 。。。");
        return new UserService() {
            @Override
            public User getUser(Long uid) {
                User user = new User();
                user.setId(-2L);
                return user;
            }
        };
    }
}
