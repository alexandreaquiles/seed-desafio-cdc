package br.com.alexandreaquiles.casadocodigo.livro;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class ListagemDeLivrosResponse {

    @JsonProperty
    private List<LivroParaListagemResponse> livros;

    public ListagemDeLivrosResponse(List<Livro> livros) {
        Assert.notNull(livros, "A lista de livros não deve ser nula.");
        this.livros = livros.stream().map(LivroParaListagemResponse::new).collect(Collectors.toList());
    }
}

class LivroParaListagemResponse {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String titulo;

    LivroParaListagemResponse(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }
}