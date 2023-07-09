package com.cavitestate.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "courier")
public class Courier {

    @Id
    private String id;

    private String courierName;

    private String courierWebsite;

}
