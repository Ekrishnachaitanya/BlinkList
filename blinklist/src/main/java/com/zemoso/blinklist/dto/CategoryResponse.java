package com.zemoso.blinklist.dto;

import com.zemoso.blinklist.model.Category;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryResponse {
    private Integer categoryId;
    private String categoryName;
    Set<BookResponse> books;
    public CategoryResponse(Category category){
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        if(category.getBooks()!=null)
            this.books = category.getBooks().stream().map(BookResponse::new).collect(Collectors.toSet());
    }
}
