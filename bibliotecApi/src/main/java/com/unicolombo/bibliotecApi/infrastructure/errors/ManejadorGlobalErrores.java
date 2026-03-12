package com.unicolombo.bibliotecApi.infrastructure.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ManejadorGlobalErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> manejadorErrorNoEnotrado(EntityNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorValidationData>> manejadorDatosNoValidos(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ErrorValidationData::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> manejadorDeErrorEntegridadDatos(DataIntegrityViolationException e){
        return ResponseEntity.badRequest().body("No es posible realizar la acción ya que incumple con la integridad de datos");
    }

//    @ExceptionHandler(JWTVerificationException.class)
//    public ResponseEntity<?> handleJwt(JWTVerificationException ex) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(Map.of("timestamp", Instant.now(), "error", ex.getMessage()));
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<?> badCredentialsErrorHandler(BadCredentialsException exception){
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(Map.of("error", exception.getMessage()));
//    }

    public record ErrorValidationData(String field, String error){
        public ErrorValidationData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
