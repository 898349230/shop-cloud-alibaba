package io.ab.shop.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.ab.shop.order.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description sentinel 链路流控测试用
 * @Author
 * @Return
 * @Date 2023/1/18 14:50
 */
@RestController
@Slf4j
public class SentinelController {
    @Autowired
    private SentinelService sentinelService;

    @GetMapping(value = "/request_sentinel1")
    public String requestSentinel1(){
        log.info("测试Sentinel1");
        sentinelService.sendMessage();
        return "sentinel1";
    }

    @GetMapping(value = "/request_sentinel2")
    public String requestSentinel2(){
        log.info("测试Sentinel2");
        sentinelService.sendMessage();
        return "sentinel2";
    }

    @GetMapping(value = "/request_sentinel3")
    @SentinelResource("request_sentinel3")
    public String requestSentinel3(String header, String body){
        log.info("测试Sentinel3");
        return "sentinel3";
    }

    @GetMapping(value = "/request_sentinel4")
    @SentinelResource("request_sentinel4")
    public String requestSentinel3(){
        log.info("测试Sentinel4");
        return "sentinel4";
    }

    @GetMapping(value = "/request_sentinel6")
    public String requestSentinel6(){
        log.info("测试Sentinel6");
        return sentinelService.sendMessage2();
    }

}
