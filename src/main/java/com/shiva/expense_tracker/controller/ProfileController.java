package com.shiva.expense_tracker.controller;

import com.shiva.expense_tracker.dto.ProfileResponse;
import com.shiva.expense_tracker.dto.UpdateProfileRequest;
import com.shiva.expense_tracker.service.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;



    // ==========================================
    // Get Logged-in User Profile
    // ==========================================

    @GetMapping
    public ProfileResponse getProfile(
            Authentication authentication
    ) {

        return profileService.getProfile(
                authentication.getName()
        );

    }



    // ==========================================
    // Update Profile
    // ==========================================

    @PutMapping
    public ProfileResponse updateProfile(

            @RequestBody
            UpdateProfileRequest request,

            Authentication authentication

    ) {

        return profileService.updateProfile(

                authentication.getName(),

                request

        );

    }

}