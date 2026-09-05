# Explicação da Solução: Pool de Threads e Prioridades

## Exercício 1: Contabilização e Tempo
Na classe Consumidor, adicionei o atributo tarefasExecutadas para contar quantas tarefas terminaram, conforme a instrução.

* O pulo do gato foi colocar um `this.tarefasExecutadas++` logo depois do método `join()`. Como o `join()` bloqueia a execução até a thread terminar, se ele passar dessa linha sem dar erro (sem cair no catch), significa que a tarefa executou até o fim com sucesso.
* Para medir o tempo, usei o `System.currentTimeMillis()` antes de começar o processamento em lotes e logo depois que todas as threads terminaram. Subtraindo o tempo final pelo inicial e dividindo por 1000.0, temos o tempo cravado em segundos!

## Exercício 2: Pool de Threads Configurável
A ideia aqui foi criar um sistema que permite variar o tamanho do pool de threads, sem engessar a quantidade rodando ao mesmo tempo.

* Modifiquei o construtor do Consumidor para aceitar `maxThread` como parâmetro.
* Dentro do método `consumir()`, fiz o laço for avançar em blocos (de `maxThread` em `maxThread`). Se o max for 4, ele pega as tarefas de 4 em 4. Inicia as 4 juntas com `.start()`, segura a execução dando `.join()` nas 4, e depois vai para o próximo lote.
* Na Main, criei as 3 instâncias exigidas (2, 4 e 6 threads). Chamei a execução de cada uma criando vetores de tarefas novos (isso é importante porque uma thread só pode dar `start()` uma vez no Java), guardei o tempo numa variável e no final usei `Math.min()` para checar qual número era o menor e exibir a configuração mais eficiente.

## Exercício 3: Sistema de Prioridades
Para as tarefas rodarem na ordem certa:

* Criei o enum chamado Prioridade com ALTA(1), MEDIA(2) e BAIXA(3).
* Coloquei esse enum como atributo na classe Tarefa.
* Para o Consumidor conseguir ordenar tudo sozinho, fiz a classe Tarefa implementar a interface nativa `Comparable<Tarefa>`. Assim, eu só precisei dar um `Arrays.sort(tarefas)` dentro do Consumidor, e ele automaticamente ordenou o vetor colocando as tarefas de ALTA prioridade na frente das outras.
* Por fim, adicionei alguns contadores rápidos dentro do laço do `join()` para ir somando quantas tarefas de cada tipo rodaram e imprimir o resumo no final.
