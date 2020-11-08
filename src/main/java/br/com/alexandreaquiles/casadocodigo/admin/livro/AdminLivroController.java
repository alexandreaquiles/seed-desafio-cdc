package br.com.alexandreaquiles.casadocodigo.admin.livro;

import br.com.alexandreaquiles.casadocodigo.admin.autor.Autor;
import br.com.alexandreaquiles.casadocodigo.admin.categoria.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/livros")
public class AdminLivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping
    public ResponseEntity<NovoLivroResponse> novoLivro(@RequestBody @Valid NovoLivroRequest novoLivroRequest) {
        Livro livro = novoLivroRequest.toEntity(categoriaId -> Optional.ofNullable(entityManager.find(Categoria.class, categoriaId)),
                                                autorId -> Optional.ofNullable(entityManager.find(Autor.class, autorId)));
        entityManager.persist(livro);
        return ResponseEntity.ok(new NovoLivroResponse(livro));
    }
}
