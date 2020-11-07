package br.com.alexandreaquiles.casadocodigo.livro;

import br.com.alexandreaquiles.casadocodigo.autor.Autor;
import br.com.alexandreaquiles.casadocodigo.categoria.Categoria;
import br.com.alexandreaquiles.casadocodigo.infra.validation.Unique;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.LongFunction;

import static br.com.alexandreaquiles.casadocodigo.livro.Livro.Builder.umLivro;
import static java.lang.String.format;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class NovoLivroRequest {

    @Unique(entity = Livro.class, field = "titulo", message = "{livro.titulo.unico}")
    @NotBlank
    @JsonProperty
    private String titulo;

    @NotBlank @Size(max = 500)
    @JsonProperty
    private String resumo;

    @JsonProperty
    private String sumarioEmMarkdown;

    @Min(2000)
    @JsonProperty
    private int precoEmCentavosDeReais;

    @Min(100)
    @JsonProperty
    private int numeroDePaginas;

    @Unique(entity = Livro.class, field = "isbn", message = "{livro.isbn.unico}")
    @NotBlank
    @JsonProperty
    private String isbn;

    @Future
    @DateTimeFormat(iso = DATE)
    @JsonProperty
    private LocalDate dataDePublicacao;

    @Positive
    @JsonProperty
    private long categoriaId;

    @Positive
    @JsonProperty
    private long autorId;

    public Livro toEntity(LongFunction<Optional<Categoria>> buscaCategoria,
                          LongFunction<Optional<Autor>> buscaAutor) {
        Categoria categoria = buscaCategoria.apply(categoriaId)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, format("Id da Categoria inválido: %s", categoriaId)));
        Autor autor = buscaAutor.apply(autorId)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, format("Id do Autor inválido: %s", autorId)));
        return umLivro()
                .comTitulo(titulo)
                .comResumo(resumo)
                .comSumario(sumarioEmMarkdown)
                .comPreco(precoEmCentavosDeReais)
                .comNumeroDePaginas(numeroDePaginas)
                .comIsbn(isbn)
                .comDataDePublicacao(dataDePublicacao)
                .comCategoria(categoria)
                .comAutor(autor)
                .cria();
    }

}
