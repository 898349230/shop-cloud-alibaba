package io.ab.shop.order.fegin;

import io.ab.shop.bean.User;
import io.ab.shop.order.fegin.fallback.UserServiceFallBack;
import io.ab.shop.order.fegin.fallback.UserServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description 调用用户微服务的接口，   fallback 和 fallbackFactory 只能一个生效
 * @Return
 * @Date 2023/1/18 11:36
 */
//@FeignClient(value = "server-user", fallback = UserServiceFallBack.class)
@FeignClient(value = "server-user",fallbackFactory = UserServiceFallBackFactory.class)
public interface UserService {
    @GetMapping(value = "/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}
