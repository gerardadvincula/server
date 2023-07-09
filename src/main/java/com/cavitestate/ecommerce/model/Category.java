package com.cavitestate.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "category")
public class Category {

    @Id
    private String id;

    private String categoryName;

    private String description;

    private String imageUrl;

    @CreatedDate
    private LocalDateTime createdDate;

    List<Product> products;
}
