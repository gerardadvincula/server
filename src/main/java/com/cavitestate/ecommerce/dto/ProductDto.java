package com.cavitestate.ecommerce.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {

    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private Double price;
    private Integer quantity;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String categoryId;
    private Float finalRating;
    private Integer sold;
    private Boolean bestSeller;
    private Boolean archived;
}
