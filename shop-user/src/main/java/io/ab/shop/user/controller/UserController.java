package io.ab.shop.user.controller;

import com.alibaba.fastjson.JSONObject;
import io.ab.shop.bean.User;
import io.ab.shop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/get/{uid}")
    public User getUser(@PathVariable("uid") Long uid){
        User user = userService.getUserById(uid);
        log.info("获取到的用户信息为：{}", JSONObject.toJSONString(user));
        return user;
    }

    @GetMapping(value = "/api1/demo1")
    public String api1Demo1(){
        log.info("访问了api1Demo1接口");
        return "api1Demo1";
    }

    @GetMapping(value = "/api/filter1")
    public String apiFilter1(HttpServletRequest request, HttpServletResponse response){
        log.info("访问了apiFilter1接口");
        String ip = request.getHeader("IP");
        String name = request.getParameter("name");
        String traceId = request.getParameter("traceId");
        log.info("ip = " + ip + ", name = " + name + ", traceId = " + traceId);
        return "apiFilter1";
    }

    @GetMapping(value = "/async/api")
    public String asyncApi() {
        log.info("执行异步任务开始...");
        userService.asyncMethod();
        log.info("异步任务执行结束...");
        return "asyncApi";
    }

    @GetMapping(value = "/sleuth/filter/api")
    public String sleuthFilter(HttpServletRequest request) {
        Object traceIdObj = request.getAttribute("traceId");
        String traceId = traceIdObj == null ? "" : traceIdObj.toString();
        log.info("获取到的traceId为: " + traceId);
        return "sleuthFilter";
    }
}