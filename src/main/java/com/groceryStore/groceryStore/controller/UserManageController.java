package com.groceryStore.groceryStore.controller;

import com.groceryStore.groceryStore.model.req.OrderGroceryRequest;
import com.groceryStore.groceryStore.model.resp.GetGroceryIteamResponse;
import com.groceryStore.groceryStore.model.resp.GroceryOrderResponse;
import com.groceryStore.groceryStore.service.GroceryService;
import com.groceryStore.groceryStore.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@Validated
public class UserManageController {

    private final OrderService orderService;
    private final GroceryService groceryService;

    public UserManageController(OrderService orderService, GroceryService groceryService){
        this.groceryService = groceryService;
        this.orderService = orderService;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<GetGroceryIteamResponse>> getGroceryItems(){
        List<GetGroceryIteamResponse> response = groceryService.getGroceryItems();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/order")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<GroceryOrderResponse> placeOrder(
            @RequestBody @Valid List<OrderGroceryRequest> orderGroceryRequest) {
        orderService.placeOrder(orderGroceryRequest);
        return new ResponseEntity<>(GroceryOrderResponse.builder().status("success").build(), HttpStatus.CREATED);
    }
}
