package br.com.alexandreaquiles.casadocodigo.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin/categorias")
public class AdminCategoriaController {

    private final EntityManager entityManager;

    public AdminCategoriaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<NovaCategoriaResponse> novaCategoria(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {
        Categoria categoria = novaCategoriaRequest.toEntity();
        entityManager.persist(categoria);
        return ResponseEntity.ok(new NovaCategoriaResponse(categoria));
    }

}
