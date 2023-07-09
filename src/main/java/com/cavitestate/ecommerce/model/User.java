package com.cavitestate.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String email;

    private String name;

    private String imageUrl;

    private String password;

    private String userRole = "ROLE_USER";

    private String secretAnswer;

    private String secretQuestion;

    private String barangay;

    private String street;

    private String municipality;

    private String city;

    private String postalCode;

    private Boolean firstLogin = false;

    private String contactNumber;

    private String blockNLot;

}
