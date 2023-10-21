package me.dio.domain.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException businessException){
        return new ResponseEntity<>(businessException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handle(NoSuchElementException notFoundException){
        return new ResponseEntity<>("Resource ID not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle(MethodArgumentNotValidException ex){
        var error = ex.getFieldErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.stream().map(DataValidationErrors::new).toList());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handle(DataIntegrityViolationException ex){
        return new ResponseEntity("Number is a constraint. Check your id or use another Number.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleUnexpectedException(Throwable unexpectedException){
        var message = "Unexpected server error, see the logs";
        logger.error(message, unexpectedException);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private record DataValidationErrors(String field, String message){
        public DataValidationErrors(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
