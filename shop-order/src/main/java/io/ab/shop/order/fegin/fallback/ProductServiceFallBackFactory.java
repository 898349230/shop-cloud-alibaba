package io.ab.shop.order.fegin.fallback;

import feign.hystrix.FallbackFactory;
import io.ab.shop.bean.Product;
import io.ab.shop.order.fegin.ProductService;
import io.ab.shop.utils.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @version 1.0.0
 * @description
 */
@Component
@Slf4j
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product getProduct(Long pid) {
                Product product = new Product();
                product.setId(-1L);
                return product;
            }

            @Override
            public Result<Integer> updateCount(Long pid, Integer count) {
                Result<Integer> result = new Result<>();
                result.setCode(1001);
                result.setCodeMsg("触发了容错逻辑");
                return result;
            }
        };
    }
}
