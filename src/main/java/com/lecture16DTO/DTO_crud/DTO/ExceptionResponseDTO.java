package com.lecture16DTO.DTO_crud.DTO;

import java.time.LocalDateTime;

public class ExceptionResponseDTO {
    private LocalDateTime time;
    private int statusCode;
    private String error;
    private String meaningmessage;
    private String path;

    public ExceptionResponseDTO(LocalDateTime time, int statusCode, String error, String meaningmessage, String path) {
        this.time = time;
        this.statusCode = statusCode;
        this.error = error;
        this.meaningmessage = meaningmessage;
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }



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
