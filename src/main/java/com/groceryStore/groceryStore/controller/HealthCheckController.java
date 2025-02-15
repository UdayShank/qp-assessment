package com.groceryStore.groceryStore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("Grocery App is up and running", HttpStatus.OK);
    }
}
