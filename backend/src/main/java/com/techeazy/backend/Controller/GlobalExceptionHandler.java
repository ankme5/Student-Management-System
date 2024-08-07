package com.techeazy.backend.Controller;

import com.techeazy.backend.Entity.CustomResponse;
import com.techeazy.backend.Exception.MissingFieldException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class GlobalExceptionHandler {


//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<CustomResponse<String>> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
//        CustomResponse<String> response = new CustomResponse<>();
//        response.setMessage(ex.getMessage());
//        response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
//        response.setData(null);
//
//        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<CustomResponse<String>> expiredJwtException(ExpiredJwtException ex, WebRequest request) {
//        CustomResponse<String> response = new CustomResponse<>();
//        response.setMessage("JWT token has expired");
//        response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
//        response.setData(null);
//        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<CustomResponse<String>> handleAllExceptions(Exception ex, WebRequest request) {
//        CustomResponse<String> response = new CustomResponse<>();
//        response.setMessage(ex.getMessage());
//        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
