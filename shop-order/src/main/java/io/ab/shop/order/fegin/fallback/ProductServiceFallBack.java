package io.ab.shop.order.fegin.fallback;

import io.ab.shop.bean.Product;
import io.ab.shop.order.fegin.ProductService;
import io.ab.shop.utils.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @version 1.0.0
 * @description 商品微服务的容错类
 */
@Component
@Slf4j
public class ProductServiceFallBack implements ProductService {
    @Override
    public Product getProduct(Long pid) {
        log.info("[ProductServiceFallBack] 。。。");
        Product product = new Product();
        product.setId(-1L);
        return product;
    }

    @Override
    public Result<Integer> updateCount(Long pid, Integer count) {
        log.info("[ProductServiceFallBack] 。。。");
        Result<Integer> result = new Result<>();
        result.setCode(1001);
        result.setCodeMsg("触发了容错逻辑");
        return result;
    }
}
