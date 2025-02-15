package com.groceryStore.groceryStore.service;

import com.groceryStore.groceryStore.model.req.LoginRequest;
import com.groceryStore.groceryStore.model.resp.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private  final UsersService users;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(
            UsersService users,
            JwtService jwtService
    ) {
        this.users = users;
        this.jwtService = jwtService;
    }

    /**
     * Authenticate the user credentials, Generate the JWT Token and return it along with response
     * @param loginRequest represents user credentials
     * @return AuthenticationResponse with jwt token
     */
    // Authenticate the user credentials and Generate the JWT Token and return in response
    public AuthenticationResponse authenticate(LoginRequest loginRequest) {
        var userInfo = users.loadUserByUsername(loginRequest.getEmail());

        //get the JWT Token from user details
        String jwtToken = jwtService.generateToken(userInfo);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
