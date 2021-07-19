package com.zemoso.blinklist.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
