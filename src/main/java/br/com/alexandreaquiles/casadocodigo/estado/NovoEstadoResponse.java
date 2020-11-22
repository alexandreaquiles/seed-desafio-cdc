package br.com.alexandreaquiles.casadocodigo.estado;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NovoEstadoResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String nome;

    @JsonProperty
    private Long paisId;

    public NovoEstadoResponse(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.paisId = estado.getPaisId();
    }
}
