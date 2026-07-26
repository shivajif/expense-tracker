package com.shiva.expense_tracker.controller;
import org.springframework.security.core.Authentication;

import com.shiva.expense_tracker.entity.User;
import com.shiva.expense_tracker.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class UserController {



    @Autowired
    private UserService userService;



    @PostMapping("/users")
    public User saveUser(
            @Valid @RequestBody User user
    ){

        return userService.saveUser(user);

    }
    @GetMapping("/users/profile")
    public User getProfile(
            Authentication authentication
    ){

        String email =
                authentication.getName();


        return userService.getUserByEmail(email);

    }


}