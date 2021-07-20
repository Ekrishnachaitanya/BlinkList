package com.zemoso.blinklist.service;

import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.Category;
import com.zemoso.blinklist.repository.BookCategoryRepository;
import com.zemoso.blinklist.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCategoryService implements BookService{

    @Autowired
    private BookCategoryRepository bookCategoryRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getBooksByCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
}
