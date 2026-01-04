package com.lcwd.user.service.controller;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userServices;

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userCreated = userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    //single user get
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user = userServices.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //all user get

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userServices.getAllUser();
        return ResponseEntity.ok(users);
    }
}
