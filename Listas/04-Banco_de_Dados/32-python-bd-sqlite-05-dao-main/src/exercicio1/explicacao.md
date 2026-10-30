# Exercício 1: Cadastro de Veículos (CRUD Interativo)

## Proposta do Exercício
A proposta deste exercício é criar um algoritmo em Python utilizando o banco de dados SQLite para gerir o cadastro de veículos. O sistema deve seguir o padrão de projeto *Data Access Object* (DAO) e conter as propriedades: marca, modelo, número do chassi, placa e cor. Além disso, deve oferecer um menu interativo para que o utilizador possa cadastrar, consultar, atualizar e excluir quantos veículos desejar.

## Como foi feito
O projeto foi estruturado seguindo o padrão arquitetural MVC/DAO simplificado:
- **Model (`veiculo.py`)**: Utilizou-se a anotação `@dataclass` para criar a entidade `Veiculo` que mapeia os dados.
- **Database (`connection_factory.py`)**: Uma fábrica de conexões responsável por criar e apontar para o ficheiro `veiculos.db`.
- **DAO (`veiculo_dao.py`)**: A classe `VeiculoDAO` concentra toda a lógica de comunicação direta com o banco de dados (comandos SQL `INSERT`, `SELECT`, `UPDATE`, `DELETE`).
- **Main (`main.py`)**: Implementou-se um loop `while True` para criar um menu interativo no terminal.

## O que faz
Quando iniciado, o programa cria automaticamente o banco de dados e a tabela `veiculo` (se não existirem). Em seguida, apresenta um menu no terminal. O utilizador pode escolher opções para digitar os dados de um novo veículo, listar todos os veículos guardados, modificar os dados de um veículo existente informando o seu ID, ou apagá-lo do sistema. 

## Como executar
Certifique-se de estar na raiz do repositório (onde a pasta `src` se encontra) e execute o seguinte comando no terminal:

```bash
python -m src.exercicio1.main
```