package com.utn.productos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {

    // Producto no encontrado
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorResponse> manejarProductoNoEncontrado(ProductoNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Errores de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacion(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("path", request.getDescription(false));

        // Recolectar los mensajes de error de los campos
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        body.put("errores", errores);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // Cualquier otro error no controlado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarGeneral(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor: " + ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


}
