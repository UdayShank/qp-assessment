package com.groceryStore.groceryStore.service;

import com.groceryStore.groceryStore.exception.GroceryNotFoundException;
import com.groceryStore.groceryStore.model.req.AddGroceryItemRequest;
import com.groceryStore.groceryStore.model.req.UpdateGroceryItemRequest;
import com.groceryStore.groceryStore.model.resp.GetGroceryIteamResponse;
import com.groceryStore.groceryStore.repository.GroceryItemsRepository;
import com.groceryStore.groceryStore.repository.entity.GroceryItems;
import com.groceryStore.groceryStore.util.GroceryIteamMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryService {

    private final GroceryItemsRepository groceryItemsRepository;
    private final GroceryIteamMapper groceryIteamMapper;

    public GroceryService(GroceryItemsRepository groceryItemsRepository, GroceryIteamMapper groceryIteamMapper){
        this.groceryItemsRepository = groceryItemsRepository;
        this.groceryIteamMapper = groceryIteamMapper;
    }

    public void addGroceryItem(AddGroceryItemRequest addGroceryItemRequest){
        GroceryItems groceryItems = groceryIteamMapper.mapToGroceryItems(addGroceryItemRequest);
        groceryItemsRepository.save(groceryItems);
    }

    public GetGroceryIteamResponse updateGroceryItems(UpdateGroceryItemRequest updateGroceryItemRequest) {
        int groceryItemId;

        try {
            groceryItemId = Integer.parseInt(updateGroceryItemRequest.getId());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Grocery Item ID: " + updateGroceryItemRequest.getId());
        }

        GroceryItems groceryItems = groceryItemsRepository.findById(groceryItemId)
                .orElseThrow(() -> new GroceryNotFoundException("Item not found in the database"));

        // Update only non-null fields
        if (updateGroceryItemRequest.getName() != null) {
            groceryItems.setName(updateGroceryItemRequest.getName());
        }
        if (updateGroceryItemRequest.getPrice() != null) {
            groceryItems.setPrice(updateGroceryItemRequest.getPrice());
        }
        if (updateGroceryItemRequest.getQuantity() != null) {
            groceryItems.setQuantity(updateGroceryItemRequest.getQuantity());
        }

        GroceryItems updatedGroceryItem = groceryItemsRepository.save(groceryItems);

        return groceryIteamMapper.mapToGroceryItemResponse(updatedGroceryItem);
    }


    public List<GetGroceryIteamResponse> getGroceryItems(){
        List<GroceryItems> groceryItems = groceryItemsRepository.findAll();
        List<GetGroceryIteamResponse> getGroceryIteamResponses = groceryItems.stream().map(this.groceryIteamMapper::mapToGroceryItemResponse).toList();
        return getGroceryIteamResponses;
    }

    public void deleteIteamById(int groceryID){
        Optional<GroceryItems> groceryItems = groceryItemsRepository.findById(groceryID);
        if (groceryItems.isEmpty()){
            throw new GroceryNotFoundException("Invalid Id, Requested Iteam Id is not found");
        }
        groceryItemsRepository.deleteById(groceryID);
    }
}
