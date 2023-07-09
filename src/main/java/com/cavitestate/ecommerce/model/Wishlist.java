package com.cavitestate.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "wishlist")
public class Wishlist {

    @Id
    private String id;

    private String productId;

    private String email;

    @CreatedDate
    private LocalDateTime createdDate;
}
