package com.shiva.expense_tracker.service;


import com.shiva.expense_tracker.entity.Category;
import com.shiva.expense_tracker.entity.User;
import com.shiva.expense_tracker.repository.CategoryRepository;
import com.shiva.expense_tracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class CategoryService {



    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private UserRepository userRepository;




    // CREATE CATEGORY

    public Category saveCategory(
            Category category,
            String email
    ){


        User user =
                userRepository.findByEmail(email);



        if(user == null){
            throw new RuntimeException(
                    "User not found"
            );
        }



        if(categoryRepository.existsByNameAndUser(
                category.getName(),
                user
        )){

            throw new RuntimeException(
                    "Category already exists"
            );
        }



        category.setUser(user);


        return categoryRepository.save(category);

    }







    // GET ALL


    public List<Category> getAllCategories(
            String email
    ){


        User user =
                userRepository.findByEmail(email);



        if(user == null){
            throw new RuntimeException(
                    "User not found"
            );
        }



        return categoryRepository.findByUser(user);

    }







    // GET BY ID


    public Category getCategoryById(
            int id,
            String email
    ){


        User user =
                userRepository.findByEmail(email);



        return categoryRepository
                .findByIdAndUser(id,user)
                .orElseThrow(
                        ()-> new RuntimeException(
                                "Category not found"
                        )
                );

    }







    // UPDATE


    public Category updateCategory(
            int id,
            Category category,
            String email
    ){


        User user =
                userRepository.findByEmail(email);



        Category existing =
                categoryRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Category not found"
                                )
                        );



        existing.setName(
                category.getName()
        );



        return categoryRepository.save(existing);

    }







    // DELETE


    public void deleteCategory(
            int id,
            String email
    ){


        User user =
                userRepository.findByEmail(email);



        Category category =
                categoryRepository
                        .findByIdAndUser(id,user)
                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Category not found"
                                )
                        );


        categoryRepository.delete(category);

    }

}