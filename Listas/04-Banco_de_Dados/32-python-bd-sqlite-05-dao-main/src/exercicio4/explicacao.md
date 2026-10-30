# Exercício 4: Gestão de Clínica Médica (CRUD)

## 📌 Proposta do Exercício
Criar o fluxo de CRUD para o banco de dados de uma clínica médica utilizando o padrão DAO. As tabelas necessárias são: `Paciente`, `Medico` e `Consulta`.

## 🛠️ Como foi feito
- **Entidades**: Definidas usando `@dataclass` para mapeamento simples (Paciente, Medico, Consulta).
- **Acesso a Dados**: Implementou-se um ficheiro `clinica_dao.py` contendo `PacienteDAO`, `MedicoDAO` e `ConsultaDAO`. 
- A tabela `Consulta` funciona como o elo entre Paciente e Médico, possuindo chaves estrangeiras para ambos, além do valor e da data da consulta.

## ⚙️ O que faz
Ao correr o ficheiro principal, o sistema cria o banco `clinica.db`. Em seguida, insere de forma programática um paciente e um médico especializado. Com os IDs gerados automaticamente pelo SQLite, o script cria uma consulta médica unindo o paciente e o médico recém-cadastrados, exibindo os dados das entidades criadas no terminal para confirmar o sucesso da operação.

## ▶️ Como executar
A partir da raiz do repositório, rode o comando:

```bash
python -m src.exercicio4.main
```