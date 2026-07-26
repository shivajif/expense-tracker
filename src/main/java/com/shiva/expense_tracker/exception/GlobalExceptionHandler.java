package com.shiva.expense_tracker.exception;



import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.*;



import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;



@RestControllerAdvice
public class GlobalExceptionHandler {





    // ==========================
    // VALIDATION ERRORS
    // ==========================


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ){


        Map<String,String> errors = new HashMap<>();


        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> {

                            errors.put(
                                    error.getField(),
                                    error.getDefaultMessage()
                            );

                        }
                );



        ErrorResponse response =
                new ErrorResponse(

                        LocalDateTime.now(),

                        400,

                        "Validation Failed",

                        errors.toString(),

                        request.getRequestURI()

                );



        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );

    }







    // ==========================
    // RUNTIME EXCEPTION
    // ==========================


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(
            RuntimeException exception,
            HttpServletRequest request
    ){



        ErrorResponse response =
                new ErrorResponse(

                        LocalDateTime.now(),

                        400,

                        "Bad Request",

                        exception.getMessage(),

                        request.getRequestURI()

                );



        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );

    }








    // ==========================
    // GLOBAL EXCEPTION
    // ==========================


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(
            Exception exception,
            HttpServletRequest request
    ){



        ErrorResponse response =
                new ErrorResponse(

                        LocalDateTime.now(),

                        500,

                        "Internal Server Error",

                        exception.getMessage(),

                        request.getRequestURI()

                );



        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );

    }



}