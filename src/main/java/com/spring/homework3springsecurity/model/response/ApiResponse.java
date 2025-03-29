package com.spring.homework3springsecurity.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponse <T> {
    private String message;
    private T payload;
    private String status;
    private LocalDateTime timestamp;
}
