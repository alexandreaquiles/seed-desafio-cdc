package br.com.alexandreaquiles.casadocodigo.estado;

import br.com.alexandreaquiles.casadocodigo.pais.Pais;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/admin/estados")
public class AdminEstadoController {

    private EntityManager entityManager;

    public AdminEstadoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<NovoEstadoResponse> novoEstado(@RequestBody @Valid NovoEstadoRequest novoEstadoRequest) {
        Estado estado = novoEstadoRequest.toEntity(paisId -> Optional.ofNullable(entityManager.find(Pais.class, paisId)));
        entityManager.persist(estado);
        return ResponseEntity.ok(new NovoEstadoResponse(estado));
    }

}
