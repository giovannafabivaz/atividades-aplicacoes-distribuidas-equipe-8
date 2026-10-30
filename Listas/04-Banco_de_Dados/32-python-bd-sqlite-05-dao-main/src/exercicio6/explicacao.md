# Exercício 6: Refatoração do DAO (Command Pattern)

## 📌 Proposta do Exercício
Refatorar o exemplo prático inicial do repositório. O objetivo é remover os múltiplos métodos de CRUD concentrados numa única classe DAO e transformar *cada método* numa classe separada, dedicada a uma única funcionalidade. É necessário também discutir as vantagens, desvantagens e a qual padrão *GoF* a refatoração corresponde.

## 🛠️ Como foi feito
- Em vez de uma classe `UsuarioDAO` monolítica, a lógica foi fragmentada em cinco ficheiros/classes: `CreateTableUsuario`, `CreateUsuario`, `ReadUsuario`, `UpdateUsuario` e `DeleteUsuario`.
- **Discussão Teórica (Padrão GoF)**: Esta refatoração aproxima o código do **Command Pattern** (Padrão Comando), onde cada operação é encapsulada no seu próprio objeto.
- **Vantagens**: Aumento extremo da coesão e cumprimento rigoroso do Princípio da Responsabilidade Única (SRP) do SOLID. Facilita muito a escrita de testes unitários isolados.
- **Desvantagens**: Aumento drástico do número de ficheiros e classes. Torna o código verboso e adiciona complexidade arquitetural desnecessária para operações simples.

## ⚙️ O que faz
O programa final tem exatamente o mesmo comportamento do repositório base original: cria um banco de dados, regista a utilizadora "Ana", lê o seu registo, atualiza o seu nome para "Ana Silva", lê novamente para confirmar a atualização, e por fim elimina o registo. A diferença invisível ao utilizador está na forma como o Python processa estes comandos (instanciando classes dedicadas em vez de invocar métodos de um DAO único).

## ▶️ Como executar
Execute a partir da raiz do diretório do projeto:

```bash
python -m src.exercicio6.main
```