# Explicação Exercícios aula 09: Estruturas e Decisões de Projeto com tryLock()

Este documento detalha o raciocínio por trás da construção dos 9 exercícios sobre a utilização de `tryLock()`. A ideia aqui é entender não apenas *o que* o código faz, mas *por que* escolhemos determinadas estruturas em vez de outras (como `lock()` tradicional, condicionais simples ou laços de repetição).

---

## 1. Tentativa Imediata (`tryLock` sem parâmetros)
**O que foi usado:** `mutex.tryLock()` puro, sem timeout.
**Por que essa estrutura:** Quando chamamos apenas `tryLock()`, o Java verifica a disponibilidade do cadeado naquele exato milissegundo. Se quiséssemos que a thread aguardasse na fila, usaríamos `mutex.lock()`. Escolhemos o `tryLock()` para forçar a thread a tomar uma decisão instantânea: ou entra na seção crítica ou desiste imediatamente e executa o bloco `else`. 
**Estrutura de repetição:** O laço `do-while` envolvendo a instanciação das threads foi usado para atender ao requisito "executado quantas vezes o usuário desejar", recriando as threads a cada ciclo (já que uma thread não pode dar `.start()` duas vezes no Java).

## 2. Timeout Configurável (`tryLock` com tempo limite)
**O que foi usado:** `mutex.tryLock(timeout, TimeUnit.SECONDS)`.
**Por que essa estrutura:** Na vida real, desistir imediatamente (como no Ex 1) pode ser agressivo demais. Aqui, damos uma "janela de tolerância" para a thread. Se o cadeado liberar dentro desse tempo, ela entra; se o cronômetro zerar, ela desiste. 
**Simulação de carga:** Usamos `Thread.sleep` com um tempo aleatório (`Random`) dentro da seção crítica para garantir que algumas threads segurem o cadeado por mais tempo do que a tolerância das outras, forçando o cenário de desistência.

## 3. Liberação Garantida (A importância do `try/finally`)
**O que foi usado:** Bloco `try { ... } finally { mutex.unlock(); }`.
**Por que essa estrutura:** Se a thread consegue o lock e, lá dentro, ocorre um erro grave (como a divisão por zero do exercício), o Java lança uma `Exception` e interrompe a execução daquele bloco imediatamente. Se o `mutex.unlock()` estivesse no final do `try` normal, ele nunca seria lido, e o cadeado ficaria trancado para sempre (deadlock). O bloco `finally` é a única estrutura do Java que tem execução 100% garantida, com ou sem erro, sendo o único lugar seguro para soltar a tranca.

## 4. Conta Bancária (UX e Concorrência)
**O que foi usado:** Desvio de fluxo com `if/else` baseado no retorno booleano do `tryLock()`.
**Por que essa estrutura:** Sistemas bancários antigos usavam `.lock()`, o que congelava a tela do caixa eletrônico ou do app enquanto o banco de dados processava outra operação. Usando `.tryLock()`, evitamos o travamento da thread da interface (UI). Se estiver ocupado, o código cai no `else` e pode renderizar um aviso amigável na tela do usuário quase que instantaneamente.

## 5. Urna Eletrônica (Laço de Insistência)
**O que foi usado:** Laço `while` testando o `tryLock()` (`while (votosEnviados < 100)`).
**Por que essa estrutura:** Diferente do banco ou do cinema, aqui nós **não podemos desistir** da operação. Cada voto é sagrado. Se usássemos um `if(!mutex.tryLock())`, a thread abortaria e o voto seria perdido. O `while` faz com que, caso o barramento esteja ocupado com outra urna, a thread fique "martelando" o `tryLock()` repetidas vezes até conseguir a brecha para somar o voto.

## 6. Reserva de Cinema (Tolerância Estrita)
**O que foi usado:** `tryLock(3, TimeUnit.SECONDS)` atrelado a uma verificação dupla (Double-check locking) no array de assentos.
**Por que essa estrutura:** Se dois clientes clicam no assento 5 ao mesmo tempo, um deles pega o lock e demora 4 segundos simulando o processamento do cartão. O segundo cliente fica no `tryLock` aguardando. Como o timeout dele é de 3 segundos, ele estoura a paciência antes da liberação e recebe o aviso. Isso otimiza o uso do servidor, não mantendo requisições presas por minutos em assentos já vendidos.

## 7. Gerador de Identificadores (Garantia de Sequência)
**O que foi usado:** Variável booleana de controle (`sucesso = false`) junto com um laço `while (!sucesso)`.
**Por que essa estrutura:** Novamente, desistir de gerar um ID não é uma opção, pois quebraria a integridade do banco de dados. Usamos o `while` para forçar a thread a tentar repetidamente até o `tryLock` retornar `true`. Apenas quando o ID é lido e incrementado, mudamos a flag `sucesso` para `true`, quebrando o laço. Isso garante o isolamento atômico da operação matemática `proximoId++`.

## 8. Gravador de Log Compartilhado (Fail-Fast)
**O que foi usado:** Timeout super baixo (`500, TimeUnit.MILLISECONDS`).
**Por que essa estrutura:** A operação de salvar logs não é a atividade principal do sistema (a principal é vender, autenticar, etc.). O sistema não pode ficar lento porque o HD demorou a gravar um arquivo de texto. Por isso, usamos uma tolerância de apenas meio segundo. O módulo tenta gravar; se o arquivo estiver ocupado por muito tempo, a thread aplica o padrão "Fail-Fast", joga o log no terminal para não travar e segue a vida.

## 9. Jogo de Dados (Validação na Seção Crítica)
**O que foi usado:** Uma dupla checagem do estado do jogo (`if (totalPontos < 100)`) dentro da zona já protegida pelo Mutex.
**Por que essa estrutura:** Imagina que o grupo tem 98 pontos. Duas threads geram números aleatórios (ex: 3 e 4) e tentam somar. Uma consegue o lock primeiro, soma 3, chega a 101 pontos e solta a tranca. Se a segunda thread entrar sem verificar o placar novamente, ela somará 4 em um jogo que já acabou. Por isso, mesmo após conseguir o `tryLock`, precisamos testar a condição de vitória novamente. É um padrão clássico para evitar que threads executem lógica baseada em um contexto desatualizado.
