package com.Lidigu.ecommerce.customer;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document

public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
