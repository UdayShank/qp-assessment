package com.groceryStore.groceryStore.controller;

import com.groceryStore.groceryStore.model.req.AddGroceryItemRequest;
import com.groceryStore.groceryStore.model.req.UpdateGroceryItemRequest;
import com.groceryStore.groceryStore.model.resp.GetGroceryIteamResponse;
import com.groceryStore.groceryStore.service.GroceryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/admin/grocery")
@PreAuthorize("hasRole('ADMIN')")
@Validated
public class AdminManageController {

    private final GroceryService groceryService;

    @Autowired
    public AdminManageController(GroceryService groceryService){
        this.groceryService = groceryService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public ResponseEntity<Void> addGroceryItems(@RequestBody List<@Valid AddGroceryItemRequest> addGroceryItemRequests) {
        addGroceryItemRequests.forEach(groceryService::addGroceryItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<GetGroceryIteamResponse> updateGroceryItems(@RequestBody @Valid UpdateGroceryItemRequest updateGroceryItemRequest){
        GetGroceryIteamResponse getGroceryIteamResponse = groceryService.updateGroceryItems(updateGroceryItemRequest);
        return new ResponseEntity<>(getGroceryIteamResponse,HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<GetGroceryIteamResponse>> getGroceryItem(){
        List<GetGroceryIteamResponse> getGroceryIteamResponse = groceryService.getGroceryItems();
        return new ResponseEntity<>(getGroceryIteamResponse, HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Void> deleteGroceryItem(@RequestParam("item_id") @Valid Integer Id){
        groceryService.deleteIteamById(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
