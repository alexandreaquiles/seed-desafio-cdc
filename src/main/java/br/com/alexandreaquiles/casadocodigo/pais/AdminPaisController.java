package br.com.alexandreaquiles.casadocodigo.pais;

import br.com.alexandreaquiles.casadocodigo.autor.Autor;
import br.com.alexandreaquiles.casadocodigo.autor.NovoAutorRequest;
import br.com.alexandreaquiles.casadocodigo.autor.NovoAutorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin/paises")
public class AdminPaisController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping
    public ResponseEntity<NovoPaisResponse> novoPais(@RequestBody @Valid NovoPaisRequest novoPaisRequest) {
        Pais pais = novoPaisRequest.toEntity();
        entityManager.persist(pais);
        return ResponseEntity.ok(new NovoPaisResponse(pais));
    }

}
