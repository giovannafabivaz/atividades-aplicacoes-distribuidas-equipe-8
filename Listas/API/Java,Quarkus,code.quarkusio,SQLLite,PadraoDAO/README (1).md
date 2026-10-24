# Exercícios: API REST com Quarkus, SQLite e Padrão DAO

Este pacote contém **duas APIs REST independentes**, cada uma em sua pasta:

| Pasta | Exercício |
|---|---|
| `ex1-clientes-produtos/` | 1) Clientes e Produtos |
| `ex2-agencia-viagens/` | 2) Agência de Viagens |

Tecnologias: Java, Quarkus (REST Jackson), SQLite (via JDBC + pool Agroal), Padrão DAO, variáveis de ambiente via `.env`.

---

## 1. Como rodar

### Pré-requisitos
- Java instalado (o `pom.xml` usa `maven.compiler.release` = **25**; se o seu ambiente tiver outra versão, ex.: 21, troque esse valor no `pom.xml`).
- Maven (`mvn`). O GitHub Codespaces já traz os dois.

### Passo a passo (Codespaces ou máquina local)
1. Descompacte o zip: `unzip quarkus-exercicios.zip`
2. Entre na pasta do exercício que quer rodar:
   ```
   cd ex1-clientes-produtos      # ou: cd ex2-agencia-viagens
   ```
3. Inicie o servidor em modo desenvolvimento:
   ```
   mvn -Dquarkus.analytics.disabled=true quarkus:dev
   ```
4. Aguarde aparecer `Listening on: http://localhost:8082`. A linha
   `Listening for transport dt_socket at address: 5005` **não é erro**: é só a porta de debug.
5. Para parar: `Ctrl + C`.

> Os dois projetos usam a porta **8082**. Rode **um de cada vez** (pare um antes de iniciar o outro).

### Dicas
- **Codespaces:** a API roda dentro do Codespace, então o REST Client e o `curl` funcionam com `localhost:8082` sem configurar nada. A aba **Portas** só é necessária para abrir a API no navegador.
- **Modo dev:** ao salvar um arquivo `.java`, o Quarkus recarrega sozinho na próxima requisição.
- **Zerar o banco:** pare o servidor e apague `data/*.db`. As tabelas são recriadas no próximo início.

---

## 2. Como testar

### Opção A: REST Client (VS Code)
1. Instale a extensão **REST Client**.
2. Abra `testes.http`.
3. Clique em **Send Request** em cada bloco, **de cima para baixo** (a ordem importa: primeiro criam-se clientes/produtos, depois compras/viagens, depois as consultas).

### Opção B: curl
Veja exemplos na seção de endpoints. Em geral:
```
curl -X POST http://localhost:8082/clientes -H "Content-Type: application/json" -d '{"nome":"Ana","email":"ana@email.com"}'
curl http://localhost:8082/clientes
```

### Status HTTP esperados
| Situação | Status |
|---|---|
| Criar com dados válidos (POST) | 201 Created |
| Dados obrigatórios ausentes | 400 Bad Request |
| Consultar / atualizar com sucesso | 200 OK |
| Id inexistente | 404 Not Found |
| Remover com sucesso | 204 No Content |

Se algo der **404/400 inesperado**, confira se os ids usados existem (`GET /clientes`) e se rodou os blocos na ordem.

### Conferir o banco
Clique com o botão direito em `data/loja.db` (Ex. 1) ou `data/viagens.db` (Ex. 2) e escolha **Open with SQLite Viewer** (extensão **SQLite Viewer**).

---

## 3. Como a aplicação funciona

### Fluxo de uma requisição
```
Cliente HTTP (REST Client / curl)
        │  JSON
        ▼
   Resource  (controlador JAX-RS: recebe a requisição, valida, devolve Response)
        │
        ▼
     DAO      (acesso ao banco: SQL com JDBC / PreparedStatement)
        │
        ▼
 SQLite (arquivo .db)
```

### Estrutura de pastas
```
projeto/
├── .env                          # DB_PATH=./data/xxx.db
├── data/                         # onde o arquivo .db é criado
├── pom.xml                       # dependências Maven
├── testes.http                   # testes para o REST Client
└── src/main/
    ├── java/com/empresa/
    │   ├── model/                # entidades (records): Cliente, Produto, ...
    │   ├── dao/                  # camada de persistência (SQL)
    │   │   └── Database.java     # cria as tabelas na inicialização
    │   └── resource/             # endpoints REST
    │       └── Respostas.java    # helper para respostas de erro em JSON
    └── resources/application.properties
```

### Peças principais
- **model:** classes de dados (`record`) que o Jackson converte de/para JSON automaticamente. Datas usam `LocalDate` (formato `AAAA-MM-DD`).
- **dao (Data Access Object):** só aqui existe SQL. Cada DAO é um bean `@ApplicationScoped` (instância única) que recebe o pool de conexões por `@Inject AgroalDataSource`. Usa `PreparedStatement` com `?` para evitar SQL injection e `try-with-resources` para fechar conexões.
- **resource:** cada classe com `@Path` expõe os endpoints (`@GET`, `@POST`, `@PUT`, `@DELETE`). Valida a entrada e devolve o status HTTP certo. Não contém SQL, apenas chama o DAO.
- **Database.java:** observa o evento de início (`StartupEvent`) e executa `CREATE TABLE IF NOT EXISTS`, então não precisa criar tabelas à mão.
- **`.env` e `application.properties`:** o Quarkus lê o `.env` e injeta `DB_PATH` em `jdbc:sqlite:${DB_PATH:...}`. O trecho `?foreign_keys=on` liga as chaves estrangeiras do SQLite (desligadas por padrão).
- **CDI (`@Inject`, `@ApplicationScoped`):** o Quarkus cria e conecta os objetos para você, sem usar `new`. É o equivalente ao `@Autowired`/`@Service` do Spring.

### Relacionamentos (tabelas de ligação)
O enunciado pede consultas do tipo "produtos que o cliente comprou", então cada projeto tem uma terceira tabela que liga as duas entidades (relação muitos-para-muitos):
- Ex. 1: `compras (cliente_id, produto_id, quantidade, data_compra)`
- Ex. 2: `viagens (cliente_id, destino_id, data_visita)`

Ao apagar um cliente/produto/destino, as linhas ligadas a ele são removidas (`ON DELETE CASCADE`).

---

## 4. Exercício 1: Clientes e Produtos

| Método | Rota | Descrição |
|---|---|---|
| GET / POST | `/clientes` | Listar / criar (`nome`, `email`) |
| GET / PUT / DELETE | `/clientes/{id}` | Buscar / atualizar / remover |
| GET / POST | `/produtos` | Listar / criar (`nome`, `preco`, `categoria`) |
| GET / PUT / DELETE | `/produtos/{id}` | Buscar / atualizar / remover |
| GET / POST | `/compras` | Listar / registrar compra (`clienteId`, `produtoId`, `quantidade`) |
| DELETE | `/compras/{id}` | Remover compra |
| **GET** | **`/clientes/{id}/produtos`** | **Produtos adquiridos por um cliente** |
| **GET** | **`/categorias?n=2`** | **Categorias adquiridas por mais de n clientes** |

**Como as consultas funcionam**
- *Produtos de um cliente:* `JOIN` entre `produtos` e `compras` filtrando por `cliente_id`, com `DISTINCT` para não repetir o produto comprado várias vezes.
- *Categorias com mais de n clientes:* junta `compras` com `produtos`, agrupa por categoria (`GROUP BY`) e filtra com `HAVING COUNT(DISTINCT cliente_id) > n`. Conta clientes **distintos**, não compras.

Exemplo: `GET /categorias?n=1` devolve `[{"categoria":"Informática","totalClientes":2}, ...]`.

---

## 5. Exercício 2: Agência de Viagens

| Método | Rota | Descrição |
|---|---|---|
| GET / POST | `/clientes` | Listar / criar (`nome`, `email`) |
| GET / PUT / DELETE | `/clientes/{id}` | Buscar / atualizar / remover |
| GET / POST | `/destinos` | Listar / criar (`nome`, `pais`, `descricao`) |
| GET / PUT / DELETE | `/destinos/{id}` | Buscar / atualizar / remover |
| GET / POST | `/viagens` | Listar / registrar viagem (`clienteId`, `destinoId`, `dataVisita`) |
| DELETE | `/viagens/{id}` | Remover viagem |
| **GET** | **`/clientes/{id}/destinos`** | **Destinos visitados por um cliente** |
| **GET** | **`/destinos/{id}/clientes`** | **Clientes que visitaram um destino** |
| **GET** | **`/destinos/por-pais`** | **Destinos agrupados e ordenados por país** |
| **GET** | **`/paises?n=1`** | **Países com mais de n destinos** |

**Como as consultas funcionam**
- *Destinos de um cliente* e *clientes de um destino:* `JOIN` com a tabela `viagens` (em sentidos opostos).
- *Destinos por país:* o SQL ordena por `pais, nome`; o resource agrupa numa estrutura `país → lista de destinos` mantendo a ordem alfabética.
- *Países com mais de n destinos:* `GROUP BY pais HAVING COUNT(*) > n`.

---

## 6. Problemas comuns

| Sintoma | Causa / solução |
|---|---|
| `Port 8082 already in use` | Outro projeto ainda está rodando. Pare com `Ctrl+C` ou `pkill -f quarkus`. |
| Erro de versão do Java (`release 25 not supported`) | Altere `maven.compiler.release` no `pom.xml` para a sua versão (ex.: 21). |
| 404 ao criar compra/viagem | O `clienteId` ou o `produtoId`/`destinoId` não existe. Consulte com `GET`. |
| 400 ao criar | Faltou campo obrigatório no JSON. |
| JSON com data inválida | Use o formato `AAAA-MM-DD` (ex.: `2025-07-10`). |
| Dados antigos atrapalhando os testes | Pare o servidor, apague `data/*.db` e inicie de novo. |
