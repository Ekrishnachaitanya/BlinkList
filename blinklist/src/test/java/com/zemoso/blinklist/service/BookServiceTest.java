package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.*;
import com.zemoso.blinklist.model.Author;
import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.BookHighlights;
import com.zemoso.blinklist.model.Category;
import com.zemoso.blinklist.repository.AuthorRepository;
import com.zemoso.blinklist.repository.BookRepository;
import com.zemoso.blinklist.repository.CategoryRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private BookCategoryService bookCategoryService;

    @Mock
    private AuthorRepository authorRepository;

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

    @Test
    void getBooksBasedOnSearchTest(){
        List<Book> books = new ArrayList<>();
        Set<Author> authors1 = new HashSet<>();
        authors1.add(new Author(1,"James","Harden","Science Friction"));
        authors1.add(new Author(2,"Gon","Killua","Manga"));
        Set<Category> bookCategory = new HashSet<>();
        bookCategory.add(new Category(1,"Comedy",null));
        Book book1 = new Book(1,"Example 1","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,bookCategory,null,null,authors1);
        Book book2 = new Book(2,"Example 2","Example description 2","","Example Synopsis 2",200,15, LocalDate.parse("2014-03-01"),1500,true,bookCategory,null,null,authors1);
        books.add(book1);
        books.add(book2);
        Mockito.when(bookRepository.findByTitleContains("Example")).thenReturn(books);
        Mockito.when(bookRepository.findByBookAuthorFirstNameOrBookAuthorLastNameContains("Example","Example")).thenReturn(new ArrayList<>());
        List<BookResponse> bookResponseList = bookCategoryService.getBooksBasedOnSearch("Example");
        assertEquals(2,bookResponseList.size());
        assertEquals("Example 1",bookResponseList.get(0).getTitle());
        assertEquals("Example 2",bookResponseList.get(1).getTitle());
    }

    @Test
    void getBooksByCategoryTest(){
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        Book book1 = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
        Book book2 = new Book(2,"Example 2","Example description 2","","Example Synopsis 2",500,15, LocalDate.parse("2018-03-01"),1400,true,null,null,null,authors);
        Set<Book> books = new HashSet<>();
        books.add(book1);
        books.add(book2);
        Category category = new Category(1,"Drama",books);
        Mockito.when(categoryRepository.findByCategoryName("Drama")).thenReturn(category);
        CategoryResponse categoryResponse = bookCategoryService.getBooksByCategory("Drama");
        assertEquals(2,categoryResponse.getBooks().size());
        assertEquals(1,categoryResponse.getCategoryId());
     }

     @Test
    void getBookDetailsTest(){
         Set<Author> authors = new HashSet<>();
         authors.add(new Author(1,"James","Harden","Science Friction"));
         authors.add(new Author(2,"Gon","Killua","Manga"));
         Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
         Mockito.when(bookRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(book));
         BookResponse bookResponse = bookCategoryService.getBookDetails(1);
         assertEquals("Example",bookResponse.getTitle());
     }

     @Test
    void getBookByIdTest(){
         Set<Author> authors = new HashSet<>();
         authors.add(new Author(1,"James","Harden","Science Friction"));
         authors.add(new Author(2,"Gon","Killua","Manga"));
         Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
         Mockito.when(bookRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(book));
         Book bookResponse = bookCategoryService.getBookById(1);
         assertEquals("Example",bookResponse.getTitle());
     }

     @Test
    void testAddBookDetails(){
        Set<Author> authors = new HashSet<>();
        Author author = new Author(1,"James","Harden","Science Friction");
        authors.add(author);
        Mockito.when(categoryRepository.findByCategoryName(Mockito.anyString())).thenReturn(null);
        Mockito.when(authorRepository.findByFirstNameAndLastName(Mockito.anyString(),Mockito.anyString())).thenReturn(null);
        Mockito.when(authorRepository.save(Mockito.any())).thenReturn(author);
        Set<CategoryRequest> bookCategory = new HashSet<>();
        bookCategory.add(new CategoryRequest("Comedy"));
        Set<Category> categories = new HashSet<>();
        categories.add(new Category(1,"Comedy",null));
        Set<AuthorRequest> authorRequests = new HashSet<>();
        authorRequests.add(new AuthorRequest("James","Harden","Science Friction"));
        BookCategoryRequest bookCategoryRequest = new BookCategoryRequest(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,authorRequests,bookCategory);
        Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,categories,null,null,authors);
        Mockito.when(bookRepository.save(any())).thenReturn(book);
        BookResponse bookResponse = bookCategoryService.addBookDetails(bookCategoryRequest);
        assertEquals("Example",bookResponse.getTitle());
        assertEquals(1,bookResponse.getBookAuthor().size());
     }

     @Test
    void testUpdateBookDetails(){
         Set<Author> authors = new HashSet<>();
         Author author = new Author(1,"James","Harden","Science Friction");
         authors.add(author);
         Mockito.when(categoryRepository.findByCategoryName(Mockito.anyString())).thenReturn(null);
         Mockito.when(authorRepository.findByFirstNameAndLastName(Mockito.anyString(),Mockito.anyString())).thenReturn(null);
         Mockito.when(authorRepository.save(Mockito.any())).thenReturn(author);
         Set<CategoryRequest> bookCategory = new HashSet<>();
         bookCategory.add(new CategoryRequest("Comedy"));
         Set<Category> categories = new HashSet<>();
         categories.add(new Category(1,"Comedy",null));
         Set<AuthorRequest> authorRequests = new HashSet<>();
         authorRequests.add(new AuthorRequest("James","Harden","Science Friction"));
         BookCategoryRequest bookCategoryRequest = new BookCategoryRequest(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,authorRequests,bookCategory);
         Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,categories,null,null,authors);
         Mockito.when(bookRepository.save(any())).thenReturn(book);
         BookResponse bookResponse = bookCategoryService.updateBookDetails(bookCategoryRequest);
         assertEquals("Example",bookResponse.getTitle());
         assertEquals(1,bookResponse.getBookAuthor().size());
     }

     @Test
     void testDeleteBookDetails(){
         Set<Author> authors = new HashSet<>();
         authors.add(new Author(1,"James","Harden","Science Friction"));
         authors.add(new Author(2,"Gon","Killua","Manga"));
         Book book = new Book(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,null,null,null,authors);
         Mockito.when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book));
         bookRepository.deleteById(1);
         assertTrue(bookCategoryService.deleteBookDetails(1));
     }

}
