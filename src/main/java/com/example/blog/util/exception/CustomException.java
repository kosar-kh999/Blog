package com.example.blog.util.exception;

import org.springframework.http.HttpStatus;

public record CustomException(HttpStatus httpStatus, String message) {
}
