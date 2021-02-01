package com.example.firsttaskspringboot.parser.impl;

import com.example.firsttaskspringboot.exception.DuplicateFileParserException;
import com.example.firsttaskspringboot.parser.FileParser;
import com.example.firsttaskspringboot.parser.FileParserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FileParserFactoryImpl implements FileParserFactory {

    private final List<FileParser> fileParsers;

    private final Map<String, FileParser> fileParserMap = new HashMap<>();

    @PostConstruct
    private void initFileParserMap() {
        fileParsers.forEach(fileParser -> {
                    if (fileParserMap.containsKey(fileParser.supportExtension())) {
                        throw new DuplicateFileParserException("Duplicate parser");
                    }
                    fileParserMap.put(fileParser.supportExtension(), fileParser);
                }
        );
    }

    @Override
    public FileParser getParser(String extension) {
        return fileParserMap.get(extension);
    }
}
