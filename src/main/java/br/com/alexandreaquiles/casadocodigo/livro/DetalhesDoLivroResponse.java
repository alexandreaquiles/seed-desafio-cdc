package br.com.alexandreaquiles.casadocodigo.livro;

import br.com.alexandreaquiles.casadocodigo.autor.Autor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class DetalhesDoLivroResponse {

    @JsonProperty
    private String titulo;

    @JsonProperty
    private String resumo;

    @JsonProperty
    private String sumarioEmMarkdown;

    @JsonProperty
    private int precoEmCentavosDeReais;

    @JsonProperty
    private int numeroDePaginas;

    @JsonProperty
    private String isbn;

    @JsonProperty
    private LocalDate dataDePublicacao;

    @JsonProperty
    private AutorParaDetalhesDoLivroResponse autor;

    public DetalhesDoLivroResponse(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumarioEmMarkdown = livro.getSumarioEmMarkdown();
        this.precoEmCentavosDeReais = livro.getPrecoEmCentavosDeReais();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.isbn = livro.getIsbn();
        this.dataDePublicacao = livro.getDataDePublicacao();
        this.autor = new AutorParaDetalhesDoLivroResponse(livro.getAutor());
    }
}

class AutorParaDetalhesDoLivroResponse {

    @JsonProperty
    private String nome;

    @JsonProperty
    private String descricao;

    public AutorParaDetalhesDoLivroResponse(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }
}

