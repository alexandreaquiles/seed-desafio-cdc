package br.com.alexandreaquiles.casadocodigo.categoria;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    @JsonProperty
    private String nome;

    public String getNome() {
        return nome;
    }

    public Categoria toEntity() {
        return new Categoria(nome);
    }
}
