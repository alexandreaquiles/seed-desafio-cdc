package br.com.alexandreaquiles.casadocodigo.admin.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    /**
     * @deprecated apenas para os amigos Hibernate + Spring Data JPA
     */
    @Deprecated(since = "uso do findById do Spring Data JPA")
    protected Categoria() {}

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
