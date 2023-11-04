package com.agriculture.AgroPlanner.controller;

import com.agriculture.AgroPlanner.domain.User;
import com.agriculture.AgroPlanner.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.agriculture.AgroPlanner.constants.Endpoints.USERS_ENDPOINT;
import static com.agriculture.AgroPlanner.constants.Endpoints.USER_ENDPOINT;


@RestController
@RequestMapping(USERS_ENDPOINT)
@SuppressWarnings("unused")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<User> insertUser(
            @RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public ResponseEntity<Page<User>> retrieveAllUsers(Pageable pageable) {
        return userService.retrieveAllUsers(pageable);
    }

    @GetMapping(USER_ENDPOINT)
    public ResponseEntity<User> retrieveUser(
            @PathVariable Long userID) {
        return userService.retrieveUser(userID);
    }

    @DeleteMapping(USER_ENDPOINT)
    public void deleteUser(
            @PathVariable Long userID) {
        userService.deleteUser(userID);
    }
}
