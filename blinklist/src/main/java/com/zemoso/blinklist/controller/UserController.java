package com.zemoso.blinklist.controller;

import com.zemoso.blinklist.dto.UserResponse;
import com.zemoso.blinklist.exception.BookNotFoundException;
import com.zemoso.blinklist.exception.UserNotFoundException;
import com.zemoso.blinklist.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/books/currently-reading")
    public UserResponse getReadingBooksByUser(@RequestHeader("userId") Integer userId) throws UserNotFoundException {
        try {
            return userService.getReadingBooksBasedOnUser(userId);
        }catch (UserNotFoundException userNotFoundException){
            throw new UserNotFoundException();
        }
    }

    @GetMapping(value = "/books/finished")
    public UserResponse getCompletedBooksByUser(@RequestHeader("userId") Integer userId) throws UserNotFoundException {
        try {
            return userService.getFinishedBooksBasedOnUser(userId);
        }catch (UserNotFoundException userNotFoundException){
            throw new UserNotFoundException();
        }
    }

    @GetMapping(value = "/books/{bookId}")
    public boolean addBookToUsersLibrary(@RequestHeader("userId") Integer userId, @PathVariable("bookId") Integer bookId) throws BookNotFoundException{
    	try {
    		return userService.addBookToUsersLibrary(userId,bookId);
    	}catch(BookNotFoundException bookNotFoundException) {
            throw new BookNotFoundException();
    	}
    }

    @GetMapping(value = "/books/{bookId}/status")
    public boolean changeBookStatusOfUser(@RequestHeader("userId") Integer userId,@PathVariable("bookId") Integer bookId) throws UserNotFoundException {
        try {
            return userService.changeStatusOfBook(userId, bookId);
        }catch (UserNotFoundException userNotFoundException){
            throw new UserNotFoundException();
        }
    }

}
