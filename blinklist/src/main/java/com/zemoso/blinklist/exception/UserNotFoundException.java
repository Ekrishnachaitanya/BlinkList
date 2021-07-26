package com.zemoso.blinklist.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super(" Users isn't found");
    }
}
