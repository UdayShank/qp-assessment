package com.groceryStore.groceryStore.repository;

import com.groceryStore.groceryStore.repository.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInfoRepsitory extends JpaRepository<OrderInfo, Integer> {
}
