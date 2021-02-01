package com.example.firsttaskspringboot.service;

import com.example.firsttaskspringboot.exception.ExtensionParseException;
import com.example.firsttaskspringboot.exception.FileParserNotFoundException;
import com.example.firsttaskspringboot.parser.FileParser;
import com.example.firsttaskspringboot.parser.FileParserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class UnlimintTaskExecutor implements CommandLineRunner {

    private final Executor cachedExecutor = Executors.newCachedThreadPool();

    private final FileParserFactory fileParserFactory;

    @Override
    public void run(String... args) {

        for (String fileName : args) {
            cachedExecutor.execute(() -> {
                String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (extension.isEmpty()) {
                    throw new ExtensionParseException("Empty extension");
                }

                FileParser fileParser = fileParserFactory.getParser(extension);
                if (fileParser == null) {
                    throw new FileParserNotFoundException("File parser is null");
                }

                try {
                    fileParser.parse(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
