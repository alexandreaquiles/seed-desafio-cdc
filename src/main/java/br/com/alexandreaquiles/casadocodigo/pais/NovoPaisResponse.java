package br.com.alexandreaquiles.casadocodigo.pais;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NovoPaisResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String nome;

    public NovoPaisResponse(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
    }
}
