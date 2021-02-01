package com.example.firsttaskspringboot.parser.impl;

import com.example.firsttaskspringboot.dto.BaseClassDto;
import com.example.firsttaskspringboot.dto.ClassForParsingJsonDto;
import com.example.firsttaskspringboot.parser.FileParser;
import com.example.firsttaskspringboot.parser.util.ReturnStringFromBaseClassDto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JsonParser implements FileParser {

    private final ReturnStringFromBaseClassDto returnStringFromBaseClassDto;

    @Override
    public void parse(String fileName) {

        Gson gson = new Gson();
        ClassForParsingJsonDto classForParsingJsonDto;
        try {
            classForParsingJsonDto = gson.fromJson(new FileReader(fileName), ClassForParsingJsonDto.class);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
            return;
        }

        List<BaseClassDto> gsonBaseClassDtoList = classForParsingJsonDto.getOrderList();
        gsonBaseClassDtoList.forEach(gsonSomeElement -> {
            String lineIndex = String.valueOf(gsonBaseClassDtoList.indexOf(gsonSomeElement));
            System.out.println(returnStringFromBaseClassDto.returnLogResultString(gsonSomeElement, fileName, lineIndex));
        });

    }

    @Override
    public String supportExtension() {
        return "json";
    }
}
