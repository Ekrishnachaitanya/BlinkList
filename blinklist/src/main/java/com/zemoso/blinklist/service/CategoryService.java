package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.CategoryResponse;
import com.zemoso.blinklist.exception.CategoryNotFoundException;
import com.zemoso.blinklist.model.Category;

public interface CategoryService {
    CategoryResponse getBooksByCategory(String categoryName)throws CategoryNotFoundException;

    CategoryResponse addCategory(Category category);
}
