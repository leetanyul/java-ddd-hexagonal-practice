package com.leetanyul.practice.common.exception;

import com.leetanyul.practice.common.response.ApiResponse;
import com.leetanyul.practice.common.response.ResponseCode;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.fail(ResponseCode.INVALID_ARGUMENT, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.fail(ResponseCode.INTERNAL_ERROR, "예상치 못한 오류가 발생했습니다."));
    }
}
