package com.example.firsttaskspringboot.parser.impl;

import com.example.firsttaskspringboot.parser.FileParser;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

@Component
public class TxtFileParser implements FileParser {

    @Override
    public void parse(String fileName) throws IOException {

        Files.lines(Paths.get(fileName));
        try (FileInputStream inputStream = new FileInputStream(fileName); Scanner sc = new Scanner(inputStream, "UTF-8")) {
            while (sc.hasNextLine()) {
                int lineIndex = 0;
                lineIndex++;
                String line = sc.nextLine();
                int finalLineIndex = lineIndex;
                System.out.println(parseLineFromScanner(line, String.valueOf(finalLineIndex), fileName));
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        }
    }

    public String parseLineFromScanner(String line, String lineIndex, String fileName) {
        String resultLine = line.replace("{", "");
        resultLine = resultLine.replace("}", "");
        String[] resultStringMass = resultLine.split(",");
        return "{id:" + resultStringMass[0].substring(resultStringMass[0].lastIndexOf(":") + 1)
                + ", amount:" + resultStringMass[1].substring(resultStringMass[1].lastIndexOf(":") + 1)
                + ", comment:" + resultStringMass[2].substring(resultStringMass[2].lastIndexOf(":") + 1)
                + ", filename:" + fileName
                + ", line:" + lineIndex
                + ", result:OK}";
    }

    @Override
    public String supportExtension() {
        return "txt";
    }
}
