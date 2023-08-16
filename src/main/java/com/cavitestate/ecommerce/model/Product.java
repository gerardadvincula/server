package com.cavitestate.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "product")
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    private String name;

    private String imageUrl;

    private String description;

    private Double price;

    private Integer quantity;

    private String categoryId;

    private Boolean archived = false;

    @CreatedDate
    private LocalDateTime createdDate;

    private Integer sold = 0;

    private Boolean bestSeller = false;

    private Long TotalSalesPerProduct;
}
