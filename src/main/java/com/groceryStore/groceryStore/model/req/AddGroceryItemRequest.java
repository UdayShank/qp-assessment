package com.groceryStore.groceryStore.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.NamedStoredProcedureQueries;
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
public class AddGroceryItemRequest {
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @Positive
    @JsonProperty("quantity")
    private Integer quantity;

    @NotNull
    @Positive
    @JsonProperty("price")
    private Double price;
}
