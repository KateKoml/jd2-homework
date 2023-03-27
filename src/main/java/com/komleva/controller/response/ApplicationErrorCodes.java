package com.komleva.controller.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApplicationErrorCodes {
    SQL_ERROR(10),
    USER_NOT_FOUND(40),
    FATAL_ERROR(1);

    private int codeId;
    public int getCodeId(){
        return codeId;
    }
}
