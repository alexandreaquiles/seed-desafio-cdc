# Casa do Código

## Como rodar os testes?

### Pré-requisitos:

- [Java 11+](https://sdkman.io/jdks)
- [Maven 3+](https://sdkman.io/sdks#maven)
- [MySQL 8+](https://dev.mysql.com/downloads/)
- [Postman](https://www.postman.com/)

### Executando a API da Casa do Código

Num Terminal, execute:

```sh
mvn spring-boot:run
```

É usado o usuário `root` sem senha.

Para definir outro usuário ou senha, utilize as variáveis de ambiente `SPRING_DATASOURCE_USERNAME` e `SPRING_DATASOURCE_PASSWORD`, respectivamente.

Deve ser criado um database `casadocodigo` no MySQL, já com as tabelas necessárias.

### Executando os testes no Postman

No Postman, importe o arquivo `api-casa-do-codigo.postman_collection.json`.

Na _collections_ "API da Casa do Código", clique em _Run_ e então em _Run API da Casa do Código_.
