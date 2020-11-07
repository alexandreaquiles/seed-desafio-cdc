package br.com.alexandreaquiles.casadocodigo.autor;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoAutorRequest {

    @NotBlank
    @JsonProperty
    private String nome;

    @NotBlank @Email
    @JsonProperty
    private String email;

    @NotBlank @Size(max = 400)
    @JsonProperty
    private String descricao;

    public Autor toEntity() {
        return new Autor(nome, email, descricao);
    }

    public String getEmail() {
        return email;
    }
}

