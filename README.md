# Baozi Store - API REST

Trabalho da disciplina Desenvolvimento Web Back-End (UNINTER).

Autor: Italo Guilherme Alves Xavier - RU 4674875

## Tecnologias
- Java 17
- Spring Boot 3.3.4
- Spring Data JPA
- Banco H2 (em memória)
- Maven

## Como rodar

1. Abra o projeto no IntelliJ / VS Code / Eclipse (importe como projeto Maven), ou rode direto pelo terminal:

```bash
mvn spring-boot:run
```

2. A API sobe em `http://localhost:8080`.

3. Console do banco H2 (opcional, para ver os dados salvos): `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:baozistore`
   - Usuário: `sa`
   - Senha: (em branco)

## Endpoints

### Cliente (`/clientes`)
| Método | Rota | Descrição |
|---|---|---|
| POST | /clientes | Criar cliente |
| GET | /clientes | Listar todos |
| GET | /clientes/{id} | Buscar por ID |
| PUT | /clientes/{id} | Atualizar |
| DELETE | /clientes/{id} | Apagar |

Exemplo de body (POST):
```json
{
  "nome": "Italo Guilherme Alves Xavier4674875",
  "clienteDesde": "2026-10-17"
}
```

### Produto (`/produtos`)
| Método | Rota | Descrição |
|---|---|---|
| POST | /produtos | Criar produto |
| GET | /produtos | Listar todos |
| GET | /produtos/{id} | Buscar por ID |
| PUT | /produtos/{id} | Atualizar |
| DELETE | /produtos/{id} | Apagar |

Exemplo de body (POST):
```json
{
  "nome": "Baozi de Carne",
  "preco": 8.50,
  "estoque": true
}
```

### Pedido (`/pedidos`)
| Método | Rota | Descrição |
|---|---|---|
| POST | /pedidos | Criar pedido |
| GET | /pedidos | Listar todos |
| GET | /pedidos/{id} | Buscar por ID |
| PUT | /pedidos/{id} | Atualizar |
| DELETE | /pedidos/{id} | Apagar |

Exemplo de body (POST) — use os IDs de um cliente e produto já criados:
```json
{
  "clienteId": 1,
  "produtoId": 1,
  "quantidade": 3
}
```

## Ordem recomendada para testar no Postman
1. POST /clientes (anote o id retornado)
2. POST /produtos (anote o id retornado)
3. POST /pedidos (usando os ids acima)
4. GET /clientes, GET /produtos, GET /pedidos (listagem geral)
5. GET /clientes/{id}, /produtos/{id}, /pedidos/{id} (consulta por id)
6. DELETE /clientes/{id}, /produtos/{id}, /pedidos/{id}
