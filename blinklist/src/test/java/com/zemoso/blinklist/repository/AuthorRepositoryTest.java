package com.zemoso.blinklist.repository;


import com.zemoso.blinklist.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author(1,"Jim","Collins","Jim Collins has produced a succession of transformative leadership books, notably Good to Great, Built to Last, How the Mighty Fall, and Great by Choice, which form the centerpiece of every leadership library around the world.");
    }

    @AfterEach
    public void tearDown() {
        authorRepository.deleteAll();
        author = null;
    }

    @Test
    void addingAuthorDetailsTest(){

        authorRepository.save(author);
        Optional<Author> OptionalAuthor= authorRepository.findById(author.getAuthorId());
        if(OptionalAuthor.isPresent()){
            Author fetchedAuthor = OptionalAuthor.get();
            assertEquals(1,fetchedAuthor.getAuthorId());
            assertEquals("Jim", fetchedAuthor.getFirstName());
            assertEquals("Collins",fetchedAuthor.getLastName());
            assertEquals("Jim Collins has produced a succession of transformative leadership books, notably Good to Great, Built to Last, How the Mighty Fall, and Great by Choice, which form the centerpiece of every leadership library around the world.",fetchedAuthor.getAreaOfInterest());
        }
    }

}
