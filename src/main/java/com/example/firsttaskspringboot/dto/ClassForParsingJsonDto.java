package com.example.firsttaskspringboot.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ClassForParsingJsonDto implements Serializable {

    private List<BaseClassDto> orderList;

}
