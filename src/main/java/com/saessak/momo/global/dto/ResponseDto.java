package com.saessak.momo.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private int status;
    private String message;
    private Object result;

    public ResponseDto() {

    }
}