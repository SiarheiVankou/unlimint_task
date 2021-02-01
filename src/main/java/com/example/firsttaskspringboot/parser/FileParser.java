package com.example.firsttaskspringboot.parser;

import java.io.IOException;

public interface FileParser {

    void parse(String fileName) throws IOException;

    String supportExtension();
}
