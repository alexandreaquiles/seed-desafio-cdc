package br.com.alexandreaquiles.casadocodigo.estado;

import br.com.alexandreaquiles.casadocodigo.infra.errors.validation.InvalidRelationshipWithEntityException;
import br.com.alexandreaquiles.casadocodigo.infra.errors.validation.Unique;
import br.com.alexandreaquiles.casadocodigo.pais.Pais;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Optional;
import java.util.function.LongFunction;

public class NovoEstadoRequest {

    @Unique(entity = Estado.class, field = "nome", message = "{estado.nome.unico}")
    @NotBlank
    @JsonProperty
    private String nome;

    @Positive
    @JsonProperty
    private long paisId;

    public Estado toEntity(LongFunction<Optional<Pais>> buscaPais) {
        Pais pais = buscaPais.apply(paisId)
                .orElseThrow(() -> new InvalidRelationshipWithEntityException(Pais.class, paisId));
        return new Estado(nome, pais);
    }

}
