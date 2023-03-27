package com.komleva.controller;

import com.komleva.controller.request.UserCreateRequest;
import com.komleva.domain.PurchaseOffer;
import com.komleva.domain.User;
import com.komleva.service.UserService;
import lombok.RequiredArgsConstructor;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
        //throw new RuntimeException("Huston, we have a problem here!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request) {

        User build = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .gender(request.getGender())
                .email(request.getEmail())
                .phone(request.getPhone())
                .login(request.getLogin())
                .password(request.getPassword())
                .userIP(request.getUserIP())
                .build();

        User createdUser = userService.create(build);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<User> updatePurchaseOffer(@RequestBody UserCreateRequest request) {
        User updateUser = userService.update(User.builder()
                .id(request.getId())
                .name(request.getName())
                .surname(request.getSurname())
                .gender(request.getGender())
                .email(request.getEmail())
                .phone(request.getPhone())
                .login(request.getLogin())
                .password(request.getPassword())
                .userIP(request.getUserIP())
                .build());
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Optional<User>> deletePurchaseOffer(@RequestBody UserCreateRequest request) {
        Optional<User> user = userService.delete(request.getId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

