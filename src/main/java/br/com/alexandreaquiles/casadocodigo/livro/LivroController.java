package br.com.alexandreaquiles.casadocodigo.livro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public ListagemDeLivrosResponse lista() {
        String jpql = String.format("select l from %s l", Livro.class.getSimpleName());
        List<Livro> livros = entityManager.createQuery(jpql, Livro.class).getResultList();
        return new ListagemDeLivrosResponse(livros);
    }
}
