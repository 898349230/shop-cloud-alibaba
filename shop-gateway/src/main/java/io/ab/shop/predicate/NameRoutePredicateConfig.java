package io.ab.shop.predicate;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 接收配置文件中的参数
 * @Author sunxinbo
 * @Return 
 * @Date 2023/1/19 16:46
 */
@Data
public class NameRoutePredicateConfig implements Serializable {
    private static final long serialVersionUID = -3289515863427972825L;
    private String name;
}