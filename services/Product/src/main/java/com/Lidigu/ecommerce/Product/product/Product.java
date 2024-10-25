package com.Lidigu.ecommerce.Product.product;

import com.Lidigu.ecommerce.Product.cartegory.Cartegory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name="cartegory_id")
    private Cartegory cartegory;
}
