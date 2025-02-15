package com.groceryStore.groceryStore.repository;

import com.groceryStore.groceryStore.repository.entity.GroceryItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemsRepository extends JpaRepository<GroceryItems, Integer> {
}
