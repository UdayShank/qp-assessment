package com.groceryStore.groceryStore.model.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class GroceryOrderResponse {

    @JsonProperty("status")
    private String status;
}
