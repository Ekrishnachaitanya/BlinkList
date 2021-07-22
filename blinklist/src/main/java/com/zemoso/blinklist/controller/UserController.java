package com.zemoso.blinklist.controller;

import com.zemoso.blinklist.dto.UserResponse;
import com.zemoso.blinklist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/library/reading")
    public UserResponse getReadingBooksByUser(@RequestParam("id") Integer id){
        return userService.getReadingBooksBasedOnUser(id);
    }

    @GetMapping(value = "/library/finished")
    public UserResponse getCompletedBooksByUser(@RequestParam("id") Integer id){
        return userService.getFinishedBooksBasedOnUser(id);
    }

    @GetMapping(value = "/book/{id}")
    public boolean addBookToUsersLibrary(@RequestParam("id") Integer userId,@PathVariable("id") Integer bookId){
        return userService.addBookToUsersLibrary(userId,bookId);
    }

}
