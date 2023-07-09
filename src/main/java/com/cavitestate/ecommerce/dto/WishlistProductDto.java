package com.cavitestate.ecommerce.dto;

import com.cavitestate.ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistProductDto {
    private String wishlistId;
    private Product product;

}
