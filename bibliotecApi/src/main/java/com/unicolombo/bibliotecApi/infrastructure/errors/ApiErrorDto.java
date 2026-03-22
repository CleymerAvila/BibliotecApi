package com.unicolombo.bibliotecApi.infrastructure.errors;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public record ApiErrorDto(
        LocalDateTime timestamp,
        int status, String error,
        String message , String path,
        Map<String, String> errors) {


    public ApiErrorDto(int status, String error, String message, String path){
        this(LocalDateTime.now(), status, error, message, path, new HashMap<>());
    }

    void setErrors(Map<String, String> errores){
        this.errors.putAll(errores);
    }
}
