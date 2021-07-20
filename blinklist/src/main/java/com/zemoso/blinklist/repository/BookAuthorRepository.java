package com.zemoso.blinklist.repository;

import com.zemoso.blinklist.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepository extends JpaRepository<BookAuthor,Integer> {
}
