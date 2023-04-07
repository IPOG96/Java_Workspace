package com.coding.Book_API.common;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity handleException(Exception e) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setError("Something went wrong : " + e);

        // apiResponse.setError("Something went wrong : ");
        apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponse);

    }


    @ExceptionHandler
    public ResponseEntity handleBadRequest(BadRequestException b) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(400);
        apiResponse.setError(b.getErrors());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(apiResponse);


    }


    @ExceptionHandler
    public ResponseEntity unauthorizedRequest(AccessDeniedException e) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(apiResponse);


    }


}
