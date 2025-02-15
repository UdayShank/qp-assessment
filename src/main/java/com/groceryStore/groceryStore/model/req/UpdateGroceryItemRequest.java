package com.groceryStore.groceryStore.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateGroceryItemRequest {
    @JsonProperty("id")
    @NotBlank(message = "Grocery ID can not be blank")
    @NotNull(message = "Grocery can not be null")
    private String id;

    @JsonProperty("name")
    @Nullable
    private String name;

    @JsonProperty("quantity")
    @Nullable
    private Integer quantity;

    @JsonProperty("price")
    @Nullable
    private Double price;
}
