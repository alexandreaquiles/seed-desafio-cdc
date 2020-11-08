package br.com.alexandreaquiles.casadocodigo.admin.livro;

import br.com.alexandreaquiles.casadocodigo.admin.autor.Autor;
import br.com.alexandreaquiles.casadocodigo.admin.categoria.Categoria;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String titulo;

    @NotBlank @Size(max = 500)
    private String resumo;

    @Lob
    private String sumarioEmMarkdown;

    @Min(2000)
    private int precoEmCentavosDeReais;

    @Min(100)
    private int numeroDePaginas;

    @NotBlank
    @Column(unique = true)
    private String isbn;

    @Future
    private LocalDate dataDePublicacao;

    @NotNull @Valid
    @ManyToOne(optional = false)
    private Categoria categoria;

    @NotNull @Valid
    @ManyToOne(optional = false)
    private Autor autor;

    /**
     * @deprecated apenas para os amigos Hibernate + Spring Data JPA
     */
    @Deprecated(since = "uso do findById do Spring Data JPA")
    protected Livro() { }

    public Livro(String titulo, String resumo, int precoEmCentavosDeReais, int numeroDePaginas, String isbn, Categoria categoria, Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.precoEmCentavosDeReais = precoEmCentavosDeReais;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumarioEmMarkdown() {
        return sumarioEmMarkdown;
    }

    public int getPrecoEmCentavosDeReais() {
        return precoEmCentavosDeReais;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataDePublicacao() {
        return dataDePublicacao;
    }

    public Long getCategoriaId() {
        Assert.notNull(categoria, "A categoria do livro não deveria ser nula.");
        return categoria.getId();
    }

    public Long getAutorId() {
        Assert.notNull(autor, "O autor do livro não deveria ser nula.");
        return autor.getId();
    }

    static class Builder {

        private String titulo;
        private String resumo;
        private String sumarioEmMarkdown;
        private int precoEmCentavosDeReais;
        private int numeroDePaginas;
        private String isbn;
        private LocalDate dataDePublicacao;
        private Categoria categoria;
        private Autor autor;

        static Builder umLivro() {
            return new Builder();
        }

        Builder comTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        Builder comResumo(String resumo) {
            this.resumo = resumo;
            return this;
        }

        Builder comSumario(String sumarioEmMarkdown) {
            this.sumarioEmMarkdown = sumarioEmMarkdown;
            return this;
        }

        Builder comPreco(int precoEmCentavosDeReais) {
            this.precoEmCentavosDeReais = precoEmCentavosDeReais;
            return this;
        }

        Builder comNumeroDePaginas(int numeroDePaginas) {
            this.numeroDePaginas = numeroDePaginas;
            return this;
        }

        Builder comIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        Builder comDataDePublicacao(LocalDate dataDePublicacao) {
            this.dataDePublicacao = dataDePublicacao;
            return this;
        }

        Builder comCategoria(Categoria categoria) {
            this.categoria = categoria;
            return this;
        }

        Builder comAutor(Autor autor) {
            this.autor = autor;
            return this;
        }

        Livro cria() {
            Livro livro = new Livro(titulo, resumo, precoEmCentavosDeReais, numeroDePaginas, isbn, categoria, autor);
            livro.sumarioEmMarkdown = sumarioEmMarkdown;
            livro.dataDePublicacao = dataDePublicacao;
            return livro;
        }
    }
}
