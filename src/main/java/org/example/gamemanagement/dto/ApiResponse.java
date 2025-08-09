package org.example.gamemanagement.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T body;
}

