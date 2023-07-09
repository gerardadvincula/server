package com.cavitestate.ecommerce.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class CategoryDto {

    private Integer id;
    private String categoryName;
    private String description;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<ProductDto> products;
}
