# Exercício 2: Gestão de Padaria (CRUD com Relacionamentos)

## Proposta do Exercício
Implementar o CRUD de um banco de dados para uma padaria utilizando o padrão DAO. O sistema deve gerir quatro tabelas interligadas: `Cliente`, `Pedido`, `Produto` e `ItemPedido`.

## Como foi feito
Para resolver a complexidade de múltiplas tabelas relacionais:
- **Modelos**: Foram criadas quatro `@dataclass` (Cliente, Pedido, Produto, ItemPedido) num ficheiro `entidades.py`.
- **DAOs**: Foi criado um DAO específico para cada entidade (`ClienteDAO`, `ProdutoDAO`, `PedidoDAO`, `ItemPedidoDAO`). 
- **Relacionamentos (Foreign Keys)**: No código de criação das tabelas, foram aplicadas as restrições `FOREIGN KEY` (ex: `Pedido` faz referência a `Cliente`, e `ItemPedido` faz referência tanto a `Pedido` quanto a `Produto`).

## O que faz
O script `main.py` funciona como um fluxo de demonstração (script de teste). Ele inicializa o banco de dados `padaria.db`, cria as quatro tabelas garantindo a integridade relacional, e executa uma sequência de operações: cadastra um cliente (Maria Joaquina), cadastra um produto (Pão Alentejano), gera um pedido para esse cliente e insere o produto como um item desse pedido. Todos os resultados são impressos no terminal.

## Como executar
A partir da raiz do repositório, execute:

```bash
python -m src.exercicio2.main
```