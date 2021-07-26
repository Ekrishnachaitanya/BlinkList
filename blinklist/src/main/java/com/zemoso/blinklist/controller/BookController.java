package com.zemoso.blinklist.controller;

import com.zemoso.blinklist.dto.BookCategoryRequest;
import com.zemoso.blinklist.dto.BookResponse;
import com.zemoso.blinklist.dto.BooksHighlightsResponse;
import com.zemoso.blinklist.dto.CategoryResponse;
import com.zemoso.blinklist.exception.BookNotFoundException;
import com.zemoso.blinklist.exception.CategoryNotFoundException;
import com.zemoso.blinklist.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping(value = "/subjects/{category}")
    public CategoryResponse getBooksByCategory(@PathVariable("category") String category) throws CategoryNotFoundException {
        try{
            return bookService.getBooksByCategory(category);
        }catch(CategoryNotFoundException categoryNotFoundException){
            throw new CategoryNotFoundException();
        }
    }

    @GetMapping(value = "/books/highlights")
    public List<BooksHighlightsResponse> getBooksHighlights(){
        return bookService.getBookHighlights();
    }

    @GetMapping(value = "/books/search")
    public List<BookResponse> getBooksBasedOnKeyword(@RequestParam("keyword") String keyword){
        return bookService.getBooksBasedOnSearch(keyword);
    }

    @GetMapping(value = "/books/{bookId}")
    public BookResponse getBookDetailsById(@PathVariable("bookId") Integer id)throws BookNotFoundException {
        try{
            return bookService.getBookDetails(id);
        }catch (BookNotFoundException bookNotFoundException){
            logger.error("no book");
            throw new BookNotFoundException();
        }
    }

    @PostMapping(value = "/books")
    public ResponseEntity<BookCategoryRequest> addBook(@RequestBody BookCategoryRequest bookCategoryRequest){
        boolean inserted = bookService.addBookDetails(bookCategoryRequest);
        if(inserted){
            log.debug("Inserted a Book");
            return new ResponseEntity<>(bookCategoryRequest, HttpStatus.CREATED);
        }
        log.warn(" Didn't get inserted into database");
        return new ResponseEntity<>(bookCategoryRequest, HttpStatus.EXPECTATION_FAILED);
    }

    @PutMapping(value = "/books/{bookId}")
    public ResponseEntity<BookCategoryRequest> updateBook(@RequestBody BookCategoryRequest bookCategoryRequest){
        boolean updated = bookService.updateBookDetails(bookCategoryRequest);
        if(updated){
            log.debug("Updated a Book");
            return new ResponseEntity<>(bookCategoryRequest,HttpStatus.ACCEPTED);
        }
        log.warn("Didn't get updated into database");
        return new ResponseEntity<>(bookCategoryRequest, HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping(value = "/books/{bookId}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable("bookId") Integer bookId) throws BookNotFoundException {
        boolean deleted = false;
        try {
            deleted = bookService.deleteBookDetails(bookId);
        }catch (BookNotFoundException bookNotFoundException){
            throw new BookNotFoundException();
        }
        if(deleted){
            log.debug("Deleted a Book");
            return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(false,HttpStatus.EXPECTATION_FAILED);
    }

}
