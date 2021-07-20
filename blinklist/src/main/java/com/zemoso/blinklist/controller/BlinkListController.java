package com.zemoso.blinklist.controller;

import com.zemoso.blinklist.dto.UserResponse;
import com.zemoso.blinklist.model.Category;
import com.zemoso.blinklist.service.BookService;
import com.zemoso.blinklist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class BlinkListController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{category}")
    public Category getBooksByCategory(@PathVariable("category") String category){
        return bookService.getBooksByCategory(category);
    }

    @GetMapping(value = "/library")
    public UserResponse getBooksByUser(@RequestParam("id") Integer id){
        return userService.getBooksBasedOnUser(id);
    }

}
