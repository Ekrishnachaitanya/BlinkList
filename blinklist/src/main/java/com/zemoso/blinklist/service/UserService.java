package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.UserResponse;
import com.zemoso.blinklist.exception.BookNotFoundException;
import com.zemoso.blinklist.exception.UserNotFoundException;

public interface UserService {
    UserResponse getReadingBooksBasedOnUser(Integer userId) throws UserNotFoundException;

    UserResponse getFinishedBooksBasedOnUser(Integer userId)throws UserNotFoundException;

    boolean addBookToUsersLibrary(Integer userId, Integer bookId)throws BookNotFoundException;

    boolean changeStatusOfBook(Integer userId, Integer bookId)throws UserNotFoundException;

}
