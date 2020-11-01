package br.com.alexandreaquiles.casadocodigo.infra.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors {

    private List<String> globalErrors = new ArrayList<>();
    private List<FieldError> fieldErrors = new ArrayList<>();

    public void addGlobalError(String errorMessage) {
        globalErrors.add(errorMessage);
    }

    public void addFieldError(String field, String errorMessage) {
        fieldErrors.add(new FieldError(field, errorMessage));
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
