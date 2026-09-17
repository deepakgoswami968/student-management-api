package com.lecture16DTO.DTO_crud.DTO;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationExceptionResponseDTO {
    private LocalDateTime time;
    private int statusCode;
    private String error;
    private String meaningmessage;
    private String path;

    public ValidationExceptionResponseDTO(LocalDateTime time,
                                          int statusCode,
                                          String error,
                                          String meaningmessage,
                                          String path,
                                          Map<String, String> fieldError) {
        this.time = time;
        this.statusCode = statusCode;
        this.error = error;
        this.meaningmessage = meaningmessage;
        this.path = path;
        this.fieldError = fieldError;
    }

    public Map<String, String> getFieldError() {
        return fieldError;
    }

    public void setFieldError(Map<String, String> fieldError) {
        this.fieldError = fieldError;
    }

    private Map<String,String> fieldError;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMeaningmessage() {
        return meaningmessage;
    }

    public void setMeaningmessage(String meaningmessage) {
        this.meaningmessage = meaningmessage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
