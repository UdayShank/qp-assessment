package com.groceryStore.groceryStore.service;

import com.groceryStore.groceryStore.exception.GroceryNotFoundException;
import com.groceryStore.groceryStore.exception.InsufficientInventoryException;
import com.groceryStore.groceryStore.exception.OrderProcessingException;
import com.groceryStore.groceryStore.model.req.OrderGroceryRequest;
import com.groceryStore.groceryStore.repository.GroceryItemsRepository;
import com.groceryStore.groceryStore.repository.OrderInfoRepsitory;
import com.groceryStore.groceryStore.repository.entity.GroceryItems;
import com.groceryStore.groceryStore.repository.entity.OrderInfo;
import com.groceryStore.groceryStore.repository.entity.OrderItems;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    private final OrderInfoRepsitory orderInfoRepsitory;
    private final GroceryItemsRepository groceryItemsRepository;

    @Autowired
    public OrderService(OrderInfoRepsitory orderInfoRepsitory, GroceryItemsRepository groceryItemsRepository){
        this.groceryItemsRepository = groceryItemsRepository;
        this.orderInfoRepsitory = orderInfoRepsitory;
    }


    public void placeOrder(List<OrderGroceryRequest> request) {
        if (request.isEmpty()) {
            log.warn("Received an empty order request.");
            return;
        }

        try {
            // Manage inventory and collect updated GroceryItems
            Map<Integer, GroceryItems> processedGroceryItems = request.stream()
                    .collect(Collectors.toMap(
                            OrderGroceryRequest::getGroceryItemId,
                            item -> manageInventory(item.getGroceryItemId(), item.getQuantity()),
                            (existing, replacement) -> existing
                    ));

            log.info("inventory items: {}", processedGroceryItems);

            // Create order items
            List<OrderItems> orderItems = request.stream()
                    .map(item -> {
                        GroceryItems groceryItem = processedGroceryItems.get(item.getGroceryItemId());
                        if (groceryItem == null) {
                            log.error("Grocery item with ID {} not found in processed items.", item.getGroceryItemId());
                            throw new GroceryNotFoundException("Grocery item not found after inventory update");
                        }
                        return OrderItems.builder()
                                .groceryItem(groceryItem)
                                .quantity(item.getQuantity())
                                .build();
                    })
                    .toList();

            log.info("orderItems: {}", orderItems);

            // Save order if items exist
            if (!orderItems.isEmpty()) {
                OrderInfo orderInfo = new OrderInfo(orderItems);
                orderInfoRepsitory.save(orderInfo);
                log.info("Order placed successfully with {} items.", orderItems.size());
            } else {
                log.warn("No valid order items found after inventory update.");
            }
        } catch (Exception e) {
            log.error("Error occurred while processing order: {}", e.getMessage(), e);
            String msg = e.getMessage();
            throw new OrderProcessingException("Failed to place order due to an internal error:"+ msg);
        }
    }




    private GroceryItems manageInventory(int groceryItemId, int quantity) {
        Optional<GroceryItems> groceryItemOptional = groceryItemsRepository.findById(groceryItemId);

        if (groceryItemOptional.isEmpty()) {
            throw new GroceryNotFoundException("Request GroceryID is OUT OF STOCK");
        }

        GroceryItems groceryItem = groceryItemOptional.get();
        Integer groceryItemQuantity = groceryItem.getQuantity();

        if (quantity > groceryItemQuantity) {
            throw new InsufficientInventoryException("Ordered quantity is more than available quantity.");
        }

        groceryItem.setQuantity(groceryItemQuantity - quantity);
        return groceryItemsRepository.save(groceryItem);
    }
}
