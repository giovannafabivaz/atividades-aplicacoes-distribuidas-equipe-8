Responsabilidade de cada estágio

O Producer gera números aleatórios entre 0.0 e 1.0 usando Random.nextDouble() e envia cada número pelo primeiro PipedOutputStream.

O Filter lê cada número recebido pelo primeiro pipe e encaminha exatamente o mesmo valor pelo segundo PipedOutputStream. Ele não calcula a média nem modifica o valor. Depois de escrever, chama flush() para disponibilizar imediatamente o dado ao próximo estágio.

O Consumer lê os valores do segundo pipe e classifica cada um deles. Valores menores que 0.2 são contabilizados separadamente; valores maiores que 0.8 também são contabilizados separadamente; os demais pertencem à faixa [0.2, 0.8]. A cada dez valores recebidos, o Consumer imprime um relatório com as três quantidades e o total processado.

Como executar

Abra o terminal na pasta que contém exercicio2 e execute:

Bash


cd exercicio2
javac pipetest/*.java
java pipetest.PipeTest



O programa é contínuo e imprime relatórios após 10, 20, 30 valores e assim por diante. Depois de observar pelo menos dois relatórios, pressione Ctrl+C para encerrá-lo.

