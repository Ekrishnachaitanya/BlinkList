package com.zemoso.blinklist.controller;

import com.zemoso.blinklist.dto.BookResponse;
import com.zemoso.blinklist.dto.BooksHighlightsResponse;
import com.zemoso.blinklist.dto.CategoryResponse;
import com.zemoso.blinklist.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/subjects/{category}")
    public CategoryResponse getBooksByCategory(@PathVariable("category") String category){
        return bookService.getBooksByCategory(category);
    }

    @GetMapping(value = "/books/highlights")
    public List<BooksHighlightsResponse> getBooksHighlights(){
        return bookService.getBookHighlights();
    }

    @GetMapping(value = "/books/search")
    public List<BookResponse> getBooksBasedOnKeyword(@RequestParam("keyword") String keyword){
        return bookService.getBooksBasedOnSearch(keyword);
    }

    @GetMapping(value = "/books/{id}")
    public BookResponse getBookDetailsById(@PathVariable("id") Integer id){
        return bookService.getBookDetails(id);
    }

}
