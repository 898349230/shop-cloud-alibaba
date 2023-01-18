package io.ab.shop.order.fegin;

import io.ab.shop.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description 调用用户微服务的接口
 * @Return
 * @Date 2023/1/18 11:36
 */
@FeignClient("server-user")
public interface UserService {
    @GetMapping(value = "/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}
