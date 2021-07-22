package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.UserResponse;
import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.User;
import com.zemoso.blinklist.model.UserLibrary;
import com.zemoso.blinklist.repository.UserLibraryRepository;
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

    @Autowired
    private UserLibraryRepository userLibraryRepository;

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
        List<UserLibrary> userLibraries = new ArrayList<>(user.getUserLibrary()).stream().filter(UserLibrary::getCompleted).collect(Collectors.toList());
        userResponse.setUserLibrary(userLibraries);
        return userResponse;
    }

    @Override
    public boolean addBookToUsersLibrary(Integer userId, Integer bookId) {
        Book book = bookService.getBookById(bookId);
        if(book==null){
            return false;
        }
        User user = userRepository.getById(userId);
        UserLibrary userLibrary = new UserLibrary();
        userLibrary.setBook(book);
        userLibrary.setUser(user);
        userLibrary.setCompleted(false);
        userLibraryRepository.save(userLibrary);
        return true;
    }

    @Override
    public boolean changeStatusOfBook(Integer userId, Integer bookId) {
        User user = userRepository.getById(userId);
        Set<UserLibrary> userBooks = user.getUserLibrary();
        for(UserLibrary userLibrary:userBooks){
            if(userLibrary.getBook().getBookId().equals(bookId)){
                userLibrary.setCompleted(true);
                userLibraryRepository.save(userLibrary);
                return true;
            }
        }
        return false;
    }
}
