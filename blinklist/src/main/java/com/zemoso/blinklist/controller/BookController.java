package com.zemoso.blinklist.controller;

import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.Category;
import com.zemoso.blinklist.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/{category}")
    public Category getBooksByCategory(@PathVariable("category") String category){
        return bookService.getBooksByCategory(category);
    }

    @GetMapping(value = "/highlights")
    public List<Book> getBooksHighlights(){
        return bookService.getBookHighlights();
    }

    @GetMapping(value = "/explore/{searchKeyword}")
    public List<Book> getBooksBasedOnKeyword(@PathVariable("searchKeyword") String searchKeyword){
        return bookService.getBooksBasedOnSearch(searchKeyword);
    }

    @GetMapping(value = "/books/{id}")
    public Book getBookDetailsById(@PathVariable("id") Integer id){
        return bookService.getBookDetails(id);
    }

}
