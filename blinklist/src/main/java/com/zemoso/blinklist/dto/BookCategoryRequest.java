package com.zemoso.blinklist.dto;

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

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String photoPath;

    @NonNull
    private String synopsis;

    @NonNull
    private Integer totalPages;

    @NonNull
    private Integer approximateMinutes;

    @NonNull
    private LocalDate publishedDate;

    private Integer numberOfReads;

    @NonNull
    private Boolean audioAvailability;

    @NonNull
    private Set<AuthorRequest> bookAuthor;

    @NonNull
    private Set<CategoryRequest> bookCategory;

}
