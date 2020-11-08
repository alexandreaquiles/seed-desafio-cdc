package br.com.alexandreaquiles.casadocodigo.infra.errors;

public class FieldError {

    private final String field;
    private final String errorMessage;

    public FieldError(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
