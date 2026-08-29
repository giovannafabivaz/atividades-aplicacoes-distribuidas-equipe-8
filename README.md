<<<<<<< HEAD
# atividades-aplicacoes-distribuidas-equipe-8
---
# 01 - Java Thread

Este projeto contém exercícios sobre o uso de Threads em Java. 
Em todos os exercícios foi utilizada a classe `Tarefa`, responsável pela execução da tarefa em uma Thread, e a classe `Main`, responsável por iniciar o programa.

## 1. Thread Calculadora

Neste exercício, a classe `Tarefa` foi modificada para receber dois números e uma operação matemática.

As operações disponíveis são:

- Soma (`+`)
- Subtração (`-`)
- Multiplicação (`*`)
- Divisão (`/`)

Dentro do método `run()`, foi utilizado um laço de repetição para realizar cinco cálculos. A cada repetição, o valor do primeiro número é alterado de acordo com o valor de `i`.

O resultado de cada cálculo é mostrado no console, permitindo acompanhar o resultado parcial de cada passo.

---

## 2. Thread Jogo de Adivinhação

Neste exercício, cada `Tarefa` gera um número secreto de forma aleatória quando a Thread é iniciada.

Depois disso, são realizados até três palpites aleatórios. A cada tentativa, o programa compara o palpite com o número secreto.

O programa informa se o palpite foi:

- Maior que o número secreto;
- Menor que o número secreto;
- Igual ao número secreto.

Quando o número é acertado, o laço é encerrado utilizando `break`, finalizando a tentativa antes dos três palpites.

---

## 3. Thread Contador de Caracteres

Neste exercício, a classe `Tarefa` recebe uma String como parâmetro.

Para descobrir a quantidade de caracteres, foi utilizado o método `length()` da classe `String`.

Após realizar a contagem, o programa mostra no console o texto informado e a quantidade de caracteres encontrada.

---

## 4. Thread Soma de Vetores

Neste exercício, a classe `Tarefa` recebe dois vetores de números inteiros.

Os elementos dos dois vetores são somados utilizando o mesmo índice. Por exemplo, o elemento da posição `0` do primeiro vetor é somado ao elemento da posição `0` do segundo vetor.

O resultado de cada posição é mostrado no console, permitindo acompanhar o resultado parcial da soma.

### Paralelização

Para vetores muito grandes, o processamento poderia ser dividido entre várias Threads.

Por exemplo, uma Thread poderia ficar responsável pelas primeiras posições do vetor, enquanto outras Threads processariam as demais posições.

Dessa forma, diferentes partes do vetor poderiam ser processadas ao mesmo tempo, aproveitando melhor os recursos do computador.

---

## 5. Thread Soma de Matrizes

Neste exercício, a classe `Tarefa` recebe duas matrizes de números inteiros.

A soma é realizada elemento por elemento, considerando a mesma linha e coluna nas duas matrizes.

Para percorrer a matriz, foram utilizados dois laços de repetição: um para percorrer as linhas e outro para percorrer as colunas.

O resultado de cada posição é mostrado no console.

### Paralelização

Para matrizes grandes, o processamento poderia ser dividido entre várias Threads.

Uma possibilidade seria dividir as linhas da matriz entre as Threads. Cada Thread ficaria responsável por realizar a soma de determinadas linhas.

Assim, diferentes partes da matriz poderiam ser processadas simultaneamente, tornando o processamento mais eficiente em matrizes grandes.

---

## Conclusão

Os exercícios permitiram praticar o uso de Threads em Java e entender como uma tarefa pode ser executada de forma independente.

Também foi possível aplicar Threads em diferentes situações, como cálculos, geração de números aleatórios, manipulação de Strings, vetores e matrizes.
=======
# Desenvolvimento de Aplicações Distribuídas

## Integrantes do Grupo
* Giovanna Fabíola Vaz
* Kaio César dos Santos Vidigal
* Luiza Rodrigues Vertelo
* Mateus de Carvalho Freitas
* Paulo Henrique Xavier

Este repositório foi criado para armazenar, versionar e organizar todos os códigos, listas de exercícios e projetos práticos desenvolvidos ao longo da disciplina de Desenvolvimento de Aplicações Distribuídas. 

Conforme o andamento das aulas e a entrega de novas atividades pelo professor, o repositório será atualizado com novas pastas, mantendo o histórico de evolução do nosso aprendizado.

--- 

## 📂 Estrutura do Repositório
A organização se dará por meio de diretórios nomeados de acordo com o tema ou número da lista de exercícios. 
* 📁 **`Listas`**:
  * 📁 **`01-java-thread-main/`**: Contém a primeira lista de exercícios focada em concorrência utilizando Threads em Java (abrange problemas como calculadora paralela, jogo de adivinhação, soma de matrizes e vetores).
  * 📁 **`02-java-thread-main/`**: Contém a segunda lista de exercícios.
  * 📁 **`01-react-native/my-app`**: Contém a primeira lista de exercícios focada em React Native.

---

## Como Executar os Projetos
Cada diretório de atividade possui o seu próprio arquivo `README.md` interno com instruções detalhadas e específicas para rodar os códigos daquela lista. 

De maneira geral, o fluxo básico nos terminais será:
```bash
# Entrar na pasta do exercício
cd nome-da-pasta

# Compilar os arquivos Java
javac *.java

# Executar a classe Principal
java Main
```

---
*Repositório mantido para fins acadêmicos.*


```bash

git add . && git commit -m "teste" && git push

```
```bash

git pull --no-rebase origin main

```
>>>>>>> c7b08c06f42a553b578b168cf4dd936b67be8778
