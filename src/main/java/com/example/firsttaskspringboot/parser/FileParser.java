package com.example.firsttaskspringboot.parser;

import java.io.FileNotFoundException;

public interface FileParser {

    void parse(String fileName);
    String supportExtension();
}
