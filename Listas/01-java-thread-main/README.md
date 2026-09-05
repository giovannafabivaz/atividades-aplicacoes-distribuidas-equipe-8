# 01-java-thread

## Compilação:
javac *.java

## Execução:
java Main

# Exercícios:

Em Java, implemente as aplicações abaixo.
Em Markdown, explique suas soluções.

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

## 6. Contabilização e Pool de Threads

Neste exercício, a classe `Consumidor` foi modificada para contabilizar as tarefas executadas e permitir configurar a quantidade máxima de Threads.

O atributo `tarefasExecutadas` registra as tarefas concluídas com sucesso, utilizando `join()` para aguardar o término de cada Thread. Também foi utilizado `System.currentTimeMillis()` para calcular o tempo total de execução.

Além disso, foram realizados testes utilizando diferentes quantidades de Threads: **2, 4 e 6**. O tempo de execução de cada configuração foi medido e comparado, permitindo identificar automaticamente qual configuração apresentou o melhor desempenho.

Dessa forma, o exercício demonstra como a quantidade de Threads pode influenciar o tempo de processamento de um conjunto de tarefas.

---

## Conclusão

Os exercícios permitiram praticar o uso de Threads em Java e entender como uma tarefa pode ser executada de forma independente.

Também foi possível aplicar Threads em diferentes situações, como cálculos, geração de números aleatórios, manipulação de Strings, vetores e matrizes.
