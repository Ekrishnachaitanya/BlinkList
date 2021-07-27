package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.BookResponse;
import com.zemoso.blinklist.dto.BooksHighlightsResponse;
import com.zemoso.blinklist.model.Author;
import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.BookHighlights;
import com.zemoso.blinklist.model.Category;
import com.zemoso.blinklist.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookCategoryService bookCategoryService;

    @Test
    void getAllBooksTest(){
        List<Book> books = new ArrayList<>();
        Set<Author> authors1 = new HashSet<>();
        authors1.add(new Author(1,"James","Harden","Science Friction"));
        authors1.add(new Author(2,"Gon","Killua","Manga"));
        Set<Category> bookCategory = new HashSet<>();
        bookCategory.add(new Category(1,"Comedy",null));
        Book book1 = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,bookCategory,null,null,authors1);
        books.add(book1);
        Mockito.when(bookRepository.findAll()).thenReturn(books);

        //test
        List<BookResponse> bookResponseList = bookCategoryService.getAllBooks();

        assertEquals(1,bookResponseList.size());
        assertEquals("Example",bookResponseList.get(0).getTitle());

        Mockito.verify(bookRepository,Mockito.times(1)).findAll();
    }

    @Test
    void getBookHighlightsTest(){
        List<Book> books = new ArrayList<>();
        Set<Author> authors1 = new HashSet<>();
        authors1.add(new Author(1,"James","Harden","Science Friction"));
        authors1.add(new Author(2,"Gon","Killua","Manga"));
        Set<Category> bookCategory = new HashSet<>();
        bookCategory.add(new Category(1,"Comedy",null));
        Set<BookHighlights> bookHighlights = new HashSet<>();
        bookHighlights.add(new BookHighlights(1,null,1,"Introduction","Introduction Highlight"));
        bookHighlights.add(new BookHighlights(2,null,2,"Ending","Ending Highlight"));
        Book book1 = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,bookCategory,bookHighlights,null,authors1);
        books.add(book1);
        Mockito.when(bookRepository.findAll()).thenReturn(books);
        List<BooksHighlightsResponse> bookHighlightsList = bookCategoryService.getBookHighlights();
        assertEquals(2,bookHighlightsList.get(0).getBookHighlights().size());
    }

}
