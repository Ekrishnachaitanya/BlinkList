package com.zemoso.blinklist.dto;

import com.zemoso.blinklist.model.Author;
import com.zemoso.blinklist.model.Category;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookCategoryRequest {

    private Integer bookId;

    private String title;

    private String description;

    private String photoPath;

    private String synopsis;

    private Integer totalPages;

    private Integer approximateMinutes;

    private LocalDate publishedDate;

    private Integer numberOfReads;

    private Boolean audioAvailability;

    private Set<AuthorRequest> bookAuthor;

    private Set<CategoryRequest> bookCategory;

}
