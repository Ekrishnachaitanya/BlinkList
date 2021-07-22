package com.zemoso.blinklist.repository;

import com.zemoso.blinklist.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContains(String title);

    List<Book> findByBookAuthorAuthorFirstNameOrBookAuthorAuthorLastNameContains(String firstName, String lastName);
}
