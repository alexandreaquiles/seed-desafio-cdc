package br.com.alexandreaquiles.casadocodigo.checkout;

import br.com.alexandreaquiles.casadocodigo.estado.Estado;
import br.com.alexandreaquiles.casadocodigo.infra.errors.validation.InvalidRelationshipWithEntityException;
import br.com.alexandreaquiles.casadocodigo.infra.errors.validation.MustHaveCpfOrCnpj;
import br.com.alexandreaquiles.casadocodigo.pais.Pais;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;
import java.util.function.LongFunction;

import static br.com.alexandreaquiles.casadocodigo.checkout.Checkout.Builder.umCheckout;

@MustHaveCpfOrCnpj
public class CheckoutRequest {

    @NotBlank @Email
    @JsonProperty
    private String email;

    @NotBlank
    @JsonProperty
    private String nome;

    @NotBlank
    @JsonProperty
    private String sobrenome;

    @CPF
    @JsonProperty
    private String cpf;

    @CNPJ
    @JsonProperty
    private String cnpj;

    @NotBlank
    @JsonProperty
    private String endereco;

    @JsonProperty
    private String complemento;

    @NotBlank
    @JsonProperty
    private String cidade;

    @Positive
    @JsonProperty
    private long paisId;

    @PositiveOrZero
    @JsonProperty
    private long estadoId;

    @NotBlank
    @JsonProperty
    private String cep;

    @NotBlank
    @JsonProperty
    private String telefone;

    public Checkout toEntity(LongFunction<Optional<Pais>> buscaPais,
                             LongFunction<Optional<Estado>> buscaEstado) {
        Pais pais = buscaPais.apply(paisId)
                .orElseThrow(() -> new InvalidRelationshipWithEntityException(Pais.class, paisId));
        Estado estado = buscaEstado.apply(estadoId)
                .orElse(null);
        return umCheckout()
                .comEmail(email)
                .comNome(nome)
                .comSobrenome(sobrenome)
                .comCpf(cpf)
                .comCnpj(cnpj)
                .comEndereco(endereco)
                .comComplemento(complemento)
                .comCidade(cidade)
                .comCep(cep)
                .comEstado(estado)
                .comPais(pais)
                .comTelefone(telefone)
                .cria();
    }
}
