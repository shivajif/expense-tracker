package com.shiva.expense_tracker.service;


import com.shiva.expense_tracker.entity.User;
import com.shiva.expense_tracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;



    public User saveUser(User user){


        String encodedPassword =
                passwordEncoder.encode(
                        user.getPassword()
                );


        user.setPassword(encodedPassword);



        return userRepository.save(user);


    }
    public User getUserByEmail(String email){


        return userRepository.findByEmail(email);

    }


}