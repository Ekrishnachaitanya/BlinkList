package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.UserResponse;

public interface UserService {
    UserResponse getReadingBooksBasedOnUser(Integer userId);

    UserResponse getFinishedBooksBasedOnUser(Integer userId);

    boolean addBookToUsersLibrary(Integer userId, Integer bookId);

    boolean changeStatusOfBook(Integer userId, Integer bookId);

}
