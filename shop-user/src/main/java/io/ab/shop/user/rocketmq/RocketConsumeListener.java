package io.ab.shop.user.rocketmq;

import com.alibaba.fastjson.JSONObject;
import io.ab.shop.bean.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @Description 监听消费
 * @Author sunxinbo
 * @Return
 * @Date 2023/1/30 19:35
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "user-group", topic = "order-topic")
public class RocketConsumeListener implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("用户微服务收到了订单信息：{}", JSONObject.toJSONString(order));
    }
}
