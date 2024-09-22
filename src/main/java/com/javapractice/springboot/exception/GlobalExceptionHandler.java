package com.javapractice.springboot.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(UserNotFoundException e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false),
                "USER_NOT_FOUND"

        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExist.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(EmailAlreadyExist e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false),
                "EMAIL_ALREADY_EXISTS"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    // eğer diğer excepler dışında bir exceptionb fırlatırsa bu gözükecek.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false),
                "Internal_Server_Error"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach((error) -> {String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);});
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }*/

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
