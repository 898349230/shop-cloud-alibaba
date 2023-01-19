package io.ab.shop.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * @Description 全局网关过滤器配置
 * @Return
 * @Date 2023/1/19 17:31
 */
@Configuration
@Slf4j
public class GatewayFilterConfig {

    @Bean
    @Order(-1)
    public GlobalFilter globalFilter(){
        return (exchanger, chain) -> {
            log.info("执行前置过滤器逻辑");
            return chain.filter(exchanger).then(Mono.fromRunnable(()->{
                log.info("执行后置过滤器逻辑");
            }));
        };
    }

}
