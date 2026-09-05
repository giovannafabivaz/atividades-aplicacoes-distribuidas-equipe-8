# Explicação da Solução: Gerenciador de Downloads (Threads em Java)

## 1. A classe `Download` (Download.java)

Esta classe é o "trabalhador" do nosso código. Eu fiz a classe `Download` herdar de `Thread` usando `class Download extends Thread`. Isso é essencial para conseguirmos usar os métodos de concorrência que o Java oferece.

### Atributos e Construtor:
*   **Atributos:** Criei os atributos privados `nome` (String) e `totalEtapas` (int). O `totalEtapas` foi adicionado como pedido para que cada arquivo demore um tempo diferente.
*   **Construtor:** O construtor recebe esses dois valores e os atribui aos atributos da classe para inicializar nosso objeto de download.

### O método `run()`:
Quando trabalhamos com Threads, o que estiver dentro do método `run()` é o que será executado em paralelo.
*   **Laço `for`:** Ele vai de `1` até `totalEtapas`. A cada iteração, ele imprime a mensagem pedida informando o andamento (Ex: "Download [Nome] baixando parte X de Y...").
*   **Simulando o tempo (`Thread.sleep(1000)`):** Para cada etapa, eu coloco a thread para "dormir" por 1000 milissegundos (1 segundo). Isso simula o custo de fazer o download.
*   **Tratamento de Exceções (`try-catch`):** O Java nos obriga a colocar o `Thread.sleep()` dentro de um bloco `try-catch`. Isso é perfeito para o nosso problema! Se a thread principal cancelar esse download enquanto ele estiver dormindo, o Java lança uma `InterruptedException`.
*   **Finalização:** Se o laço for concluído sem interrupções, ele imprime a mensagem de "SUCESSO". Se ele for interrompido e cair no `catch`, ele imprime o alerta "CRÍTICO", avisando que o tempo estourou.

---

## 2. A classe Principal (`Main.java`)

Aqui é onde o programa começa e onde nós atuamos como o "gerente" que supervisiona os downloads.

### Instanciando e Iniciando os Downloads:
*   Criei três objetos da classe `Download` passando os nomes e tamanhos exigidos: `d1` (2 etapas = 2s), `d2` (4 etapas = 4s) e `d3` (8 etapas = 8s).
*   Para que eles rodem ao mesmo tempo de verdade, nós não podemos chamar o método `run()` direto. O correto é chamar o método `.start()`. Ao rodar `d1.start()`, `d2.start()` e `d3.start()`, o Java coloca as três tarefas para trabalhar em paralelo no fundo.

### O Tempo de Tolerância (Timeout):
*   Assim que os downloads começam, eu uso `Thread.sleep(5000)` na thread principal (a Main).
*   Isso faz com que o programa principal pause por 5 segundos enquanto os downloads continuam rodando de forma independente.

### Verificação e Cancelamento:
*   Depois que a Main "acorda" desses 5 segundos, ela precisa verificar como estão as tarefas.
*   Usei comandos `if` com o método `.isAlive()` para checar o status de cada download. Se a thread ainda estiver rodando, o método retorna `true`.
*   **O que acontece na prática:** O Arquivo Pequeno (2s) e o Médio (4s) já terminaram há muito tempo. O método `isAlive()` deles vai dar `false`, e eles passam direto sem sofrer interrupção.
*   Porém, o Arquivo Grande (8s) ainda está processando. O `isAlive()` dele vai dar `true`, ativando o bloco `if`. 
*   Dentro do `if`, eu chamo o comando `d3.interrupt()`. Esse comando manda um sinal lá para a thread do Arquivo Grande, acordando ela à força, disparando a exceção no bloco `catch` e cancelando a operação no meio do caminho.
