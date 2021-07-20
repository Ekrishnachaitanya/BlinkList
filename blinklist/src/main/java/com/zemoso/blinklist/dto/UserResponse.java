package com.zemoso.blinklist.dto;

import com.zemoso.blinklist.model.UserLibrary;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {
    private Integer userId;
    private List<UserLibrary> userLibrary;
}
