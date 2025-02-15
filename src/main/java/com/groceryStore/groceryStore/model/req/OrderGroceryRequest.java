package com.groceryStore.groceryStore.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroceryRequest {
    @NotNull
    @JsonProperty("item_id")
    private Integer groceryItemId;

    @NotNull
    @Positive
    @JsonProperty("quantity")
    private Integer quantity;
}
