# Explicação da Solução: Painel de Telemetria (Lambda Edition)

Nesta atividade, o objetivo foi criar um sistema de monitoramento paralelo, mas de uma forma bem mais enxuta usando Expressões Lambda, eliminando a necessidade de criar um arquivo de classe separado para a Thread.

## 1. A Thread de Monitoramento (Expressão Lambda)
Em vez de criar uma classe que herda de `Thread` ou implementa `Runnable`, instanciamos a thread passando uma função anônima direto no construtor: `new Thread(() -> { ... })`. Essa é a famosa expressão lambda do Java. 
* **Processamento (O Loop):** Dentro da lambda, inserimos um laço `for` que roda de 1 a 5. Em cada volta, ele imprime a mensagem `"[Sensor] Coletando dados de temperatura..."`.
* **Custo de Tempo:** Para simular o atraso da leitura do sensor, usamos o `Thread.sleep(800)` para que a thread pare de executar por 800 milissegundos a cada iteração.
* **Segurança:** Todo `sleep` pode sofrer uma interrupção, então o código da lambda precisa ser envolvido em um bloco `try-catch` para capturar a `InterruptedException`. Após as 5 passadas do laço, a mensagem de sucesso do monitoramento é exibida.

## 2. A Thread Principal (Main)
A grande sacada das threads é o que acontece depois que chamamos o `.start()`. 
* Assim que `sensor.start()` é executado, a thread do sensor começa a rodar no plano de fundo. A thread `main` não fica esperando o sensor terminar; ela avança imediatamente para a próxima linha de código, imprimindo `"[Painel] Interface gráfica iniciada e pronta para o usuário."`.
* Logo em seguida, a própria `main` é colocada para "dormir" com um `Thread.sleep(1500)`. 
* **A Mágica do Paralelismo:** Enquanto a `main` está dormindo por 1,5 segundos, a thread do sensor continua acordada no plano de fundo imprimindo as suas coletas de temperatura. 
* Quando a `main` finalmente desperta do seu sono, ela imprime `"[Painel] Usuário navegando pelos menus..."`.

O resultado prático ao rodar o programa é ver as mensagens do painel (geradas pela `main`) e as mensagens do sensor (geradas pela thread em lambda) se embaralhando no terminal, o que comprova visualmente a execução simultânea das duas tarefas.
