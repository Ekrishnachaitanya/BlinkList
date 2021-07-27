package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.UserResponse;
import com.zemoso.blinklist.model.Author;
import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.User;
import com.zemoso.blinklist.model.UserLibrary;
import com.zemoso.blinklist.repository.UserLibraryRepository;
import com.zemoso.blinklist.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserLibraryRepository userLibraryRepository;

    @Mock
    private BookService bookService;

    @InjectMocks
    private UserBooksService userBooksService;

    @Test
    void getReadingBooksBasedOnUserTest(){
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
        UserLibrary userLibrary = new UserLibrary(1,book,null,false);
        Set<UserLibrary> userLibraries = new HashSet<>();
        userLibraries.add(userLibrary);
        User user=new User(1,"Eric","Blots","ericblots.example.com",userLibraries);
        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        UserResponse userResponse = userBooksService.getReadingBooksBasedOnUser(1);
        assertEquals(1,userResponse.getUserLibrary().size());
        List<UserLibrary> outputUserLibraries = userResponse.getUserLibrary();
        for(UserLibrary library: outputUserLibraries){
            assertEquals(false,library.getCompleted());
        }
    }

    @Test
    void getFinishedBooksBasedOnUserTest(){
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
        UserLibrary userLibrary = new UserLibrary(1,book,null,true);
        Set<UserLibrary> userLibraries = new HashSet<>();
        userLibraries.add(userLibrary);
        User user=new User(1,"Eric","Blots","ericblots.example.com",userLibraries);
        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        UserResponse userResponse = userBooksService.getFinishedBooksBasedOnUser(1);
        assertEquals(1,userResponse.getUserLibrary().size());
        List<UserLibrary> outputUserLibraries = userResponse.getUserLibrary();
        for(UserLibrary library: outputUserLibraries){
            assertEquals(true,library.getCompleted());
        }
    }

    @Test
    void TestAddBookToUsersLibrary(){
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
        Mockito.when(bookService.getBookById(1)).thenReturn(book);
        User user=new User(1,"Eric","Blots","ericblots.example.com",null);
        UserLibrary userLibrary = new UserLibrary(1,book,user,false);
        Mockito.when(userLibraryRepository.save(Mockito.any())).thenReturn(userLibrary);
        assertTrue(userBooksService.addBookToUsersLibrary(1, 1));
    }

    @Test
    void TestChangeStatusOfBook(){
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
        UserLibrary userLibrary = new UserLibrary(1,book,null,false);
        Set<UserLibrary> userLibraries = new HashSet<>();
        userLibraries.add(userLibrary);
        User user=new User(1,"Eric","Blots","ericblots.example.com",userLibraries);
        Mockito.when(userRepository.findById(1)).thenReturn(java.util.Optional.of(user));
        Mockito.when(userLibraryRepository.save(Mockito.any())).thenReturn(userLibrary);
        assertTrue(userBooksService.changeStatusOfBook(1, 1));
    }

}
