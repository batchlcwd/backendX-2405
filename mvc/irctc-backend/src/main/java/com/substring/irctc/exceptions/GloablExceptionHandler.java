package com.substring.irctc.exceptions;

import com.substring.irctc.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

//@ControllerAdvice
@RestControllerAdvice
public class GloablExceptionHandler {

    //pure project ke kie lie: hai
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchException(NoSuchElementException exception) {
        ErrorResponse response
                = new ErrorResponse("Not Found !! " + exception.getMessage(), "404", false);


        ResponseEntity<ErrorResponse> responseResponseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        return responseResponseEntity;

    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ErrorResponse response
                = new ErrorResponse(exception.getMessage(), "404", false);


        ResponseEntity<ErrorResponse> responseResponseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        return responseResponseEntity;

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleSQLIntegrityConstraintViolationException(DataIntegrityViolationException exception) {

        String message = exception.getMessage().contains("Duplicate entry") ? "You are trying to provide fields that are already in database. " : exception.getMessage();

        ErrorResponse response
                = new ErrorResponse(message, "400", false);


        ResponseEntity<ErrorResponse> responseResponseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        return responseResponseEntity;

    }


    //handle method argument not valid exception

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidExceptio(MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, String> errorResponse = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error -> {

            String errorMessage = error.getDefaultMessage();
            String field = ((FieldError) error).getField();
            errorResponse.put(field, errorMessage);


        });


        ResponseEntity<Map<String, String>> error = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return error;


    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {

        ErrorResponse response
                = new ErrorResponse("Request Body is not in proper format", "400", false);

        ResponseEntity<ErrorResponse> error = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return error;

    }



    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException exception) {

        ErrorResponse response
                = new ErrorResponse(exception.getMessage(), "400", false);

        exception.printStackTrace();

        ResponseEntity<ErrorResponse> error = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return error;

    }


    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleSignatureException(JwtException exception) {

        ErrorResponse response
                = new ErrorResponse("Invalid Token " + exception.getMessage(), "400", false);

        exception.printStackTrace();

        ResponseEntity<ErrorResponse> error = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return error;

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {

        ErrorResponse response
                = new ErrorResponse("Something went wrong", "500", false);

        exception.printStackTrace();

        ResponseEntity<ErrorResponse> error = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        return error;

    }


}
