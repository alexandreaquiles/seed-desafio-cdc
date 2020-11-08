package br.com.alexandreaquiles.casadocodigo.infra.errors.validation;

import br.com.alexandreaquiles.casadocodigo.infra.errors.Errors;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {

    private final MessageSource messageSource;

    public ValidationErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Errors handleValidationErrors(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return createValidationErrors(bindingResult);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Errors handleValidationErrors(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return createValidationErrors(bindingResult);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRelationshipWithEntityException.class)
    public Errors handleValidationErrors(InvalidRelationshipWithEntityException exception) {
        Errors errors = new Errors();
        errors.addFieldError(InvalidRelationshipWithEntityException.FIELD, exception.getMessage());
        return errors;
    }

    private Errors createValidationErrors(BindingResult bindingResult) {
        Errors errors = new Errors();

        bindingResult
                .getGlobalErrors()
                .forEach(globalError -> {
                    String errorMessage = extractErrorMessage(globalError);
                    errors.addGlobalError(errorMessage);
                });

        bindingResult
                .getFieldErrors()
                .forEach(fieldError -> {
                    String field = fieldError.getField();
                    String errorMessage = extractErrorMessage(fieldError);
                    errors.addFieldError(field, errorMessage);
                });

        return errors;
    }

    private String extractErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }

}
