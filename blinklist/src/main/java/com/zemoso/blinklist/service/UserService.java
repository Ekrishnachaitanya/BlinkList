package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.UserResponse;

public interface UserService {
    public UserResponse getReadingBooksBasedOnUser(Integer userId);

    public UserResponse getFinishedBooksBasedOnUser(Integer userId);

    public boolean addBookToUsersLibrary(Integer userId,Integer bookId);

}
