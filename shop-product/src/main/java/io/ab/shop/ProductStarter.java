package io.ab.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author binghe
 * @version 1.0.0
 * @description 商品服务启动类
 */
@SpringBootApplication
@MapperScan(value = { "io.ab.shop.product.mapper" })
@EnableTransactionManagement(proxyTargetClass = true)
@EnableDiscoveryClient
public class ProductStarter {

    public static void main(String[] args){
        SpringApplication.run(ProductStarter.class, args);
    }
}
