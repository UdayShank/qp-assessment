package com.groceryStore.groceryStore.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groceryStore.groceryStore.model.req.AddGroceryItemRequest;
import com.groceryStore.groceryStore.model.resp.GetGroceryIteamResponse;
import com.groceryStore.groceryStore.repository.entity.GroceryItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroceryIteamMapper {

    private final ObjectMapper objectMapper;

    @Autowired
    public GroceryIteamMapper(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public GroceryItems mapToGroceryItems(AddGroceryItemRequest addGroceryItemRequest){
        return objectMapper.convertValue(addGroceryItemRequest, GroceryItems.class);
    }

    public GetGroceryIteamResponse mapToGroceryItemResponse(GroceryItems groceryItems){
        return objectMapper.convertValue(groceryItems, GetGroceryIteamResponse.class);
    }

}
