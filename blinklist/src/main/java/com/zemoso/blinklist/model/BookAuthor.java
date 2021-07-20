package com.zemoso.blinklist.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book_author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_author_id")
    private Integer bookAuthorId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

}
