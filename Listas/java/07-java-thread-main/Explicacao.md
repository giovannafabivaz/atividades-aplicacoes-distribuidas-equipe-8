# Explicação da Solução: Mutex e Seção Crítica

E aí! Pra essa lista, o foco total é no uso do **Mutex** (Mutual Exclusion) com a classe `ReentrantLock` do Java. Ele funciona como aquele "cadeado" que a gente viu na teoria, impedindo que os dados se corrompam na seção crítica quando várias tarefas rodam juntas.

## Exercício 1: Bloqueio sem Espera Infinita (tryLock)
* **O que foi feito:** Em vez de usar o `.lock()` comum que deixa a thread travada esperando, usei o método `.tryLock()`.
* **Como funciona:** Ele tenta pegar a chave de acesso. Se a conta já estiver ocupada por outra thread, o `tryLock()` retorna `false` na mesma hora. Aí, usando um `if/else`, a gente consegue barrar a segunda thread e imprimir a mensagem de desistência, evitando que o programa fique congelado aguardando na fila.

## Exercício 2: Sistema de Votação (Contador Global)
* **O que foi feito:** Criei a classe `UrnaEletronica` implementando a interface `Runnable`. Ela tem uma variável global (`static`) para o `totalVotos` e um `ReentrantLock` também global.
* **Como funciona:** O laço repete 100 vezes. Dentro dele, eu tranquei a linha `totalVotos++` com o mutex. Como disparamos 3 threads iguais na `Main`, o mutex garante que nenhuma sobrescreva a contagem em andamento da outra, resultando em exatos 300 votos no final.

## Exercício 3: Reserva de Assentos (Verificação Dupla)
* **O que foi feito:** Criei a classe `Cinema` gerenciando o vetor `boolean[] assentos`.
* **Como funciona:** Quando duas threads tentam comprar a cadeira 5 ao mesmo tempo, a primeira que chega passa pelo `.lock()`, vê que a posição está `false` (livre) e altera para `true`. A segunda thread é obrigada a esperar do lado de fora do `.lock()`. Quando ela finalmente consegue entrar na seção crítica, ela verifica o vetor de novo, vê que o status agora é `true` e cai direto no `else`, imprimindo "Assento indisponível".

## Exercício 4: Gerador de Identificadores Únicos
* **O que foi feito:** Uma classe bem direta com a variável `idAtual = 0` e o método `obterProximoId()`.
* **Como funciona:** Tranquei o incremento matemático (`idAtual++`) com o Mutex. Na classe `Main`, fiz um laço `for` rápido disparando 5 threads simultâneas pedindo um ID para impressão. O Mutex organiza essa "briga", forçando a execução sequencial por baixo dos panos para que cada thread pegue seu ID corretamente sem pular ou duplicar números.

## Exercício 5: Gravação de Log Compartilhado
* **O que foi feito:** O método `escreverMensagem()` imprime a abertura do arquivo, executa um `Thread.sleep(300)` e depois imprime o fechamento.
* **Como funciona:** Sem o Mutex, uma thread ia começar a escrever enquanto a outra ainda estava no meio da lentidão do `sleep`. Com o bloqueio englobando todo o bloco (da abertura ao fechamento), o Java obriga que toda a mensagem seja impressa e concluída antes da próxima thread poder entrar na seção de gravação.
