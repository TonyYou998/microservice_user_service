package com.uit.user_service.common.repository;

import com.uit.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /// select user
    @Query(value= "SELECT * FROM user  WHERE username = ?1",  nativeQuery=true)
    Optional<User> findByUsernameAndRole(String email);

    User findByUsername(String username);



}
