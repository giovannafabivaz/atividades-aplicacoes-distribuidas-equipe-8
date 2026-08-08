# atividades-aplicacoes-distribuidas-equipe-8

# 01 - Java Thread

Este projeto contém exercícios sobre o uso de Threads em Java. 
Em todos os exercícios foram utilizadas classes baseadas em uma estrutura de `Tarefa` (implementando a interface `Runnable`), responsáveis pela execução da tarefa em uma Thread, e a classe `Main`, responsável por iniciar o programa.

## 1. Thread Calculadora

Neste exercício, a classe da tarefa foi modificada para receber dois números e uma operação matemática.

As operações disponíveis são:
- Soma (`+`)
- Subtração (`-`)
- Multiplicação (`*`)
- Divisão (`/`)

Dentro do método `run()`, foi utilizado um laço de repetição para realizar três cálculos, com pausas de 1 segundo entre eles para simular o tempo de processamento. A cada repetição, o resultado é alterado combinando os números informados com o valor do índice `i` do laço.

O resultado de cada cálculo é mostrado no console, permitindo acompanhar o resultado parcial de cada passo.

---

## 2. Thread Jogo de Adivinhação

Neste exercício, a tarefa gera um número secreto de forma aleatória (de 1 a 10) utilizando a classe `Random` quando a Thread é iniciada.

Depois disso, são realizados até três palpites aleatórios, pausando 1 segundo a cada passo. A cada tentativa, o programa compara o palpite com o número secreto.

O programa informa se o palpite foi:
- Maior que o número secreto;
- Menor que o número secreto;
- Igual ao número secreto (acertou).

Quando o número é acertado, o laço é encerrado utilizando `break`, finalizando a execução imediatamente antes de esgotar os três palpites.

---

## 3. Thread Contador de Caracteres

Neste exercício, a classe da tarefa recebe uma String como parâmetro em seu construtor.

Para descobrir a quantidade de caracteres (incluindo os espaços), foi utilizado o método `length()` da classe `String`.

Após realizar a contagem, o programa faz uma pausa de 1 segundo e mostra no console o texto informado junto com a quantidade de caracteres encontrada.

---

## 4. Thread Soma de Vetores

Neste exercício, a classe da tarefa recebe dois vetores de números inteiros do mesmo tamanho.

Os elementos dos dois vetores são somados utilizando o mesmo índice dentro de um laço de repetição. Por exemplo, o elemento da posição `0` do primeiro vetor é somado ao elemento da posição `0` do segundo vetor. Há uma pausa de 1 segundo a cada soma.

O resultado de cada posição é mostrado no console, permitindo acompanhar o resultado parcial da soma.

### Paralelização

Para vetores muito grandes, o processamento poderia ser dividido entre várias Threads.

Por exemplo, uma Thread poderia ficar responsável pelas primeiras posições do vetor (ex: 0 a 500.000), enquanto outras Threads processariam as demais posições.

Dessa forma, diferentes partes do vetor poderiam ser processadas ao mesmo tempo, aproveitando melhor os recursos do computador e reduzindo o tempo total.

---

## 5. Thread Soma de Matrizes

Neste exercício, a classe da tarefa recebe duas matrizes de números inteiros.

A soma é realizada elemento por elemento, considerando a mesma linha e coluna nas duas matrizes.

Para percorrer as matrizes, foram utilizados dois laços de repetição aninhados: um para percorrer as linhas e outro para percorrer as colunas.

O resultado de cada posição é calculado, e após uma pausa de 1 segundo, é mostrado no console.

### Paralelização

Para matrizes grandes, o processamento poderia ser dividido entre várias Threads.

Uma possibilidade seria dividir as linhas da matriz entre as Threads. Cada Thread ficaria responsável por realizar a soma de determinadas linhas de forma independente (ex: Thread 1 processa as linhas de cima, Thread 2 processa as linhas de baixo).

Assim, diferentes partes da matriz poderiam ser processadas simultaneamente, tornando o cálculo muito mais eficiente e rápido em estruturas grandes.

---

## Conclusão

Os exercícios permitiram praticar o uso de Threads em Java e entender como uma tarefa pode ser executada de forma independente.

Também foi possível aplicar Threads em diferentes situações, como cálculos, geração de números aleatórios, manipulação de Strings, vetores e matrizes.
