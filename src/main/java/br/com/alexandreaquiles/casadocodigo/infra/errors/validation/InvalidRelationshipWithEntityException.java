package br.com.alexandreaquiles.casadocodigo.infra.errors.validation;

public class InvalidRelationshipWithEntityException extends RuntimeException {

    public static final String FIELD = "id";

    public InvalidRelationshipWithEntityException(Class<?> entity, Long id) {
        super(String.format("Invalid relationship with %s: id %s is not valid", entity, id));
    }
}
