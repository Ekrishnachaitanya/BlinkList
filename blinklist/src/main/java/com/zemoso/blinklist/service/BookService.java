package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.BookResponse;
import com.zemoso.blinklist.dto.BooksHighlightsResponse;
import com.zemoso.blinklist.dto.CategoryResponse;
import com.zemoso.blinklist.model.Book;

import java.util.List;

public interface BookService {
    CategoryResponse getBooksByCategory(String categoryName);

    List<BooksHighlightsResponse> getBookHighlights();

    List<BookResponse> getBooksBasedOnSearch(String searchKeyword);

    BookResponse getBookDetails(Integer bookId);

    Book getBookById(Integer bookId);
}
