# Ecommerce-API
## Descrição do projeto
Projeto desenvolvido para estudos referente ao Spring Security, chamado de "ecommerce-api" se trata de uma simulação de 
um ecommerce onde usuarios comuns e administrador possui autorizações diferentes.

## Requisitos:
| Role           | Descricao                                                                                                           | Retorno |
|----------------|---------------------------------------------------------------------------------------------------------------------|---------|
| CLIENT         | deverá conseguir visualizar as informações de todos os produtos, com paginação e ordenação default por menor preço. | |
| CLIENT         | deverá conseguir fazer pedido de produtos.                                                                          | |
| ADMIN          | deverá conseguir cadastrar, atualizar, listar e deletar produtos.                                                   | |
| ADMIN          | poderá cancelar um pedido com status realizado.                                                                     | |
| CLIENT / ADMIN | visualizar todos os produtos de acordo com a categoria                                                              | |
| CLIENT / ADMIN | visualizar todos os produtos de acordo com categoria(s) filtradas(s)                                                | |

## Pré requisitos:
* Java 17
* Maven
* Postgres

## Como rodar:
Clone o repositório e entrando dentro da pasta raiz utilize o próprio maven wrapper para conseguir roda-lo.
> ./mvnw spring-boot:run

## Nesse projeto foi utilizado:
* Spring Data JPA
>  * jpql
>  * @Query
>  * projections
> * Spring Security
    >  * JWT
> * Mapstruct
> * Lombok
> * OpenAPI
> * Guava (Lib Utils Google)
> * jjwt

## Endpoints:
Todos os endpoints podem ser visualizados a partir da documentação gerada automaticamente pelo OpenAPI:
> http://localhost:8080/swagger-ui/index.html

### Autenticar:
POST:
> https://localhost:8080/auth retornará o JWT Token com validade de 15 minutos que será usado para todas as requisições.
```
{
    "username": "string",
    "password": "string
}
```

### Gerenciar Usuarios:
GET:
> http://localhost:8080/api/v1/users retorna todos usuários

POST:
> http://localhost:8080/api/v1/users/create cadastra novo usuário
```
{
  "username": "string",
  "password": "string",
}
```

PUT:
> http://localhost:8080/api/v1/users/{userId}/grant-permission?roleName=ADMIN conceder role de admin para um usuario

### Produtos:
GET:
> http://localhost:8080/api/v1/produtos retorna todos os produtos

POST:
> http://localhost:8080/api/v1/produtos/create cria um novo produto
```
{
  "nome": "string",
  "preco": 0,
  "fornecedorNome": "string",
  "categoriaNome": "string"
}
```

GET:
> http://localhost:8080/api/v1/produtos/categorias/{nomeCategoria} retorna todos os produtos de uma categoria

PUT:
> http://localhost:8080/api/v1/produtos/{produtoId}/novo-preco?novoPreco=25.0 altera o preco do produto para 25.00

DELETE:
> http://localhost:8080/api/v1/produtos/{produtoId} deleta um produto pelo seu id

### Fornecedores:
GET:
> http://localhost:8080/api/v1/fornecedores retorna todos fornecedores

GET:
> http://localhost:8080/api/v1/fornecedores/{idFornecedor} retorna fornecedor pelo id

POST:
> http://localhost:8080/api/v1/fornecedores/create cria novo fornecedor
```
{
  "descricao": "string",
}
```

PUT:
> http://localhost:8080/api/v1/fornecedores/{idFornecedor} editar fornecedor pelo id
```
{
  "descricao": "string",
}
```

DELETE:
> http://localhost:8080/api/v1/fornecedores/{idFornecedor} deleta fornecedor pelo id

### Categorias:
GET:
> http://localhost:8080/api/v1/categorias retorna todos categorias

GET:
> http://localhost:8080/api/v1/categorias/{idCategoria} retorna categoria pelo id

POST:
> http://localhost:8080/api/v1/categorias/create cria novo fornecedor
```
{
  "descricao": "string",
}
```

PUT:
> http://localhost:8080/api/v1/categorias/{idCategoria} editar categoria pelo id
```
{
  "descricao": "string",
}
```

DELETE:
> http://localhost:8080/api/v1/categorias/{idCategoria} deleta categoria pelo id

### Carrinho de compras:
GET:
> http://localhost:8080/api/v1/carrinhos-de-compras retorna todos Carrinho de compras

GET:
> http://localhost:8080/api/v1/carrinhos-de-compras/{idCarrinho} retorna Carrinho de compra pelo id

POST:
> http://localhost:8080/api/v1/carrinhos-de-compras/create cria novo Carrinho de compra
```
{
  "produtosList": [
    0
  ]
}
```

PUT:
> http://localhost:8080/api/v1/carrinhos-de-compras/realize/{idCarrinho} alterar status pedido Carrinho de compra pelo id para realizado

PUT:
> http://localhost:8080/api/v1/carrinhos-de-compras/cancel/{idCarrinho} alterar status pedido Carrinho de compra pelo id para cancelado (Somente ADMIN)

DELETE:
> http://localhost:8080/api/v1/carrinhos-de-compras/{idCarrinho} deleta Carrinho de compra pelo id