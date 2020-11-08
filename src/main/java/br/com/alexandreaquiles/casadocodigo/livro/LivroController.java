package br.com.alexandreaquiles.casadocodigo.livro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public ListagemDeLivrosResponse lista() {
        String jpql = format("select l from %s l", Livro.class.getSimpleName());
        List<Livro> livros = entityManager.createQuery(jpql, Livro.class).getResultList();
        return new ListagemDeLivrosResponse(livros);
    }

    @GetMapping(path="/{livroId:\\d+}")
    public DetalhesDoLivroResponse detalhe(@PathVariable("livroId") Long livroId) {
        Livro livro = ofNullable(entityManager.find(Livro.class, livroId))
                        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Não foi encontrado um livro com id: %s", livroId)));
        return new DetalhesDoLivroResponse(livro);
    }
}
