package com.example.demo.controller;


import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserByEmail(email));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user){
        service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<User> deleteUserByEmail(@PathVariable String email){
        User user  = service.getUserByEmail(email);
        service.deleteUser(email);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }
}
