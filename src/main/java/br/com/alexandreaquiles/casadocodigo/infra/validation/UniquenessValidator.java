package br.com.alexandreaquiles.casadocodigo.infra.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquenessValidator implements ConstraintValidator<Unique, Object> {

    private Class<?> entity;
    private String field;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(Unique unique) {
        entity = unique.entity();
        field = unique.field();
    }

    @Override
    public boolean isValid(Object valueToBeValidated, ConstraintValidatorContext context) {
        Assert.notNull(entityManager, "The entity manager should not be null");
        String jpql = String.format("select count(1) > 0 from %s where %s = :value", entity.getSimpleName(), field);

        boolean existsInDB = entityManager
                .createQuery(jpql, Boolean.class)
                .setParameter("value", valueToBeValidated)
                .getSingleResult();

        return !existsInDB;
    }
}
