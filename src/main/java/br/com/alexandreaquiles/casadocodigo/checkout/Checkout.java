package br.com.alexandreaquiles.casadocodigo.checkout;

import br.com.alexandreaquiles.casadocodigo.estado.Estado;
import br.com.alexandreaquiles.casadocodigo.pais.Pais;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Checkout {

    @Id
    private UUID id = UUID.randomUUID();

    @NotBlank @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @CPF
    private String cpf;

    @CNPJ
    private String cnpj;

    @NotBlank
    private String telefone;

    @NotBlank
    private String endereco;

    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    private String cep;

    @NotNull @Valid
    @ManyToOne(optional = false)
    private Pais pais;

    @Valid
    @ManyToOne
    private Estado estado;

    /**
     * @deprecated apenas para os amigos Hibernate + Spring Data JPA
     */
    @Deprecated(since = "uso do findById do Spring Data JPA")
    protected Checkout() { }

    private Checkout(String email, String nome, String sobrenome, String cpf, String cnpj, String telefone, String endereco, String complemento, String cidade, String cep,  Pais pais,  Estado estado) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.cep = cep;
        this.pais = pais;
        this.estado = estado;
    }

    static class Builder {

        private String email;
        private String nome;
        private String sobrenome;
        private String cpf;
        private String cnpj;
        private String telefone;
        private String endereco;
        private String complemento;
        private String cidade;
        private String cep;
        private Pais pais;
        private Estado estado;

        static Builder umCheckout() {
            return new Builder();
        }

        Builder comEmail(String email) {
            this.email = email;
            return this;
        }

        Builder comNome(String nome) {
            this.nome = nome;
            return this;
        }

        Builder comSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
            return this;
        }

        Builder comCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        Builder comCnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        Builder comTelefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        Builder comEndereco(String endereco) {
            this.endereco = endereco;
            return this;
        }

        Builder comComplemento(String complemento) {
            this.complemento = complemento;
            return this;
        }

        Builder comCidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        Builder comCep(String cep) {
            this.cep = cep;
            return this;
        }

        Builder comPais(Pais pais) {
            this.pais = pais;
            return this;
        }

        Builder comEstado(Estado estado) {
            this.estado = estado;
            return this;
        }

        Checkout cria() {
            return new Checkout(email, nome, sobrenome, cpf, cnpj, telefone, endereco, complemento, cidade , cep, pais, estado);
        }
    }
}
