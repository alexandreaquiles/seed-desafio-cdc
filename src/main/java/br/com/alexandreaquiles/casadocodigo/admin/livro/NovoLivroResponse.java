package br.com.alexandreaquiles.casadocodigo.admin.livro;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class NovoLivroResponse {

    @JsonProperty
    private Long id;

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
    private Long categoriaId;

    @JsonProperty
    private Long autorId;

    public NovoLivroResponse(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumarioEmMarkdown = livro.getSumarioEmMarkdown();
        this.precoEmCentavosDeReais = livro.getPrecoEmCentavosDeReais();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.isbn = livro.getIsbn();
        this.dataDePublicacao = livro.getDataDePublicacao();
        this.categoriaId = livro.getCategoriaId();
        this.autorId = livro.getAutorId();
    }
}
