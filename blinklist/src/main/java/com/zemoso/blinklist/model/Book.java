package com.zemoso.blinklist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    private String title;

    private String description;

    @Column(name = "photo_path")
    private String photoPath;

    private String synopsis;

    @Column(name = "total_pages")
    private Integer totalPages;

    @Column(name = "approximate_minutes")
    private Integer approximateMinutes;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "number_of_reads")
    private Integer numberOfReads;

    @Column(name = "audio_availability")
    private Boolean audioAvailability;

    @ManyToMany
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonIgnore
    private Set<Category> bookCategory;

    @OneToMany(mappedBy = "book")
    private Set<BookHighlights> bookHighlights;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<UserLibrary> userLibrary;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> bookAuthor;

}
