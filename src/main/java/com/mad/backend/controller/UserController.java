package com.mad.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mad.backend.model.*;
import com.mad.backend.repo.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<MyResponse> saveUser(@RequestBody User user) {
        User userSaved = userRepository.save(user);

        MyResponse response = new MyResponse();
        response.setMessage("User successfully created!");
        response.setData(userSaved);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MyResponse> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update fields with non-null values from updatedUser
            if (updatedUser.getUsername() != null) {
                existingUser.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getFirstName() != null) {
                existingUser.setFirstName(updatedUser.getFirstName());
            }
            if (updatedUser.getLastName() != null) {
                existingUser.setLastName(updatedUser.getLastName());
            }
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(updatedUser.getPassword());
            }

            User updated = userRepository.save(existingUser);

            MyResponse response = new MyResponse();
            response.setMessage("User with ID: " + id + " has been successfully updated!");
            response.setData(updated);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            MyResponse response = new MyResponse();
            response.setMessage("User with ID: " + id + " not found!");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MyResponse> removeUser(@PathVariable String id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);

            MyResponse response = new MyResponse();
            response.setMessage("User with ID " + id + " has been deleted!");

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } else {
            MyResponse response = new MyResponse();
            response.setMessage("User with ID " + id + " not found!");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}