# 03-java-thread

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

## 1) Gerenciador de Downloads (DownloadManager)

Você foi contratado para criar um sistema básico de downloads. O sistema deve disparar três downloads paralelos. Cada download possui tamanhos e tempos de execução diferentes e o usuário deve ser capaz de cancelar apenas os downloads que estourarem o tempo limite.

## Requisitos de Implementação:

### Modifique a classe Tarefa:

Renomeie a classe para Download.

Adicione um novo atributo privado no construtor chamado `int totalEtapas` (em vez de fixar o loop em 5, cada download terá seu próprio número de etapas/tamanho).

Atualize as mensagens na tela para fazerem sentido com um download (ex: "Download [Nome] baixando parte X de Y...").

### Crie a classe Principal (Main):

Instancie e dispare três threads de download simultaneamente com as seguintes configurações:

Download 1: Nome "Arquivo_Pequeno", com 2 etapas (duração total: 2 segundos).

Download 2: Nome "Arquivo_Medio", com 4 etapas (duração total: 4 segundos).

Download 3: Nome "Arquivo_Grande", com 8 etapas (duração total: 8 segundos).

Faça a thread main aguardar por 5000 milissegundos (5 segundos) usando Thread.sleep(5000).Após esse tempo de espera, a main deve verificar cada uma das três threads. Se a thread ainda estiver viva (.isAlive()), envie o sinal de interrupção (.interrupt()) individualmente nela.

## Resultado Esperado no Console:

Ao rodar o programa com a tolerância de 5 segundos, você deverá observar que:

1. O Arquivo_Pequeno e o Arquivo_Medio devem terminar com SUCESSO (pois precisam de 2s e 4s, respectivamente).

2. O Arquivo_Grande deve ser interrompido no meio do caminho pela main (por volta da etapa 5 ou 6), disparando o alerta CRÍTICO.

