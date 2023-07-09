package com.cavitestate.ecommerce.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private String id;

    private Double totalPrice;

    private String status = "Pending";

    private String userId;

    private String userFullName;

    private String productId;

    private Integer quantity;

    private LocalDateTime createdDate;

    private String proofPayment;

    private LocalDate dateNow;

    private String orderJsonList;

    private String email;

    private List<ProductQuantityDto> products;

    private String modeOfPayment;
}
