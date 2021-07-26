package com.zemoso.blinklist.service;

import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.repository.BookRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Autowired
    @InjectMocks
    private BookCategoryService bookCategoryService;
    private Book book;

    /*@BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }*/

}
