package com.shiva.expense_tracker.controller;


import com.shiva.expense_tracker.entity.Category;
import com.shiva.expense_tracker.service.CategoryService;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/categories")
public class CategoryController {



    @Autowired
    private CategoryService categoryService;





    // CREATE CATEGORY

    @PostMapping
    public Category saveCategory(
            @Valid @RequestBody Category category,
            Authentication authentication
    ){


        return categoryService.saveCategory(
                category,
                authentication.getName()
        );

    }







    // GET ALL CATEGORIES

    @GetMapping
    public List<Category> getAllCategories(
            Authentication authentication
    ){


        return categoryService.getAllCategories(
                authentication.getName()
        );

    }







    // GET CATEGORY BY ID

    @GetMapping("/{id}")
    public Category getCategoryById(
            @PathVariable int id,
            Authentication authentication
    ){


        return categoryService.getCategoryById(
                id,
                authentication.getName()
        );

    }







    // UPDATE CATEGORY

    @PutMapping("/{id}")
    public Category updateCategory(
            @PathVariable int id,
            @Valid @RequestBody Category category,
            Authentication authentication
    ){


        return categoryService.updateCategory(
                id,
                category,
                authentication.getName()
        );

    }







    // DELETE CATEGORY

    @DeleteMapping("/{id}")
    public String deleteCategory(
            @PathVariable int id,
            Authentication authentication
    ){


        categoryService.deleteCategory(
                id,
                authentication.getName()
        );


        return "Category deleted successfully";

    }


}