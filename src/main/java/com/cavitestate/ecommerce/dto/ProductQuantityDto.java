package com.cavitestate.ecommerce.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductQuantityDto {

    private String productId;
    private int quantity;
}
