package com.example.project_java.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ApiResponse {
    private int status;
    private String message;
    private Object data;
    private LocalDateTime timestamp;
    private String path;
    private List<String> errors; // Danh sách lỗi (nếu có nhiều lỗi)
}
