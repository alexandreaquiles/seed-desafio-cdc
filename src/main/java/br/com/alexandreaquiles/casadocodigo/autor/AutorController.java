package br.com.alexandreaquiles.casadocodigo.autor;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/autores")
public class AutorController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping
    public ResponseEntity<Autor> novoAutor(@RequestBody @Valid Autor autor, BindingResult bindingResult) throws BindException {
        Assert.isNull(autor.getId(), "Um novo autor não deveria id.");
        Assert.isNull(autor.getCriadoEm(), "Um novo autor não deveria ter a data de criação.");

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Boolean jaTemEmailCadastrado = entityManager
                .createQuery("select count(a.id) > 0 from " + Autor.class.getSimpleName() + " a where a.email = :email", Boolean.class)
                .setParameter("email", autor.getEmail())
                .getSingleResult();
        if (jaTemEmailCadastrado) {
            bindingResult.rejectValue("email", "autor.email.unico", new String[]{ autor.getEmail() }, "");
            throw new BindException(bindingResult);
        }

        entityManager.persist(autor);
        return ResponseEntity.ok(autor);
    }

}
