package com.zemoso.blinklist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.blinklist.dto.BookResponse;
import com.zemoso.blinklist.dto.BooksHighlightsResponse;
import com.zemoso.blinklist.dto.CategoryResponse;
import com.zemoso.blinklist.model.Author;
import com.zemoso.blinklist.model.BookHighlights;
import com.zemoso.blinklist.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void getAllBooksListTest() throws Exception {
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        BookResponse book1 = new BookResponse(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,authors);
        BookResponse book2 = new BookResponse(2,"Example 2","Example description 2","","Example Synopsis 2",500,15, LocalDate.parse("2018-03-01"),1400,true,authors);
        List<BookResponse> bookResponseList = new ArrayList<>();
        bookResponseList.add(book1);
        bookResponseList.add(book2);

        Mockito.when(bookService.getAllBooks()).thenReturn(bookResponseList);
        
        String uri = "/books";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        BookResponse[] booklist = mapFromJson(content, BookResponse[].class);
        assertTrue(booklist.length > 0);
        assertEquals("Example",booklist[0].getTitle());
        assertEquals("Example 2",booklist[1].getTitle());
    }

    @Test
    void getBooksByCategoryTest() throws Exception {
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1,"James","Harden","Science Friction"));
        authors.add(new Author(2,"Gon","Killua","Manga"));
        BookResponse book1 = new BookResponse(1,"Example","Example description","","Example Synopsis",300,20, LocalDate.parse("2019-03-01"),1500,true,authors);
        BookResponse book2 = new BookResponse(2,"Example 2","Example description 2","","Example Synopsis 2",500,15, LocalDate.parse("2018-03-01"),1400,true,authors);
        Set<BookResponse> bookResponses = new HashSet<>();
        bookResponses.add(book1);
        bookResponses.add(book2);
        CategoryResponse categoryResponse = new CategoryResponse(1,"Drama",bookResponses);

        Mockito.when(bookService.getBooksByCategory("Drama")).thenReturn(categoryResponse);

        String uri = "/subjects/Drama";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        CategoryResponse categoryBooks = mapFromJson(content, CategoryResponse.class);
        assertEquals("Drama",categoryBooks.getCategoryName());
        assertEquals(2,categoryBooks.getBooks().size());
    }

    @Test
    void getBookHighlightsTest() throws Exception {
        Set<BookHighlights> booksHighlights = new HashSet<>();
        BookHighlights bookHighlights1 = new BookHighlights(1,null,1,"Introduction","Introduction Highlight");
        BookHighlights bookHighlights2 = new BookHighlights(2,null,2,"Beginning of journey","Beginning of journey Highlight");
        booksHighlights.add(bookHighlights1);
        booksHighlights.add(bookHighlights2);
        BooksHighlightsResponse booksHighlightsResponse = new BooksHighlightsResponse(1,"Example",booksHighlights);
        List<BooksHighlightsResponse> booksHighlightsResponses = new ArrayList<>();
        booksHighlightsResponses.add(booksHighlightsResponse);
        Mockito.when(bookService.getBookHighlights()).thenReturn(booksHighlightsResponses);

        String uri = "/books/highlights";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        BooksHighlightsResponse[] booksHighlightsResponses1 = mapFromJson(content, BooksHighlightsResponse[].class);
        assertTrue(booksHighlightsResponses1.length > 0);
        assertEquals("Example",booksHighlightsResponses1[0].getTitle());
        assertEquals(2,booksHighlightsResponses1[0].getBookHighlights().size());
    }



}
