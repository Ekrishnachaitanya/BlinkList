package com.zemoso.blinklist.controller;

import com.zemoso.blinklist.dto.UserResponse;
import com.zemoso.blinklist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/books/currently-reading")
    public UserResponse getReadingBooksByUser(@RequestHeader("userId") Integer userId){
        return userService.getReadingBooksBasedOnUser(userId);
    }

    @GetMapping(value = "/books/finished")
    public UserResponse getCompletedBooksByUser(@RequestHeader("userId") Integer userId){
        return userService.getFinishedBooksBasedOnUser(userId);
    }

    @GetMapping(value = "/{id}")
    public boolean addBookToUsersLibrary(@RequestHeader("userId") Integer userId, @PathVariable("id") Integer bookId){
        return userService.addBookToUsersLibrary(userId,bookId);
    }

    @GetMapping(value = "/books/{id}/status")
    public boolean changeBookStatusOfUser(@RequestHeader("userId") Integer userId,@PathVariable("id") Integer bookId){
        return userService.changeStatusOfBook(userId,bookId);
    }

}
