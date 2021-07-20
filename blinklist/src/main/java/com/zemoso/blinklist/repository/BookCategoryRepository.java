package com.zemoso.blinklist.repository;

import com.zemoso.blinklist.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
}
