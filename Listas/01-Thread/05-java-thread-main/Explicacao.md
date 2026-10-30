# Explicação da Solução: Processador de Relatórios Multithread

Para essa atividade, a ideia foi entender como criar Threads de forma clássica em Java: usando a herança da classe `Thread`. Como cada relatório funciona de forma muito independente, criar uma classe própria para eles ajuda a organizar o código.

## 1. A Classe `ProcessadorRelatorio` (Herança de Thread)
Criamos um arquivo separado para esta classe e utilizamos `extends Thread`. Isso significa que nossa classe ganha todos os "poderes" de uma thread do Java.

* **O Construtor e o Nome da Thread:** O exercício pediu para passarmos o nome do departamento no construtor. A sacada aqui foi usar `this.setName(nomeDepartamento)`. A classe `Thread` do Java já possui um controle interno de nomes, então quando usamos `setName`, estamos batizando oficialmente a thread dentro da máquina virtual.
* **O Método `run()`:** Tudo que a thread vai fazer quando rodar em paralelo fica dentro do `run()`. 
* **O Loop e o `getName()`:** Fizemos um `for` de 1 a 3 para simular as 3 páginas do relatório. Na hora de imprimir, usamos `this.getName()`. Ele puxa exatamente aquele nome ("Faturamento" ou "Vendas") que configuramos no construtor, deixando a mensagem personalizada: `"[Nome_da_Thread] Gerando página X..."`.
* **A Pausa (`Thread.sleep`):** Usamos o `Thread.sleep(500)` para atrasar a execução em meio segundo por página, simulando o processamento. Como é de praxe com Threads, isso precisou ser envolvido num bloco `try-catch` para capturar uma possível `InterruptedException`.

## 2. A Classe `Main` (Disparando o Paralelismo)
O arquivo principal ficou bem simples, focado apenas em ser o "gatilho" do programa.

* **Instanciação:** Criamos dois objetos da nossa classe, passando os nomes "Faturamento" e "Vendas" como parâmetros.
* **O método `.start()`:** Esse é o ponto crucial. Se chamássemos o `.run()` direto, o programa rodaria sequencialmente (primeiro todo o Faturamento, depois todas as Vendas). Ao usar `.start()`, dizemos ao Java para abrir ramificações no processador. 
* O resultado é que as duas tarefas rodam juntas e as mensagens de páginas impressas no console começam a se misturar de forma intercalada, provando o paralelismo real.
