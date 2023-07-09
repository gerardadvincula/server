package com.cavitestate.ecommerce.dto;

import lombok.Data;

@Data
public class OrderJsonDto {
    private Long id;
    private String productName;
    private String productVariationName;
    private Long quantity;
    private String description;
    private String imageUrl;
}
