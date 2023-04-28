package com.vmware.bookmanagement.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String ex)
    {
        super(ex);
    }
}
