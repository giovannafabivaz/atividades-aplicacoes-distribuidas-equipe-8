# 08-java-thread

# Semáforo

Implementação de um Semáforo em Java.

Neste exemplo, é criado um estacionamento que possui apenas 3 vagas disponíveis e 8 threads (carros) tentam estacionar ao mesmo tempo.

# Comandos

```bash
javac *.java
```

```bash
java Main
```

# Exercício

## Exercício 1: Modificação do Exemplo (Estacionamento com Vagas VIP)

Objetivo: modificar o exemplo do Estacionamento para criar uma prioridade no uso das permissões do semáforo.

Instruções: ao instanciar o semáforo do estacionamento, passe o parâmetro de justiça (fairness) como verdadeiro: `new Semaphore(2, true)`. 

Crie um loop na `main` que dispara os carros com pequenos intervalos de tempo. Comente no código o que essa modificação altera na fila de espera dos carros bloqueados (Política FIFO).

## Exercício 2: O Limitador de Requisições de uma API (Rate Limiter)

Objetivo: limitar a quantidade de conexões simultâneas a um servidor para evitar sobrecarga.

Cenário: um servidor de banco de dados só aguenta processar 3 consultas ao mesmo tempo.

Implementação: use um `Semaphore(3)`. Crie uma classe `ClienteAPI` que tenta conectar ao servidor. Dispare 10 threads simulando clientes acessando a API simultaneamente. O semáforo deve garantir que apenas 3 processem por vez, enquanto as outras 7 aguardam na fila.

## Exercício 3: A Travessia da Ponte Estreita

Objetivo: controlar o fluxo de tráfego em uma ponte que suporta apenas 1 carro por vez em uma única direção.

Cenário: embora pareça um Mutex, implemente isso usando um Semaphore(1) (Semáforo Binário).

Desafio: crie Threads representando "Carro vindo da Esquerda" e "Carro vindo da Direita". Use os métodos `acquire()` e `release()` para garantir que nenhum carro colida no meio da ponte.

## Exercício 4: O Laboratório de Informática (Controle de Recursos Limitados)

Objetivo: gerenciar o acesso a um número exato de hardwares físicos.

Cenário: um laboratório de faculdade possui apenas 5 computadores disponíveis para os alunos estudarem.

Implementação: crie um semáforo inicializado com 5. Dispare 8 threads de alunos. Cada aluno "adquire" um computador, estuda por um tempo aleatório (Thread.sleep) e depois "libera" o computador para o próximo estudante da fila.

## Exercício 5: O Buffet de Restaurante (Várias Seções Simultâneas)

Objetivo: aplicar semáforos para simular um limite de pessoas em uma área comum.

Cenário: uma pista de buffet self-service permite que no máximo 4 pessoas se sirvam ao mesmo tempo por questões de espaço físico.

Implementação: represente os clientes como threads. Use um Semaphore(4) para gerenciar a entrada na pista de comida. Conforme um cliente termina de montar o prato e sai, ele abre espaço para que a próxima pessoa que está com fome possa entrar na pista do buffet.