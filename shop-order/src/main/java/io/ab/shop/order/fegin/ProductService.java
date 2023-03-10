package io.ab.shop.order.fegin;

import io.ab.shop.bean.Product;
import io.ab.shop.order.fegin.fallback.ProductServiceFallBack;
import io.ab.shop.order.fegin.fallback.ProductServiceFallBackFactory;
import io.ab.shop.utils.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "server-product", fallback = ProductServiceFallBack.class)
//@FeignClient(value = "server-product", fallbackFactory = ProductServiceFallBackFactory.class)
public interface ProductService {
    /**
     * 获取商品信息
     */
    @GetMapping(value = "/product/get/{pid}")
    Product getProduct(@PathVariable("pid") Long pid);

    /**
     * 更新库存数量
     */
    @GetMapping(value = "/product/update_count/{pid}/{count}")
    Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count);
}
