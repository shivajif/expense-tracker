package com.shiva.expense_tracker.service;

import com.shiva.expense_tracker.dto.ProfileResponse;
import com.shiva.expense_tracker.dto.UpdateProfileRequest;
import com.shiva.expense_tracker.entity.User;
import com.shiva.expense_tracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    // ==========================================
    // Get Profile
    // ==========================================

    public ProfileResponse getProfile(String email){

        User user =
                userRepository.findByEmail(email);

        if(user == null){

            throw new RuntimeException(
                    "User Not Found"
            );

        }

        return new ProfileResponse(

                user.getId(),

                user.getName(),

                user.getEmail()

        );

    }



    // ==========================================
    // Update Profile
    // ==========================================

    public ProfileResponse updateProfile(

            String currentEmail,

            UpdateProfileRequest request

    ){

        User user =
                userRepository.findByEmail(currentEmail);

        if(user == null){

            throw new RuntimeException(
                    "User Not Found"
            );

        }



        // Check Email Already Exists

        User existingUser =
                userRepository.findByEmail(
                        request.getEmail()
                );

        if(existingUser != null &&
                existingUser.getId() != user.getId()){
            throw new RuntimeException(
                    "Email Already Exists"
            );

        }



        // Update Name

        user.setName(
                request.getName()
        );



        // Update Email

        user.setEmail(
                request.getEmail()
        );



        // Update Password (Optional)

        if(request.getPassword() != null &&
                !request.getPassword().trim().isEmpty()){

            user.setPassword(

                    passwordEncoder.encode(

                            request.getPassword()

                    )

            );

        }



        userRepository.save(user);



        return new ProfileResponse(

                user.getId(),

                user.getName(),

                user.getEmail()

        );

    }

}