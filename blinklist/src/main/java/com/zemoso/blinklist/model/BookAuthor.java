package com.zemoso.blinklist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore
    private User author;

}
