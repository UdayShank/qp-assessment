package com.groceryStore.groceryStore.repository;

import com.groceryStore.groceryStore.repository.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInformationRepository extends JpaRepository<UserInformation, String> {
    Optional<UserInformation> findByEmail(String email);
}
