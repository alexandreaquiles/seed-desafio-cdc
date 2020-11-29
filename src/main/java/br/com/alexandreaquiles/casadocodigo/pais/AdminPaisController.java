package br.com.alexandreaquiles.casadocodigo.pais;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin/paises")
public class AdminPaisController {

    private final EntityManager entityManager;

    public AdminPaisController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<NovoPaisResponse> novoPais(@RequestBody @Valid NovoPaisRequest novoPaisRequest) {
        Pais pais = novoPaisRequest.toEntity();
        entityManager.persist(pais);
        return ResponseEntity.ok(new NovoPaisResponse(pais));
    }

}
