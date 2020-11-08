package br.com.alexandreaquiles.casadocodigo.admin.categoria;

import br.com.alexandreaquiles.casadocodigo.infra.validation.Unique;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @Unique(entity = Categoria.class, field = "nome", message = "{categoria.nome.unico}")
    @NotBlank
    @JsonProperty
    private String nome;

    public Categoria toEntity() {
        return new Categoria(nome);
    }
}