package com.example.firsttaskspringboot.exception;

public class FileParserNotFoundException extends RuntimeException {

    public FileParserNotFoundException(String message) {
        super(message);
    }
}
