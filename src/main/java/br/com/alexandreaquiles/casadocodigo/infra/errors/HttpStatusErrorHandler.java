package br.com.alexandreaquiles.casadocodigo.infra.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class HttpStatusErrorHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Errors> handleValidationErrors(ResponseStatusException exception) {
        Errors errors = new Errors();
        errors.addGlobalError(exception.getMessage());
        return ResponseEntity.status(exception.getStatus()).body(errors);
    }

}
