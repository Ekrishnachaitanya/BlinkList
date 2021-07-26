package com.zemoso.blinklist.exception;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(){
        super(" Book isn't found.");
    }
}
