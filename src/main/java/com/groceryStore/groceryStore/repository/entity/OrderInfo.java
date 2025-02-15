package com.groceryStore.groceryStore.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private int id;

    @OneToMany(mappedBy = "itemOrderId", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    public OrderInfo(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }
}
