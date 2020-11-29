package br.com.alexandreaquiles.casadocodigo.checkout;

import br.com.alexandreaquiles.casadocodigo.estado.Estado;
import br.com.alexandreaquiles.casadocodigo.pais.Pais;
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
@RequestMapping("/checkout")
public class CheckoutController {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody @Valid CheckoutRequest checkoutRequest) {
        Checkout checkout = checkoutRequest.toEntity(paisId -> Optional.ofNullable(entityManager.find(Pais.class, paisId)),
                                                     estadoId -> Optional.ofNullable(entityManager.find(Estado.class, estadoId)));
        entityManager.persist(checkout);
        return ResponseEntity.ok().build();
    }

}
