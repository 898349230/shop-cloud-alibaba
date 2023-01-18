package io.ab.shop.order.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 定义异常时调用的方法
 * @Return
 * @Date 2023/1/18 15:38
 */
@Slf4j
public class MyFallbackClass {

    public static String fallback(Throwable e){
        log.error("[fallback] 异常了 ", e);
        return "[fallback] 异常了";
    }
}
