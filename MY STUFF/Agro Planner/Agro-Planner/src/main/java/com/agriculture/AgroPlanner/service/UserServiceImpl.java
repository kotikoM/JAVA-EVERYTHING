package com.agriculture.AgroPlanner.service;

import com.agriculture.AgroPlanner.domain.User;
import com.agriculture.AgroPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private static ResponseEntity<Page<User>> getPageResponseEntity(Page<User> allUsers) {
        return (allUsers.isEmpty()) ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(allUsers);
    }

    public ResponseEntity<User> createUser(User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    public ResponseEntity<Page<User>> retrieveAllUsers(Pageable pageable) {
        Page<User> allUsers = userRepository.findAll(pageable);
        return getPageResponseEntity(allUsers);
    }

    public ResponseEntity<User> retrieveUser(Long userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    public void deleteUser(Long userID) {
        userRepository.deleteById(userID);
    }
}

