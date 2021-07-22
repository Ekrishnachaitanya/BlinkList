package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.BookResponse;
import com.zemoso.blinklist.dto.BooksHighlightsResponse;
import com.zemoso.blinklist.dto.CategoryResponse;
import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.repository.BookCategoryRepository;
import com.zemoso.blinklist.repository.BookRepository;
import com.zemoso.blinklist.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookCategoryService implements BookService {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponse getBooksByCategory(String categoryName) {
        return new CategoryResponse(categoryRepository.findByCategoryName(categoryName));
    }

    @Override
    public List<BooksHighlightsResponse> getBookHighlights() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BooksHighlightsResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> getBooksBasedOnSearch(String keyword) {
        List<Book> books = bookRepository.findByTitleContains(keyword);
        if (books != null) {
            books.addAll(bookRepository.findByBookAuthorAuthorFirstNameOrBookAuthorAuthorLastNameContains(keyword, keyword));
            return books.stream().map(BookResponse::new).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public BookResponse getBookDetails(Integer bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.map(BookResponse::new).orElse(null);
    }

    @Override
    public Book getBookById(Integer bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.orElse(null);
    }


}
