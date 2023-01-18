package io.ab.shop.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.ab.shop.bean.Order;
import io.ab.shop.bean.OrderItem;
import io.ab.shop.bean.Product;
import io.ab.shop.bean.User;
import io.ab.shop.order.fegin.ProductService;
import io.ab.shop.order.fegin.UserService;
import io.ab.shop.order.mapper.OrderItemMapper;
import io.ab.shop.order.mapper.OrderMapper;
import io.ab.shop.order.service.OrderService;
import io.ab.shop.params.OrderParams;
import io.ab.shop.utils.constants.HttpCode;
import io.ab.shop.utils.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


/**
 * @author 增加熔断限流
 * @version 1.0.0
 * @description
 */
@Slf4j
@Service("orderServiceV6")
public class OrderServiceV6Impl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        if (orderParams.isEmpty()){
            throw new RuntimeException("参数异常: " + JSONObject.toJSONString(orderParams));
        }

        // feign 调用
        User user = userService.getUser(orderParams.getUserId());
        log.info("[saveOrder] user = {}", JSON.toJSONString(user));
        if (user == null){
            throw new RuntimeException("未获取到用户信息: " + JSONObject.toJSONString(orderParams));
        }
        if (user.getId() == -1L || user.getId() == -2L){
            throw new RuntimeException("触发了用户微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
        Product product = productService.getProduct(orderParams.getProductId());
        log.info("[saveOrder] product = {}", JSON.toJSONString(product));
        if (product == null){
            throw new RuntimeException("未获取到商品信息: " + JSONObject.toJSONString(orderParams));
        }
        if (product.getId() == -1){
            throw new RuntimeException("触发了商品微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
        if (product.getProStock() < orderParams.getCount()){
            throw new RuntimeException("商品库存不足: " + JSONObject.toJSONString(orderParams));
        }
        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

        Result<Integer> result = productService.updateCount(orderParams.getProductId(), orderParams.getCount());
        if (result.getCode() != HttpCode.SUCCESS){
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");
    }

}
