Funcionamento dos estágios

O Producer gera exatamente 100 números aleatórios entre 0.0 e 1.0. Depois de enviar o centésimo número, ele fecha o primeiro pipe. O fechamento informa ao próximo estágio que não haverá mais valores.

O Filter recebe cada valor pelo primeiro pipe e encaminha o mesmo valor pelo segundo pipe. Após cada escrita, chama flush(). Quando o primeiro pipe é encerrado, o Filter fecha o segundo pipe para informar ao Consumer que o fluxo terminou.

O Consumer recebe os valores, classifica cada um e atualiza três contadores: menores que 0.2, maiores que 0.8 e dentro da faixa [0.2, 0.8]. A cada dez valores, ele imprime um relatório. A quantidade fora da faixa é calculada pela soma dos valores menores que 0.2 com os valores maiores que 0.8.
A porcentagem é calculada pela fórmula:

Plain Text


porcentagem fora da faixa = (quantidade fora da faixa * 100.0) / total processado



Como o limite pertence à faixa esperada, o código utiliza valor < 0.2 e valor > 0.8. Assim, valores exatamente iguais a 0.2 ou 0.8 ficam dentro da faixa.


O Consumer para depois de processar 100 valores. O PipeTest usa join() para esperar que o Producer, o Filter e o Consumer terminem. Depois que as três threads são encerradas, o programa imprime:

Plain Text


Pipeline encerrado após processar 100 valores.



Como executar

Abra o terminal na pasta principal do projeto, entre em exercicio3 e execute:

Bash


cd exercicio3
javac pipetest/*.java
java pipetest.PipeTest



