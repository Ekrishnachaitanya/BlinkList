package com.zemoso.blinklist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "area_of_interest")
    private String areaOfInterest;

    @OneToMany(mappedBy = "user")
    private Set<UserLibrary> userLibrary;

    @OneToMany(mappedBy = "author")
    private Set<BookAuthor> bookAuthor;
}
