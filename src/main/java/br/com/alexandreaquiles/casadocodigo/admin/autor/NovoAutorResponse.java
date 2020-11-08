package br.com.alexandreaquiles.casadocodigo.admin.autor;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class NovoAutorResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String nome;

    @JsonProperty
    private String email;

    @JsonProperty
    private String descricao;

    @JsonProperty
    private LocalDateTime criadoEm;

    public NovoAutorResponse(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.email = autor.getEmail();
        this.descricao = autor.getDescricao();
        this.criadoEm = autor.getCriadoEm();
    }
}
