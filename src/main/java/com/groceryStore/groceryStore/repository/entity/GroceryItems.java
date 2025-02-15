package com.groceryStore.groceryStore.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GroceryItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROCERY_ID")
    private int id;

    @Column(name = "PRODUCT_NAME",unique = true)
    private String name;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRICE")
    private Double price;
}
