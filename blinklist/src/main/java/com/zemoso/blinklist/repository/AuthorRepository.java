package com.zemoso.blinklist.repository;

import com.zemoso.blinklist.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
