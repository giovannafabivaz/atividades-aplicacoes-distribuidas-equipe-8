# 02-java-thread

## Compilação:
```bash
javac *.java
```
## Execução:
```bash
java Main
```


# Exercícios:

Em Java, implemente a aplicação abaixo.
Em Markdown, explique sua solução.

## 1) Serviço de Backup

Escreva um programa em Java que simule um processo de backup dividido em 5 etapas. Cada etapa simula um processamento pesado demorando 1 segundo (Thread.sleep(1000)).

A thread principal (main) deve iniciar o backup e dar ao usuário a chance de interrompê-lo caso o tempo limite de tolerância seja estourado.

# Requisitos de Implementação:

A classe do Backup: crie uma classe chamada ServicoBackup que implementa Runnable.

O loop de etapas: dentro do método run(), faça um loop que vai do passo 1 até o 5. A cada passo, printe qual etapa está sendo executada.

Tratamento de interrupção: se o processo de backup sofrer um .interrupt(), capture a exceção no bloco catch, imprimindo a mensagem "-> CRÍTICO: O backup foi cancelado pelo usuário! Limpando arquivos temporários..." e encerre a thread com return.

Mensagem de sucesso: se o loop terminar todos os 5 passos sem ser interrompido, exiba "-> SUCESSO: Backup concluído e salvo no servidor!".

# Cenários de Teste na classe Principal (main):

Modifique o tempo que a thread main espera no método Thread.sleep(...) para testar os dois comportamentos abaixo:

Cenário A (Cancelamento): faça a main esperar apenas 2 segundos (2000 ms) antes de chamar o .interrupt(). O programa deve parar no meio (provavelmente no passo 2 ou 3) e acionar a mensagem de erro crítico.

Cenário B (Sucesso): altere a main para esperar 7 segundos (7000 ms) antes de disparar o .interrupt(). Como 7 segundos é tempo suficiente para o backup rodar os seus 5 segundos totais, a tarefa deve terminar com sucesso antes mesmo de a main tentar interrompê-la.

