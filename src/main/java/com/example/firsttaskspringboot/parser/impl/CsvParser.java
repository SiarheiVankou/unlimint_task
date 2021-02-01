package com.example.firsttaskspringboot.parser.impl;

import com.example.firsttaskspringboot.dto.BaseClassDto;
import com.example.firsttaskspringboot.parser.FileParser;
import com.example.firsttaskspringboot.parser.util.ReturnStringFromBaseClassDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class CsvParser implements FileParser {

    private final ReturnStringFromBaseClassDto returnStringFromBaseClassDto;

    private final Executor parserExecutor = Executors.newCachedThreadPool();

    @Override
    public void parse(String fileName) {

        List<BaseClassDto> baseClassDtoList = new ArrayList<>();
        List<String> fileLines = null;

        try {
            fileLines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileLines != null) {
            for (String fileLine : fileLines) {
                String lineIndex = String.valueOf(fileLines.indexOf(fileLine));
                String[] splitLinesList = fileLine.split(",");

                BaseClassDto baseClassDto = baseClassDtoConverter(splitLinesList, lineIndex);
                baseClassDtoList.add(baseClassDto);
            }
        }

        baseClassDtoList.forEach(baseClassDto ->
                parserExecutor.execute(() ->
                        System.out.println(returnStringFromBaseClassDto.returnLogResultString(baseClassDto, fileName))));
    }

    private BaseClassDto baseClassDtoConverter(String[] strings, String lineIndex) {
        return BaseClassDto.builder()
                .orderId(Long.parseLong(strings[0]))
                .amount(Double.parseDouble(strings[1]))
                .currency(strings[2])
                .comment(strings[3])
                .line(lineIndex)
                .build();
    }

    @Override
    public String supportExtension() {
        return "csv";
    }
}
