package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.BookCategoryRequest;
import com.zemoso.blinklist.dto.BookResponse;
import com.zemoso.blinklist.dto.BooksHighlightsResponse;
import com.zemoso.blinklist.dto.CategoryResponse;
import com.zemoso.blinklist.exception.BookNotFoundException;
import com.zemoso.blinklist.exception.CategoryNotFoundException;
import com.zemoso.blinklist.model.Book;

import java.util.List;

@SuppressWarnings("ALL")
public interface BookService {
    CategoryResponse getBooksByCategory(String categoryName)throws CategoryNotFoundException;

    List<BooksHighlightsResponse> getBookHighlights();

    List<BookResponse> getBooksBasedOnSearch(String searchKeyword);

    List<BookResponse> getAllBooks();

    BookResponse getBookDetails(Integer bookId) throws BookNotFoundException;

    Book getBookById(Integer bookId) throws BookNotFoundException;

    BookResponse addBookDetails(BookCategoryRequest bookCategoryRequest);

    BookResponse updateBookDetails(BookCategoryRequest bookCategoryRequest);

    boolean deleteBookDetails(Integer bookId)throws BookNotFoundException;
}
