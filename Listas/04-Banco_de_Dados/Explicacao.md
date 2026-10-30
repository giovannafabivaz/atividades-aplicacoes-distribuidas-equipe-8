# Lista de Banco de Dados: Java, SQLite e DAO

Cinco atividades de CRUD (Create, Read, Update, Delete) em Java, com banco de dados **SQLite** e o padrão **Data Access Object (DAO)**.

| Pasta | Atividade |
|---|---|
| `01-veiculos` | Cadastro de veículos |
| `02-padaria` | Padaria |
| `03-agencia-viagens` | Agência de viagens |
| `04-clinica-medica` | Clínica médica |
| `05-farmacia` | Farmácia |

> Se a pasta dos veículos tiver outro nome no seu repositório, use o nome que ela tiver.

---

## Como executar

### 1. Conferir se Java e Maven estão instalados

```bash
java -version
mvn -version
```

### 2. Ir para a pasta da atividade

Todos os comandos abaixo devem ser rodados **dentro da pasta da atividade** (onde está o arquivo `pom.xml`). A partir da raiz do repositório:

```bash
cd "Listas/Banco de Dados/02-padaria"
```

Se você já estiver dentro de uma das pastas, dá para trocar de atividade com `..`:

```bash
cd ../03-agencia-viagens
```

> As aspas são obrigatórias porque o nome da pasta tem espaços.

### 3. Compilar e rodar

```bash
mvn -q compile exec:java
```

Na primeira vez o Maven baixa o driver do SQLite, então pode demorar um pouco. Depois disso o programa abre no terminal. Se aparecer um aviso do SLF4J, pode ignorar.

### 4. Gerar a documentação Javadoc

```bash
javadoc -d ./doc src/main/java/*.java
```

Para ver no navegador do Codespaces:

```bash
python3 -m http.server 8000 -d doc
```

Abra a porta **8000** na aba **PORTAS**. Para parar o servidor, aperte `Ctrl + C`.

### 5. Comandos de cada atividade

```bash
cd "Listas/Banco de Dados/01-veiculos"        && mvn -q compile exec:java
cd "Listas/Banco de Dados/02-padaria"         && mvn -q compile exec:java
cd "Listas/Banco de Dados/03-agencia-viagens" && mvn -q compile exec:java
cd "Listas/Banco de Dados/04-clinica-medica"  && mvn -q compile exec:java
cd "Listas/Banco de Dados/05-farmacia"        && mvn -q compile exec:java
```

Rode um por vez. Para sair de qualquer programa, escolha a opção `0` do menu.

---

## Como o padrão DAO está organizado

Todas as atividades seguem a mesma divisão de responsabilidades:

| Arquivo | Papel |
|---|---|
| Classes de modelo (`Veiculo`, `Cliente`, `Pedido`...) | Guardam os dados: atributos privados com `get()` e `set()` |
| Classes DAO (`VeiculoDAO`, `ClienteDAO`...) | **Todo o SQL fica aqui**: `inserir`, `listar`, `buscarPorId`, `atualizar`, `excluir` |
| `Conexao.java` (atividades 2 a 5) | Abre a conexão, liga as chaves estrangeiras e cria as tabelas |
| `Main.java` | Menus e leitura do teclado. Não tem SQL, só chama os DAOs |

Todas as classes e métodos têm comentários `/** */` no formato do Javadoc.

---

## Atividade 1: Cadastro de veículos

**Arquivos:** `Veiculo.java`, `VeiculoDAO.java`, `Main.java`
**Banco:** `veiculos.db`

**Tabela `veiculo`:** `id`, `marca`, `modelo`, `chassi` (único), `placa` (única), `cor`.

**Como funciona:**
1. Ao iniciar, o programa pede os dados de um veículo e pergunta se você quer cadastrar outro. Dá para cadastrar quantos veículos quiser.
2. Depois do cadastro, abre o menu:
   - `1` Consultar veículos
   - `2` Atualizar veículo (informe o id; ENTER mantém o valor atual)
   - `3` Excluir veículo (informe o id)
   - `4` Cadastrar novo veículo
   - `0` Sair
3. Chassi e placa não podem se repetir. Se repetir, o programa avisa e não cadastra.

---

## Atividade 2: Padaria

**Pasta:** `02-padaria` | **Banco:** `padaria.db`

**Tabelas:**
- `Cliente(id_cliente, nome)`
- `Produto(id_produto, nome, preco)`
- `Pedido(id_pedido, id_cliente, data_pedido, total)`
- `ItemPedido(id_pedido, id_produto, quantidade)`

**Como funciona:** o menu principal tem Clientes, Produtos, Pedidos e Itens do pedido. Cada um abre um submenu com Cadastrar, Listar, Atualizar, Excluir e Voltar.

**Regras importantes:**
- Cadastre nesta ordem: **Cliente e Produto**, depois **Pedido**, depois **Item do pedido**.
- `ItemPedido` tem **chave primária composta** (`id_pedido` + `id_produto`). Por isso, para atualizar ou excluir um item, o programa pede os dois ids.
- Não é possível excluir um cliente que tem pedidos, nem um produto usado em itens. O programa avisa e não apaga.
- Ao excluir um **pedido**, os itens dele são apagados junto (`ON DELETE CASCADE`).

---

## Atividade 3: Agência de viagens

**Pasta:** `03-agencia-viagens` | **Banco:** `agencia.db`

**Tabelas:**
- `Cliente(id_cliente, nome)`
- `Destino(id_destino, nome_destino, pais)`
- `Reserva(id_reserva, id_cliente, id_destino, data_viagem, valor)`

**Como funciona:** menu com Clientes, Destinos e Reservas, cada um com Cadastrar, Listar, Atualizar e Excluir.

**Regras importantes:**
- Cadastre primeiro **Cliente e Destino**, depois **Reserva**.
- Uma reserva só aceita `id_cliente` e `id_destino` que já existem.
- Não é possível excluir um cliente ou destino que tenha reservas.

---

## Atividade 4: Clínica médica

**Pasta:** `04-clinica-medica` | **Banco:** `clinica.db`

**Tabelas:**
- `Paciente(id_paciente, nome)`
- `Medico(id_medico, nome, especialidade)`
- `Consulta(id_consulta, id_paciente, id_medico, data_consulta, valor)`

**Como funciona:** menu com Pacientes, Médicos e Consultas, cada um com Cadastrar, Listar, Atualizar e Excluir.

**Regras importantes:**
- Cadastre primeiro **Paciente e Médico**, depois **Consulta**.
- Uma consulta só aceita `id_paciente` e `id_medico` que já existem.
- Não é possível excluir um paciente ou médico que tenha consultas.

---

## Atividade 5: Farmácia

**Pasta:** `05-farmacia` | **Banco:** `farmacia.db`

**Tabelas:**
- `Medicamento(id_medicamento, nome, preco)`
- `Fornecedor(id_fornecedor, nome, cidade)`
- `Compra(id_compra, id_medicamento, id_fornecedor, quantidade, data_compra)`

**Como funciona:** menu com Medicamentos, Fornecedores e Compras, cada um com Cadastrar, Listar, Atualizar e Excluir.

**Regras importantes:**
- Cadastre primeiro **Medicamento e Fornecedor**, depois **Compra**.
- Uma compra só aceita `id_medicamento` e `id_fornecedor` que já existem.
- Não é possível excluir um medicamento ou fornecedor que tenha compras.

---

## Dicas de uso (atividades 2 a 5)

- **ID:** nos cadastros de tabelas principais o id é gerado automaticamente. Você só digita o id ao ligar um registro a outro (por exemplo, o cliente de um pedido).
- **Atualizar:** o programa mostra o valor atual entre colchetes. Apertar ENTER mantém o valor.
- **Datas:** formato `AAAA-MM-DD`, por exemplo `2026-10-25`.
- **Valores decimais:** aceitam vírgula ou ponto, por exemplo `12,50` ou `12.50`.
- **Mensagens de erro:** se você tentar cadastrar com um id que não existe ou excluir algo que está em uso, o programa mostra a mensagem e volta ao menu.

---

## Enviar para o GitHub

Na raiz do repositório:

```bash
git add .
git commit -m "Lista de Banco de Dados: CRUD com DAO e SQLite"
git push
```

A pasta `target/` (gerada pelo Maven) deve ficar no `.gitignore`. Os arquivos `.db` podem ir junto se o professor quiser ver o banco.

