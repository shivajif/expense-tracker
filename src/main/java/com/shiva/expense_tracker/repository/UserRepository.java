package com.shiva.expense_tracker.repository;


import com.shiva.expense_tracker.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository
        extends JpaRepository<User,Integer>{


    User findByEmail(String email);


}