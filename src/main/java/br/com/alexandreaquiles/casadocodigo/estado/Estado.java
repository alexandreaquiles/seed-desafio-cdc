package br.com.alexandreaquiles.casadocodigo.estado;

import br.com.alexandreaquiles.casadocodigo.pais.Pais;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @NotNull
    @Valid
    @ManyToOne(optional = false)
    private Pais pais;

    /**
     * @deprecated apenas para os amigos Hibernate + Spring Data JPA
     */
    @Deprecated(since = "uso do findById do Spring Data JPA")
    protected Estado() { }

    public Estado(@NotBlank String nome, @NotNull @Valid Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        Assert.notNull(pais, "O país do estado não deveria ser nulo.");
        return pais.getId();
    }
}
