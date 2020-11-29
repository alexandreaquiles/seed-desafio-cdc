package br.com.alexandreaquiles.casadocodigo.autor;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin/autores")
public class AdminAutorController {

    private final EntityManager entityManager;

    public AdminAutorController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<NovoAutorResponse> novoAutor(@RequestBody @Valid NovoAutorRequest novoAutorRequest) {
        Autor autor = novoAutorRequest.toEntity();
        entityManager.persist(autor);
        return ResponseEntity.ok(new NovoAutorResponse(autor));
    }

}
