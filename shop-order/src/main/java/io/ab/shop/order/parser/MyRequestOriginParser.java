package io.ab.shop.order.parser;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyRequestOriginParser implements RequestOriginParser{
    /**
     * @Description 返回值等于 授权规则中的流控应用 配置的值，则会触发 白名单或者黑名单规则
     * @Return
     * @Date 2023/1/18 15:14
     */
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        // 判断黑名单使用
        return httpServletRequest.getParameter("serverName");
    }
}
