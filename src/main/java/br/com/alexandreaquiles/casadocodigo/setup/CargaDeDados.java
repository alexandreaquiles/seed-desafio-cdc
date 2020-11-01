package br.com.alexandreaquiles.casadocodigo.setup;

import br.com.alexandreaquiles.casadocodigo.autor.Autor;
import br.com.alexandreaquiles.casadocodigo.categoria.Categoria;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class CargaDeDados implements ApplicationRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        entityManager.persist(new Autor("Alberto Souza", "email@quejaexiste.com", "Alberto, o Dev Eficiente, criador do Cognitive-Driven Development (CDD)."));
        entityManager.persist(new Categoria("Categoria que já existe"));
    }

}
