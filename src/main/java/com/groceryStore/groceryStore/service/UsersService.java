package com.groceryStore.groceryStore.service;

import com.groceryStore.groceryStore.model.req.UserRegistrationRequest;
import com.groceryStore.groceryStore.repository.UserInformationRepository;
import com.groceryStore.groceryStore.repository.entity.Role;
import com.groceryStore.groceryStore.repository.entity.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsersService implements UserDetailsService {

    private final UserInformationRepository userInformationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UserInformationRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userInformationRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInformation> user = userInformationRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid Username");
        }

        return user.get();
    }

    @Transactional
    public void registerUser(UserRegistrationRequest userRegistrationRequest) {
        var userInfo = UserInformation.builder()
                .firstName(userRegistrationRequest.getFirstName())
                .lastName(userRegistrationRequest.getLastName())
                .email(userRegistrationRequest.getEmail())
                .password(
                        passwordEncoder.encode(
                                userRegistrationRequest.getPassword()
                        )
                )
                .role(
                        userRegistrationRequest.getRole() != null
                                ? userRegistrationRequest.getRole()
                                : Role.USER
                )
                .build();
        userInformationRepository.save(userInfo);
    }
}
