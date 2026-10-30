# Explicação da Solução: Semáforos em Java

Fala aí! Pra fechar essa lista de Semáforos, mantive a estrutura de separar cada entidade em sua própria classe para deixar o código legível, e juntei todas as execuções na `Main` para facilitar os testes no terminal. O Semáforo (`Semaphore`) funciona de maneira bem similar ao Mutex, mas com a vantagem de permitir que N threads (em vez de apenas 1) entrem na seção crítica ao mesmo tempo.

## Exercício 1: Estacionamento VIP (Fairness / Justiça)
* **O que fiz:** Na instanciação, usei `new Semaphore(2, true)`. Na `Main`, criei um laço que dispara 5 carros, dando um `Thread.sleep(100)` entre as partidas para criar a fila gradualmente.
* **O impacto na fila:** Aquele parâmetro `true` habilita o *fairness* (justiça). Com isso, o Java passa a usar uma política estrita de FIFO (First-In, First-Out). Isso garante que o carro que deu o `acquire()` primeiro será obrigatoriamente o primeiro a estacionar quando uma vaga for liberada, impedindo que threads mais rápidas "furem a fila" de quem já estava aguardando.

## Exercício 2: Rate Limiter da API
* **O que fiz:** Criei a classe `ClienteAPI`. Na `Main`, declarei um `Semaphore(3)` e lancei 10 threads de uma vez.
* **Como funciona:** O semáforo concede as 3 primeiras permissões. Esses 3 clientes entram e processam suas consultas no banco de dados. Os outros 7 ficam suspensos na fila. Conforme os clientes iniciais dão o `release()`, as permissões retornam para o semáforo, e o Java acorda as próximas threads da fila para conectarem.

## Exercício 3: Ponte Estreita (Semáforo Binário)
* **O que fiz:** Iniciei um `Semaphore(1)`. Quando um semáforo só tem 1 vaga, ele atua praticamente como um Mutex. 
* **Como funciona:** A classe `CarroPonte` recebe a direção de onde o carro vem. Como só há uma permissão, o carro que conseguir executar o `acquire()` primeiro bloqueia a ponte. Qualquer outro carro vindo de qualquer direção fica aguardando o `release()` para poder iniciar a travessia de forma segura.

## Exercício 4: Laboratório de Informática
* **O que fiz:** Usei `Semaphore(5)` para espelhar as 5 máquinas físicas do laboratório e lancei 8 threads de alunos.
* **Como funciona:** Para atender ao pedido de tempo aleatório do exercício, importei a classe `Random` e coloquei ela dentro do `Thread.sleep()`. Assim, cada aluno fica um tempo diferente no laboratório. Logo que um aluno encerra e libera a permissão, o próximo estudante da fila assume aquele PC.

## Exercício 5: Buffet de Restaurante
* **O que fiz:** A lógica de recursos limitados se repete aqui. Criei um `Semaphore(4)` para controlar a lotação da pista do buffet.
* **Como funciona:** Iniciei diversas threads `ClienteBuffet`. O semáforo barra a entrada da 5ª pessoa. Assim que um dos 4 primeiros finaliza a montagem do prato e dá o `release()`, o espaço físico na pista "abre" para o próximo cliente entrar.
