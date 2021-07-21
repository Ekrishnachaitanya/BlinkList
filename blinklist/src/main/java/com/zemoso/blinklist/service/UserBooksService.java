package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.UserResponse;
import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.User;
import com.zemoso.blinklist.model.UserLibrary;
import com.zemoso.blinklist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserBooksService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Override
    public UserResponse getReadingBooksBasedOnUser(Integer userId) {
        User user = userRepository.getById(userId);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(userId);
        List<UserLibrary> userLibraries = new ArrayList<>(user.getUserLibrary()).stream().filter(userLibrary -> !userLibrary.getCompleted()).collect(Collectors.toList());
        userResponse.setUserLibrary(userLibraries);
        return userResponse;
    }

    @Override
    public UserResponse getFinishedBooksBasedOnUser(Integer userId) {
        User user = userRepository.getById(userId);
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(userId);
        List<UserLibrary> userLibraries = new ArrayList<>(user.getUserLibrary()).stream().filter(userLibrary -> userLibrary.getCompleted()).collect(Collectors.toList());
        userResponse.setUserLibrary(userLibraries);
        return userResponse;
    }

    @Override
    public boolean addBookToUsersLibrary(Integer userId, Integer bookId) {
        Book book = bookService.getBookDetails(bookId);
        User user = userRepository.getById(userId);
        UserLibrary userLibrary = new UserLibrary();
        userLibrary.setBook(book);
        userLibrary.setCompleted(false);
        return user.getUserLibrary().add(userLibrary);
    }
}
