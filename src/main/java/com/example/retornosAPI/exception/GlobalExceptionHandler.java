package com.example.retornosAPI.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Define que este metodo será chamado para tratar ProductNotFoundException.
    // Metodo para manipular exceções específicas do tipo ProductNotFoundException.
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        // Cria um map para armazenar os detalhes do erro.
        Map<String, Object> map = new HashMap<>();
        // Adiciona o timestamp (data e hora do erro).
        map.put("timestamp", LocalDateTime.now());
        // Adiciona o código de status HTTP (404).
        map.put("status", HttpStatus.NOT_FOUND.value());
        // Adiciona uma descrição do erro.
        map.put("error", "Not Found");
        // Adiciona a mensagem da exceção (detalhes do erro).
        map.put("message", ex.getMessage());
        // Adiciona o caminho do endpoint que gerou o erro.
        map.put("path", request.getDescription(false).replace("uri=", ""));

        // Retorna uma resposta HTTP com o corpo do erro e o status 404 (Not Found).
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    //Perceba que a estrutura é sempre a mesma.
    // Metodo para manipular exceções do tipo IllegalArgumentException (erros de validação).
    // Define que este metodo será chamado para tratar IllegalArgumentException.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(IllegalArgumentException ex, WebRequest request) {
        // Cria um map para armazenar os detalhes do erro.
        Map<String, Object> map = new HashMap<>();
        // Adiciona o timestamp (data e hora do erro).
        map.put("timestamp", LocalDateTime.now());
        // Adiciona o código de status HTTP (400).
        map.put("status", HttpStatus.BAD_REQUEST.value());
        // Adiciona uma descrição do erro.
        map.put("error", "Bad Request");
        // Adiciona a mensagem da exceção (detalhes do erro).
        map.put("message", ex.getMessage());
        // Adiciona o caminho do endpoint que gerou o erro.
        map.put("path", request.getDescription(false).replace("uri=", ""));

        // Retorna uma resposta HTTP com o corpo do erro e o status 400 (Bad Request).
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}

