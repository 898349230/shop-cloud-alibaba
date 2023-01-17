package io.ab.shop.order.service;

import io.ab.shop.params.OrderParams;

/**
 * @author binghe
 * @version 1.0.0
 * @description 订单业务接口
 */
public interface OrderService {


    /**
     * 保存订单
     */
    void saveOrder(OrderParams orderParams);
}
