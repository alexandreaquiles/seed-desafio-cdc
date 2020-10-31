package br.com.alexandreaquiles.casadocodigo.autor;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/api/admin/autor")
public class AutorController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping
    public ResponseEntity<Autor> novoAutor(@RequestBody Autor autor) {
        entityManager.persist(autor);
        return ResponseEntity.ok().body(autor);
    }

}
