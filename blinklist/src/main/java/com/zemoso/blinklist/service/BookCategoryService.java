package com.zemoso.blinklist.service;

import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.Category;
import com.zemoso.blinklist.repository.BookCategoryRepository;
import com.zemoso.blinklist.repository.BookRepository;
import com.zemoso.blinklist.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCategoryService implements BookService {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getBooksByCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Book> getBookHighlights() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksBasedOnSearch(String searchKeyword) {
        List<Book> books = bookRepository.findByTitleContains(searchKeyword);
        if (books != null) {
            books.addAll(bookRepository.findByBookAuthorAuthorFirstNameOrBookAuthorAuthorLastNameContains(searchKeyword,searchKeyword));
        }
        return books;
    }

    @Override
    public Book getBookDetails(Integer bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.orElse(null);
    }


}
