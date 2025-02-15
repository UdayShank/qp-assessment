package com.groceryStore.groceryStore.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ITEM_ID")
    private int itemOrderId;

    @Column(name = "ORDER_QUANTITY")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id")
    private GroceryItems groceryItem;
}
