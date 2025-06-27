package store.politech.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ApiResponse<T> {
    private String message;
    private int statusCode;
    private T data;
    private boolean success;
    private Date timestamp;

    public ApiResponse(String message, int statusCode, T data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
        this.success = statusCode >= 200 && statusCode < 300;
        this.timestamp = new Date();
    }
}
