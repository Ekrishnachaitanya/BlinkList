package com.zemoso.blinklist.service;

import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.Category;

import java.util.List;

public interface BookService {
    Category getBooksByCategory(String categoryName);

    List<Book> getBookHighlights();

    List<Book> getBooksBasedOnSearch(String searchKeyword);

    Book getBookDetails(Integer bookId);
}
