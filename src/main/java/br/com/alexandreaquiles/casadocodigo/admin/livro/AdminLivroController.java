package br.com.alexandreaquiles.casadocodigo.admin.livro;

import br.com.alexandreaquiles.casadocodigo.admin.autor.AutorRepository;
import br.com.alexandreaquiles.casadocodigo.admin.categoria.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/livros")
public class AdminLivroController {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;

    public AdminLivroController(LivroRepository livroRepository, AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<NovoLivroResponse> novoLivro(@RequestBody @Valid NovoLivroRequest novoLivroRequest) {
        Livro livro = novoLivroRequest.toEntity(categoriaRepository::findById,
                                                autorRepository::findById);
        livroRepository.save(livro);
        return ResponseEntity.ok(new NovoLivroResponse(livro));
    }
}
