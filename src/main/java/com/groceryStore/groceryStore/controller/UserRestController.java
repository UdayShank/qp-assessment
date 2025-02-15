package com.groceryStore.groceryStore.controller;

import com.groceryStore.groceryStore.model.req.LoginRequest;
import com.groceryStore.groceryStore.model.req.UserRegistrationRequest;
import com.groceryStore.groceryStore.model.resp.AuthenticationResponse;
import com.groceryStore.groceryStore.service.AuthenticationService;
import com.groceryStore.groceryStore.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
public class UserRestController {

    private final AuthenticationService authenticationService;
    private final UsersService usersService;

    public UserRestController(AuthenticationService authenticationService, UsersService usersService){
        this.authenticationService = authenticationService;
        this.usersService = usersService;
    }


    //Endpoint is used to log in the user using email and Password;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest){
        AuthenticationResponse response = authenticationService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody UserRegistrationRequest userRegistrationRequest){
        usersService.registerUser(userRegistrationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
