package com.Lidigu.ecommerce.Product.cartegory;

import com.Lidigu.ecommerce.Product.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Cartegory {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy ="cartegory", cascade=CascadeType.REMOVE )
    private List<Product> products;

}
