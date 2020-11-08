package br.com.alexandreaquiles.casadocodigo.categoria;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NovaCategoriaResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String nome;

    public NovaCategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}
