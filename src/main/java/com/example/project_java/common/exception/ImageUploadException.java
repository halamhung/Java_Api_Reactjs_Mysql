package com.example.project_java.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ImageUploadException extends RuntimeException{
    public ImageUploadException(String message){
        super(message);
    }
}
