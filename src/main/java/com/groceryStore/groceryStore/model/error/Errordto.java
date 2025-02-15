package com.groceryStore.groceryStore.model.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Errordto {
   private String status;
   private String message;
   private int statusCode;
}
