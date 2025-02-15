package com.groceryStore.groceryStore.repository;

import com.groceryStore.groceryStore.repository.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
}
