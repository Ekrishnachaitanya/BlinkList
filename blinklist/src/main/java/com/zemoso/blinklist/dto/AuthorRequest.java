package com.zemoso.blinklist.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorRequest {
    private String firstName;
    private String lastName;
    private String areaOfInterest;
}
