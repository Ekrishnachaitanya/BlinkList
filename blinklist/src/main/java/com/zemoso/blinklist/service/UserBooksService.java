package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.UserResponse;
import com.zemoso.blinklist.model.User;
import com.zemoso.blinklist.model.UserLibrary;
import com.zemoso.blinklist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserBooksService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse getBooksBasedOnUser(Integer userId) {
        User user = userRepository.getById(userId);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(userId);
        List<UserLibrary> userLibrary = new ArrayList<>(user.getUserLibrary());
        userResponse.setUserLibrary(userLibrary);
        return userResponse;
    }
}
