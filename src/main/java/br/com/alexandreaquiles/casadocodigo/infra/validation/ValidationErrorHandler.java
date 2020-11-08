package br.com.alexandreaquiles.casadocodigo.infra.validation;

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
    public ValidationErrors handleValidationErrors(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return createValidationErrors(bindingResult);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidationErrors handleValidationErrors(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return createValidationErrors(bindingResult);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRelationshipWithEntityException.class)
    public ValidationErrors handleValidationErrors(InvalidRelationshipWithEntityException exception) {
        ValidationErrors validationErrors = new ValidationErrors();
        validationErrors.addFieldError(InvalidRelationshipWithEntityException.FIELD, exception.getMessage());
        return validationErrors;
    }

    private ValidationErrors createValidationErrors(BindingResult bindingResult) {
        ValidationErrors validationErrors = new ValidationErrors();

        bindingResult
                .getGlobalErrors()
                .forEach(globalError -> {
                    String errorMessage = extractErrorMessage(globalError);
                    validationErrors.addGlobalError(errorMessage);
                });

        bindingResult
                .getFieldErrors()
                .forEach(fieldError -> {
                    String field = fieldError.getField();
                    String errorMessage = extractErrorMessage(fieldError);
                    validationErrors.addFieldError(field, errorMessage);
                });

        return validationErrors;
    }

    private String extractErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }

}
