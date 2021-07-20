package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.UserResponse;

public interface UserService {
    public UserResponse getBooksBasedOnUser(Integer userId);
}
