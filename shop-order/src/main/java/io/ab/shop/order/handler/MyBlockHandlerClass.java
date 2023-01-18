package io.ab.shop.order.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 定义被Sentinel限流时调用的方法
 * @Return
 * @Date 2023/1/18 15:35
 */
@Slf4j
public class MyBlockHandlerClass {

    public static String blockHandler(BlockException e){
        log.error("[blockHandler] 限流了  ", e);
        return "[blockHandler] 限流了";
    }
}
