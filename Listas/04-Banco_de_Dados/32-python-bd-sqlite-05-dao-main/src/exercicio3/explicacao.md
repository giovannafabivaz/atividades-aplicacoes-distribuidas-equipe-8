# Exercício 3: Gestão de Agência de Viagens (CRUD)

## 📌 Proposta do Exercício
Implementar o CRUD de um banco de dados para uma agência de viagens utilizando o padrão DAO, contemplando as tabelas: `Cliente`, `Destino` e `Reserva`.

## 🛠️ Como foi feito
A arquitetura reflete o modelo do exercício anterior, adaptado para as novas regras de negócio:
- **Modelos**: Criação das classes de dados para Cliente, Destino e Reserva.
- **DAOs**: Criação das respetivas classes DAO para isolar as queries de inserção e criação de tabelas.
- A tabela `Reserva` foi modelada como uma tabela de junção contendo chaves estrangeiras (`id_cliente` e `id_destino`), além dos campos `data_viagem` e `valor`.

## ⚙️ O que faz
O programa gera a base de dados `agencia.db` e estabelece a estrutura de tabelas. Através do `main.py`, o código regista um cliente ("Carlos Mendes"), regista um destino ("Lisboa", "Portugal") e, em seguida, cruza estas duas informações para efetuar o registo de uma reserva com uma data e valor específicos, imprimindo a confirmação das gravações no terminal.

## ▶️ Como executar
A partir da raiz do repositório, execute:

```bash
python -m src.exercicio3.main
```