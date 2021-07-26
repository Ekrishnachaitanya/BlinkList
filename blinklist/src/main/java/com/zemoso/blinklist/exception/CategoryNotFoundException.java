package com.zemoso.blinklist.exception;

public class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(){
        super(" Given Category not found");
    }
}
