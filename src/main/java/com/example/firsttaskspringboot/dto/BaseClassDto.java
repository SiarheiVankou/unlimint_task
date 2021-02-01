package com.example.firsttaskspringboot.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BaseClassDto implements Serializable {

    private long orderId;
    private double amount;
    private String currency;
    private String comment;
    private String line;

}
