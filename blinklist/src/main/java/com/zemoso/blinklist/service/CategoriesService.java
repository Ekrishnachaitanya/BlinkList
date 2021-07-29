package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.CategoryResponse;
import com.zemoso.blinklist.exception.CategoryNotFoundException;
import com.zemoso.blinklist.model.Category;
import com.zemoso.blinklist.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponse getBooksByCategory(String categoryName) throws CategoryNotFoundException {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if (category == null) {
            throw new CategoryNotFoundException();
        }
        return new CategoryResponse(category);
    }

    @Override
    public CategoryResponse addCategory(Category category) {
        return new CategoryResponse(categoryRepository.save(category));
    }
}
