package br.com.alexandreaquiles.casadocodigo.infra.errors.validation;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.util.Assert;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Optional;

import static java.util.Arrays.stream;

public class MustHaveCpfOrCnpjValidator implements ConstraintValidator<MustHaveCpfOrCnpj, Object> {

    @Override
    public void initialize(MustHaveCpfOrCnpj constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Class<?> aClass = value.getClass();
            Optional<Field> possibleCpfField = stream(aClass.getDeclaredFields())
                    .filter(field -> field.getAnnotation(CPF.class) != null)
                    .findFirst();
            Optional<Field> possibleCnpjField = stream(aClass.getDeclaredFields())
                    .filter(field -> field.getAnnotation(CNPJ.class) != null)
                    .findFirst();
            if (possibleCpfField.isPresent() && possibleCnpjField.isPresent()) {
                Field cpfField = possibleCpfField.get();
                cpfField.setAccessible(true);
                Object cpfObject = cpfField.get(value);
                Field cnpjField = possibleCnpjField.get();
                cnpjField.setAccessible(true);
                Object cnpjObject = cnpjField.get(value);
                if (cpfObject == null && cnpjObject == null) {
                    return false;
                }
                if (cpfObject != null && cnpjObject != null) {
                    Assert.isInstanceOf(String.class, cpfObject);
                    Assert.isInstanceOf(String.class, cnpjObject);
                    String cpf = (String) cpfObject;
                    String cnpj = (String) cnpjObject;
                    boolean hasCpf = !cpf.trim().isEmpty();
                    boolean hasCnpj = !cnpj.trim().isEmpty();
                    return hasCpf ^ hasCnpj;
                }
            }
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }
}
