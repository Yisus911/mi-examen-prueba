package com.examen.prueba.presentation.advice;

import com.examen.prueba.presentation.dto.ExceptionDTO;
import com.examen.prueba.service.exception.TelefonoNoEncontradoException;
import com.mongodb.MongoWriteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(TelefonoNoEncontradoException.class)
    public ResponseEntity<ExceptionDTO> handleResourceNotFoundException(TelefonoNoEncontradoException ex) {
        return new ResponseEntity<>(ExceptionDTO.builder().code(HttpStatus.NOT_FOUND.value())
                                                .message(ex.getMessage())
                                                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MongoWriteException.class)
    public ResponseEntity<ExceptionDTO> handleResourceNotFoundException(MongoWriteException ex) {
        return new ResponseEntity<>(ExceptionDTO.builder().code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage().contains("message") ?
                        ex.getMessage().substring(ex.getMessage().lastIndexOf("message=")+8) :
                        ex.getMessage()+"madsklfmaldmflkamsdfa")
                .build(), HttpStatus.NOT_FOUND);
    }

}