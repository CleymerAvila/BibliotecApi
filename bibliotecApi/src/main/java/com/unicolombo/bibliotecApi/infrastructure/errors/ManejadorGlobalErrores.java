package com.unicolombo.bibliotecApi.infrastructure.errors;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.unicolombo.bibliotecApi.infrastructure.errors.exceptions.ValidacionDeLogicaDeNegocioException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ManejadorGlobalErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorDto> manejadorErrorNoEncontrado(
            EntityNotFoundException ex,
            HttpServletRequest request){
        return  buildResponse(HttpStatus.NOT_FOUND, ex, request);
    }

    @ExceptionHandler(ValidacionDeLogicaDeNegocioException.class)
    public ResponseEntity<ApiErrorDto> manejadorErrorNegocio(
            ValidacionDeLogicaDeNegocioException ex,
            HttpServletRequest request){

        return buildResponse(HttpStatus.BAD_REQUEST, ex, request);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDto> manejadorDatosNoValidos(
                        MethodArgumentNotValidException e,
                        HttpServletRequest request){
        var errors = e.getFieldErrors().stream().map(ErrorValidationData::new).toList();

        Map<String, String> errores = new HashMap<>();

        e.getBindingResult().getFieldErrors()
                .forEach( error ->
                        errores.put(error.getField(), error.getDefaultMessage())
                );

        ApiErrorDto apiErrorDto = new ApiErrorDto(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    e.getMessage(),
                    request.getRequestURI()
        );

        apiErrorDto.setErrors(errores);
        return ResponseEntity.badRequest().body(apiErrorDto);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorDto> manejadorDeErrorEntegridadDatos(
            DataIntegrityViolationException exception,
            HttpServletRequest request){

        ApiErrorDto apiErrorDto = new ApiErrorDto(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                "No es posible realizar la acción ya que incumple con la integridad de datos",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorDto);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ApiErrorDto> handleJwt(
            JWTVerificationException ex,
            HttpServletRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ex, request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorDto> badCredentialsErrorHandler(
            BadCredentialsException exception,
            HttpServletRequest request){

        return buildResponse(HttpStatus.UNAUTHORIZED, exception, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> manejadorErrorGeneral(
                                Exception ex,
                                HttpServletRequest request
                            ){
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }
    private ResponseEntity<ApiErrorDto> buildResponse(HttpStatus status, Exception ex, HttpServletRequest request){

        String message = (ex.getMessage() != null && !ex.getMessage().isEmpty())
                            ? ex.getMessage()
                            : "Unexpected Error Ocurred";


        ApiErrorDto apiErrorDto = new ApiErrorDto(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(apiErrorDto);
    }

    public record ErrorValidationData(String field, String error){
        public ErrorValidationData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
