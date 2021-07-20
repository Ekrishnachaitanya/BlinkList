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
public class BookHighlights {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_highlights_id")
    private Integer bookHighlightsId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "chapter")
    private Integer chapter;

    @Column(name = "chapter_title")
    private String chapterTitle;

    @Column(name = "chapter_highlight")
    private String chapterHighlight;

}
