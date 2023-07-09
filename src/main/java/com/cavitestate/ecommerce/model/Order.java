package com.cavitestate.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String userFullName;

    private String email;

    private Double totalPrice;

    private String proofPayment;

    private String orderJsonList;

    private String status;

    @CreatedDate
    private LocalDate dateNow;

    private String trackingNum;

    private String modeOfPayment;

    private String courier;
}
