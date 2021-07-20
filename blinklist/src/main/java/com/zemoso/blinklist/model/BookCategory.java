package com.zemoso.blinklist.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_category_id")
    private Integer bookCategoryId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
