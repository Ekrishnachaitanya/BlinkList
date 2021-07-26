package com.zemoso.blinklist.service;

import com.zemoso.blinklist.dto.BookCategoryRequest;
import com.zemoso.blinklist.dto.BookResponse;
import com.zemoso.blinklist.dto.BooksHighlightsResponse;
import com.zemoso.blinklist.dto.CategoryResponse;
import com.zemoso.blinklist.exception.BookNotFoundException;
import com.zemoso.blinklist.exception.CategoryNotFoundException;
import com.zemoso.blinklist.model.Author;
import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.Category;
import com.zemoso.blinklist.repository.AuthorRepository;
import com.zemoso.blinklist.repository.BookRepository;
import com.zemoso.blinklist.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
public class BookCategoryService implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public CategoryResponse getBooksByCategory(String categoryName)throws CategoryNotFoundException {
        Category category = categoryRepository.findByCategoryName(categoryName);
        if(category==null){
            throw new CategoryNotFoundException();
        }
        return new CategoryResponse(category);
    }

    @Override
    public List<BooksHighlightsResponse> getBookHighlights() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BooksHighlightsResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> getBooksBasedOnSearch(String keyword) {
        List<Book> books = bookRepository.findByTitleContains(keyword);
        if (books != null) {
            log.debug("Found Multiple Books with the given keyword");
            books.addAll(bookRepository.findByBookAuthorFirstNameOrBookAuthorLastNameContains(keyword, keyword));
            return books.stream().map(BookResponse::new).collect(Collectors.toList());
        }
        log.warn(" No book is found with given Keyword");
        return new ArrayList<>();
    }

    @Override
    public BookResponse getBookDetails(Integer bookId)throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new BookNotFoundException();
        }
        return book.map(BookResponse::new).orElse(null);
    }

    @Override
    public Book getBookById(Integer bookId)throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            return book.get();
        }
        throw new BookNotFoundException();
    }

    public Book convertToBook(BookCategoryRequest bookCategoryRequest){
        Book book = new Book();
        book.setBookId(bookCategoryRequest.getBookId());
        if(bookCategoryRequest.getTitle()!=null)
            book.setTitle(bookCategoryRequest.getTitle());
        if(bookCategoryRequest.getDescription()!=null)
            book.setDescription(bookCategoryRequest.getDescription());
        if(bookCategoryRequest.getPhotoPath()!=null)
            book.setPhotoPath(bookCategoryRequest.getPhotoPath());
        if(bookCategoryRequest.getSynopsis()!=null)
            book.setSynopsis(bookCategoryRequest.getSynopsis());
        if(bookCategoryRequest.getTotalPages()!=null)
            book.setTotalPages(bookCategoryRequest.getTotalPages());
        if(bookCategoryRequest.getApproximateMinutes()!=null)
            book.setApproximateMinutes(bookCategoryRequest.getApproximateMinutes());
        if(bookCategoryRequest.getPublishedDate()!=null)
            book.setPublishedDate(bookCategoryRequest.getPublishedDate());
        if(bookCategoryRequest.getNumberOfReads()!=null)
            book.setNumberOfReads(bookCategoryRequest.getNumberOfReads());
        if(bookCategoryRequest.getAudioAvailability()!=null)
            book.setAudioAvailability(bookCategoryRequest.getAudioAvailability());
        if(bookCategoryRequest.getBookCategory()!=null) {
            Set<Category>  categories = bookCategoryRequest.getBookCategory().stream().map(bookCategory->{
                Category category = categoryRepository.findByCategoryName(bookCategory.getCategoryName());
                if(category!=null){
                    log.debug("Inserted Book is from existing category");
                    category.getBooks().add(book);
                    categoryRepository.save(category);
                    return category;
                }else{
                    log.debug("Creating new Category");
                    Category category1=new Category();
                    category1.setCategoryName(bookCategory.getCategoryName());
                    category1.setBooks(new HashSet<>());
                    category1.getBooks().add(book);
                    return categoryRepository.save(category1);
                }
            }).collect(Collectors.toSet());
            book.setBookCategory(categories);
        }
        if(bookCategoryRequest.getBookAuthor()!=null) {
            Set<Author> authors = bookCategoryRequest.getBookAuthor().stream().map(bookAuthor->{
                Author author = authorRepository.findByFirstNameAndLastName(bookAuthor.getFirstName(),bookAuthor.getLastName());
                if(author!=null){
                    log.debug("Inserted another book of existing author");
                    return author;
                }else {
                    Author author1 = new Author();
                    author1.setFirstName(bookAuthor.getFirstName());
                    author1.setLastName(bookAuthor.getLastName());
                    author1.setAreaOfInterest(bookAuthor.getAreaOfInterest());
                    return authorRepository.save(author1);
                }
            }).collect(Collectors.toSet());
            book.setBookAuthor(authors);
        }
        return book;
    }

    @Override
    public boolean addBookDetails(BookCategoryRequest bookCategoryRequest) {
        bookRepository.save(convertToBook(bookCategoryRequest));
        return true;
    }

    @Override
    public boolean updateBookDetails(BookCategoryRequest bookCategoryRequest) {
        log.debug("Updating a Book Details");
        bookRepository.save(convertToBook(bookCategoryRequest));
        return true;
    }

    @Override
    public boolean deleteBookDetails(Integer bookId)throws BookNotFoundException {
        try{
            getBookById(bookId);
        }catch(BookNotFoundException bookNotFoundException){
            throw new BookNotFoundException();
        }
        bookRepository.deleteById(bookId);
        return true;
    }


}
