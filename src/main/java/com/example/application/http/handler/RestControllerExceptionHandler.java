package com.example.application.http.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.example.application.http.controller")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}
