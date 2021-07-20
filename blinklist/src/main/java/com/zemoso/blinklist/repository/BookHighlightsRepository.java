package com.zemoso.blinklist.repository;

import com.zemoso.blinklist.model.BookHighlights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookHighlightsRepository extends JpaRepository<BookHighlights, Integer> {
}
