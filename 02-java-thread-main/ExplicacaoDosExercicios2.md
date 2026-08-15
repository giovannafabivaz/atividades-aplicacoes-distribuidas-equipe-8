Explicação dos Exercícios
1. Thread Serviço de Backup
Neste exercício, a classe da tarefa (ServicoBackup) implementa a interface Runnable para simular uma rotina de salvamento de arquivos dividida em 5 etapas sequenciais.

Dentro do método run(), foi utilizado um laço de repetição que vai do passo 1 até o 5. A cada etapa, o programa faz uma pausa de 1 segundo utilizando Thread.sleep(1000) para simular o processamento de gravação.

A thread principal (main) dispara a tarefa e gerencia um tempo limite de tolerância:

Se todas as 5 etapas forem concluídas dentro do tempo, o programa exibe uma mensagem de sucesso informando que o backup foi salvo no servidor.

Se a tolerância for estourada antes do término (ex: espera de 2 segundos), a main chama o método interrupt(). A interrupção é capturada no bloco catch (InterruptedException), disparando um alerta crítico de cancelamento e encerrando a execução imediatamente com return para liberar os arquivos temporários.

2. Thread Gerenciador de Downloads
Neste exercício, a classe da tarefa (Download) foi estruturada para permitir múltiplos downloads simultâneos com durações e tamanhos independentes.

O construtor da classe recebe o nome do arquivo e a quantidade total de etapas (totalEtapas). Dentro do método run(), um laço de repetição exibe o progresso de cada parte baixada com pausas de 1 segundo entre elas.

A classe principal dispara três downloads em paralelo com tempos distintos:

Arquivo_Pequeno: 2 etapas (2 segundos);

Arquivo_Medio: 4 etapas (4 segundos);

Arquivo_Grande: 8 etapas (8 segundos).

A thread main aguarda um tempo limite fixo de 5 segundos. Após esse intervalo, ela verifica individualmente cada thread com o método isAlive():

Os arquivos pequeno e médio terminam com sucesso, pois necessitam de menos de 5 segundos.

O arquivo grande ainda se encontra em execução, sendo interrompido individualmente com .interrupt(), o que aciona o tratamento de cancelamento por timeout sem afetar os outros downloads já concluídos.

3. Thread Painel de Telemetria (Lambda Edition)
Neste exercício, o monitoramento de sensores industriais foi implementado diretamente dentro da classe principal utilizando Expressões Lambda (() -> { ... }), aproveitando o fato de a interface Runnable ser uma interface funcional.

A thread do sensor executa um laço de 5 repetições para coletar dados de temperatura, realizando pausas de 800 milissegundos a cada leitura.

Enquanto o sensor roda em segundo plano, a thread principal (main) continua sua execução normalmente, inicializando a interface gráfica e simulando a navegação do usuário com uma pausa de 1,5 segundos.

As mensagens do sensor e da interface se intercalam no console, comprovando a execução assíncrona e paralela sem a necessidade de criar arquivos ou classes adicionais.

4. Thread Processador de Relatórios Multithread
Neste exercício, a classe ProcessadorRelatorio foi construída herdando diretamente a classe Thread (extends Thread), adequada para processos independentes e isolados.

No construtor, o nome do departamento é atribuído diretamente à thread por meio do método this.setName(...). Dentro do método run(), um laço simula a geração de 3 páginas de relatório financeiro com pausas de 500 milissegundos entre elas.

A classe principal instancia e inicia simultaneamente duas threads: uma para o departamento de "Faturamento" e outra para "Vendas". As páginas de ambos os relatórios são geradas e exibidas de forma intercalada no console, evidenciando o processamento paralelo entre diferentes setores da empresa.

Conclusão
Os exercícios permitiram aprofundar o domínio sobre programação concorrente e multithreading em Java, cobrindo diferentes formas de criação de threads (implementação de Runnable, herança direta de Thread e expressões Lambda).