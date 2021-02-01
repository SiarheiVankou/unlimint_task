package com.example.firsttaskspringboot.parser;

public interface FileParserFactory {

    FileParser getParser(String extension);
}
