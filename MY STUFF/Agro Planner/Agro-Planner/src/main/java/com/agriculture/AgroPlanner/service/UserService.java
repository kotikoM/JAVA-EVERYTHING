package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<User> createUser(User user);

    ResponseEntity<Page<User>> retrieveAllUsers(Pageable pageable);

    ResponseEntity<User> retrieveUser(Long userID);

    void deleteUser(Long userID);
}
