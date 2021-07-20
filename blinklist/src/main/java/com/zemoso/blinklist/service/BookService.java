package com.zemoso.blinklist.service;

import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.Category;

import java.util.List;

public interface BookService {
    public Category getBooksByCategory(String categoryName);
}
