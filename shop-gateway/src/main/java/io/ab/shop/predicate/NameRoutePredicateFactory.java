package io.ab.shop.predicate;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Description 自定义断言，请求参数中需要有“name”属性，并且与配置中的值相同才可以
 * @Return
 * @Date 2023/1/19 16:50
 */
@Component
public class NameRoutePredicateFactory extends AbstractRoutePredicateFactory<NameRoutePredicateConfig>{

    public NameRoutePredicateFactory() {
        super(NameRoutePredicateConfig.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(NameRoutePredicateConfig config) {

        return (serverWebExchange -> {
            String name = serverWebExchange.getRequest().getQueryParams().getFirst("name");
            if (StringUtils.isEmpty(name)){
                name = "";
            }
            return name.equals(config.getName());
        });
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("name");
    }
}
