package com.shiva.expense_tracker.repository;


import com.shiva.expense_tracker.entity.Category;
import com.shiva.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository
        extends JpaRepository<Category,Integer> {



    List<Category> findByUser(User user);



    Optional<Category> findByIdAndUser(
            int id,
            User user
    );



    boolean existsByNameAndUser(
            String name,
            User user
    );
}