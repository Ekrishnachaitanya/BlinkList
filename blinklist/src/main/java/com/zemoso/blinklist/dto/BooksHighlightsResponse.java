package com.zemoso.blinklist.dto;

import com.zemoso.blinklist.model.Book;
import com.zemoso.blinklist.model.BookHighlights;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BooksHighlightsResponse {
    private Integer bookId;
    private String title;
    private Set<BookHighlights> bookHighlights;

    public BooksHighlightsResponse(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.bookHighlights = book.getBookHighlights();
    }
}
