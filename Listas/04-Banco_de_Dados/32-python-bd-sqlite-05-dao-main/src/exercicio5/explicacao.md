# Exercício 5: Gestão de Farmácia (CRUD)

## 📌 Proposta do Exercício
Implementar o CRUD de um banco de dados para uma farmácia usando o padrão DAO, focado na área de aprovisionamento com as tabelas: `Medicamento`, `Fornecedor` e `Compra`.

## 🛠️ Como foi feito
- **Classes de Domínio**: Criaram-se as representações em memória de Medicamento, Fornecedor e Compra usando as funcionalidades de `dataclass` do Python.
- **DAOs**: Foram criadas as classes para separar a responsabilidade de executar SQL (`MedicamentoDAO`, `FornecedorDAO`, `CompraDAO`). 
- A entidade `Compra` mapeia o evento de aquisição, relacionando um medicamento específico comprado a um determinado fornecedor.

## ⚙️ O que faz
O fluxo de inicialização (`main.py`) acede ou cria a base de dados `farmacia.db`. O sistema insere um produto na tabela de Medicamentos e uma empresa na tabela de Fornecedores. Por fim, executa a operação de cruzamento criando uma "Compra" (associando o medicamento ao fornecedor com a quantidade adquirida e a data) e imprime todas as etapas do CRUD efetuado no ecrã.

## ▶️ Como executar
A partir da raiz do repositório, rode o seguinte comando:

```bash
python -m src.exercicio5.main
```