package com.example.firsttaskspringboot.parser.util;

import com.example.firsttaskspringboot.dto.BaseClassDto;
import org.springframework.stereotype.Component;

@Component
public class ReturnStringFromBaseClassDto {

    public String returnLogResultString(BaseClassDto baseClassDto, String fileName) {

        return "{id:" + baseClassDto.getOrderId() +
                ", amount:" +
                baseClassDto.getAmount() +
                ", comment:" +
                baseClassDto.getComment() +
                ", filename:" +
                fileName +
                ", line:" +
                baseClassDto.getLine() +
                ", result:OK }";
    }

    public String returnLogResultString(BaseClassDto baseClassDto, String fileName, String line) {

        return "{id:" + baseClassDto.getOrderId() +
                ", amount:" +
                baseClassDto.getAmount() +
                ", comment:" +
                baseClassDto.getComment() +
                ", filename:" +
                fileName +
                ", line:" +
                line +
                ", result:OK }";
    }
}
