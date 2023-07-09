package com.cavitestate.ecommerce.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private Long id;

    private String email;

    private String name;

    private String imageUrl;

    private String password;

    private String userRole;

    private String secretAnswer;

    private String secretQuestion;
}
