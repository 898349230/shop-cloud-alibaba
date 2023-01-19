package io.ab.shop.filter;

import lombok.Data;

import java.io.Serializable;

@Data
public class GrayscaleGatewayFilterConfig implements Serializable {
    private static final long serialVersionUID = 983019309000445082L;
    private boolean grayscale;
}