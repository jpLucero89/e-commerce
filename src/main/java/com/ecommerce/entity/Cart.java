package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long cartId;
    private Date date;
    @OneToMany
    private List<Product> product;
    private Boolean isSpecial;

    public Cart() {

    }
}
