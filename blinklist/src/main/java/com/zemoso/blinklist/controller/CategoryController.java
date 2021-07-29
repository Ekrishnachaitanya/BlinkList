package com.zemoso.blinklist.controller;

import com.zemoso.blinklist.dto.CategoryRequest;
import com.zemoso.blinklist.dto.CategoryResponse;
import com.zemoso.blinklist.exception.CategoryNotFoundException;
import com.zemoso.blinklist.model.Category;
import com.zemoso.blinklist.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/subjects")
    public CategoryResponse addCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.addCategory(new Category(categoryRequest));
    }


    @GetMapping(value = "/subjects/{category}")
    public CategoryResponse getBooksByCategory(@PathVariable("category") String category) throws CategoryNotFoundException {
        return categoryService.getBooksByCategory(category);
    }
}
