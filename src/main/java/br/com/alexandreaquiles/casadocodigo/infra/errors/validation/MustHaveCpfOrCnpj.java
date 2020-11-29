package br.com.alexandreaquiles.casadocodigo.infra.errors.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = MustHaveCpfOrCnpjValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface MustHaveCpfOrCnpj {

    String message() default "{br.com.alexandreaquiles.casadocodigo.infra.errors.validation.MustHaveCpfOrCnpj.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
