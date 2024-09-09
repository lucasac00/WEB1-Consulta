package org.consulta.util;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.Map;


@ControllerAdvice
public class CustomExceptionHandler {

    /*@ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        // Mensagem personalizada para endpoint não encontrado
        return new ResponseEntity<>("Endpoint dessa API não existe", HttpStatus.NOT_FOUND);
    }*/
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Esse Endpoint não foi encontrado");
        response.put("message", "Recurso não localizado. Verifique a URL e tente novamente.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}


/*
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler(value = { ResourceNotFoundException.class })
    // public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("API endpoint not found.");
    // }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        // Aqui você pode retornar a mensagem que quiser, junto com o status HTTP 404
        return new ResponseEntity<>("Endpoint dessa API não existe", HttpStatus.NOT_FOUND);
    }

    // @ExceptionHandler(value = { Exception.class })
    // public ResponseEntity<Object> handleAllExceptions(Exception ex) {
    //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred.");
    // }
}*/