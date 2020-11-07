package br.com.alexandreaquiles.casadocodigo.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/api/admin/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping
    public ResponseEntity<Categoria> novaCategoria(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Boolean jaTemNomeCadastrado = entityManager
                .createQuery("select count(c.id) > 0 from " + Categoria.class.getSimpleName() + " c where c.nome = :nome", Boolean.class)
                .setParameter("nome", novaCategoriaRequest.getNome())
                .getSingleResult();
        if (jaTemNomeCadastrado) {
            bindingResult.rejectValue("nome", "categoria.nome.unico", new String[]{ novaCategoriaRequest.getNome() }, "");
            throw new BindException(bindingResult);
        }

        Categoria categoria = novaCategoriaRequest.toEntity();
        entityManager.persist(categoria);
        return ResponseEntity.ok(categoria);
    }

}
