# 04-java-thread

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

## 1) Painel de Telemetria (Lambda Edition)

Você precisa criar um sistema que monitora os sinais vitais de uma máquina industrial. Para evitar criar arquivos de classes separados e manter o código compacto, você deve implementar essa lógica diretamente dentro do método main usando Expressões Lambda.

### Requisitos de Implementação:

#### A Thread de Monitoramento: 
Dentro do método main, crie e inicie uma nova Thread utilizando uma Expressão Lambda `() -> { ... }`. Essa thread deve rodar um loop que se repete 5 vezes. A cada iteração do loop, exiba na tela: 

`"[Sensor] Coletando dados de temperatura... Passada X de 5 etapas".`

Faça a thread simular o tempo de leitura do sensor pausando por 800 milissegundos (`Thread.sleep(800)`) a cada volta. Lembre-se de tratar a exceção com um bloco try-catch interno. Quando o loop terminar, exiba a mensagem: 

`"[Sensor] Monitoramento encerrado com sucesso!"`.

#### A Thread Principal (Main): 
Logo após a linha que inicia a thread do sensor (método .start()), faça a thread main imprimir a mensagem: 

`"[Painel] Interface gráfica iniciada e pronta para o usuário."`.

Adicione um `Thread.sleep(1500)` na main e, logo em seguida, imprima: 

`"[Painel] Usuário navegando pelos menus..."`.

#### Comportamento Esperado no Console: 
Ao rodar o programa, você verá as mensagens do Sensor (Lambda) e do Painel (Main) se misturarem no console, provando que ambas estão sendo executadas simultaneamente em paralelo.
