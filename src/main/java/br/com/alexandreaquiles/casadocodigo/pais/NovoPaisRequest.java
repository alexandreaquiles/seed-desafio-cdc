package br.com.alexandreaquiles.casadocodigo.pais;

import br.com.alexandreaquiles.casadocodigo.infra.errors.validation.Unique;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class NovoPaisRequest {

    @NotBlank
    @JsonProperty
    @Unique(entity = Pais.class, field = "nome", message = "{pais.nome.unico}")
    private String nome;

    public Pais toEntity() {
        return new Pais(nome);
    }

}
