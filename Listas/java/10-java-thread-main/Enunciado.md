# Enunciado Exercícios aula 10: Synchronized

## 1. Sistema de Vendas de Ingressos (Synchronized em Método)

Uma plataforma de eventos está vendendo ingressos para um *show* internacional de grande procura. Existe um número limitado de ingressos em estoque. Se o método de compra não for totalmente protegido, duas pessoas podem clicar no botão de compra ao mesmo tempo e o sistema venderá mais ingressos do que a capacidade do estádio suporta.

- Crie uma classe `Bilheteria` com um atributo privado `int ingressosDisponiveis = 10`.
- Implemente o método `public synchronized void comprarIngresso(String nomeCliente)`.
- Dentro do método, o código deve verificar se ainda há ingressos disponíveis (`> 0`).
  - Se sim, decrementa o estoque em 1 e exibe: `"[Sucesso] " + nomeCliente + " comprou um ingresso. Restam: " + ingressosDisponiveis`.
  - Se não, exibe uma mensagem de esgotado.
- Na `Main`, dispare 12 threads de clientes simultaneamente tentando comprar ingressos no mesmo objeto `Bilheteria`.

**Objetivo:** compreender a simplicidade do `synchronized` no nível do método para blindar uma rotina inteira.

---

## 2. Atualização de Perfil de Usuário (Synchronized no bloco this)

Em uma rede social, um usuário decide atualizar suas informações de perfil (como biografia e *status*) a partir de dois dispositivos diferentes ao mesmo tempo. Para evitar misturar as escritas, o método usa um bloco sincronizado. No entanto, o método também faz uma operação demorada de validação de texto que não precisa de tranca.

- Crie uma classe `PerfilUsuario` com os atributos `String biografia` e `String status`.
- Crie o método `public void atualizarPerfil(String novaBio, String novoStatus, String dispositivo)`.
- Fora de qualquer bloco seguro, simule a validação do texto exibindo uma mensagem e colocando um `Thread.sleep(200)` (Validação local pesada).
- Logo em seguida, abra um bloco:

```java
synchronized(this) {
    ...
}
```

- Dentro dele, atualize as duas variáveis com os novos valores e exiba o resultado na tela usando `System.out.flush()`.
- Na `Main`, crie 2 threads (Dispositivo Móvel e Computador) tentando atualizar o mesmo perfil com textos diferentes.

**Objetivo:** perceber o ganho de desempenho ao usar o `synchronized(this)` para proteger apenas o trecho crítico, deixando o processamento pesado de validação rodar em paralelo.

---

## 3. Caixa de Supermercado (Synchronized com Objeto Privado Final)

Um caixa de supermercado registra o fluxo de dinheiro que entra no caixa (vendas) e o fluxo que sai (sangria/troco). Para garantir que o sistema seja robusto e seguro contra invasões ou modificações externas acidentais, a tranca do saldo do caixa não deve expor a instância do objeto (`this`), utilizando um objeto privado dedicado.

- Crie uma classe `CaixaRegistradora` com o atributo `double saldoCaixa = 100.0`.
- Declare a tranca interna recomendada:

```java
private final Object travaSaldo = new Object();
```

- Crie os métodos:
  - `public void registrarVenda(double valor, String operador)`
  - `public void realizarSangria(double valor, String operador)`
- Em ambos os métodos, envolva a alteração da variável `saldoCaixa` usando a sintaxe:

```java
synchronized(travaSaldo) {
    ...
}
```

- Exiba o saldo atualizado a cada movimentação.
- Na `Main`, crie 3 threads simulando operadores registrando vendas e retiradas ao mesmo tempo no mesmo caixa.

**Objetivo:** dominar o padrão de encapsulamento seguro da indústria com trancas dedicadas do tipo `Object final`.

---

## 4. Controlador de Tráfego de Downloads (Múltiplas Trancas Privadas com Object final)

Um navegador de internet possui um gerenciador que controla duas listas independentes em segundo plano: a lista de arquivos baixados com sucesso (`int downloadsConcluidos`) e a lista de downloads que falharam (`int downloadsFalhados`).

Se o programador usar um único cadeado para a classe inteira, o registro de uma falha travará temporariamente o painel de conclusões de outro download saudável.

- Crie uma classe `GerenciadorDownloads` com dois contadores independentes.
- Instancie duas trancas privadas distintas e finais:

```java
private final Object travaSucesso = new Object();
private final Object travaFalha = new Object();
```

- Crie o método `public void incrementarSucesso(String nomeArquivo)` que sincroniza estritamente na `travaSucesso`.
- Crie o método `public void incrementarFalha(String nomeArquivo)` que sincroniza estritamente na `travaFalha`.
- Na `Main`, crie a `Thread A` disparando múltiplos sucessos e a `Thread B` disparando múltiplas falhas.

**Objetivo:** aprender a trabalhar com concorrência fina (*fine-grained locking*), provando que threads acessando recursos diferentes no mesmo objeto não precisam esperar uma pela outra na fila se usarem trancas separadas.

---

## 5. Painel de Controle de Aeroporto (Múltiplas Trancas Privadas)

Você está desenvolvendo o sistema de monitoramento de um terminal de aeroporto. O sistema precisa gerenciar duas informações em tempo real: o número de passageiros que fizeram *check-in* (`int passageiros`) e a quantidade de bagagens despachadas (`int bagagens`).

Como o fluxo de passageiros e o fluxo de bagagens são processos totalmente independentes, usar `synchronized(this)` travaria o sistema inteiro sempre que uma única bagagem fosse registrada, impedindo que um passageiro fizesse *check-in* no mesmo instante.

Para resolver esse problema de desempenho, você deve implementar o padrão de **múltiplas trancas privadas dedicadas**.

### Requisitos de Implementação

- Na classe `PainelAeroporto`, crie dois atributos inteiros privados: `passageiros` e `bagagens`, ambos iniciando em 0.
- Crie dois objetos privados e finais para servirem como trancas dedicadas:

```java
private final Object travaPassageiros = new Object();
private final Object travaBagagens = new Object();
```

- Implemente o método `public void registrarCheckIn(String atendente)`.
  - Ele deve sincronizar apenas na `travaPassageiros`.
  - Incremente o contador de passageiros.
  - Exiba o estado na tela usando `System.out.flush()`.

- Implemente o método `public void registrarBagagem(String esteira)`.
  - Ele deve sincronizar apenas na `travaBagagens`.
  - Incremente o contador de bagagens.
  - Exiba o estado na tela usando `System.out.flush()`.

- Na classe Principal (`Main`), instancie um único objeto `PainelAeroporto`.
- Crie a `Thread A` (Atendimento), que simula um loop inserindo 3 passageiros chamando `registrarCheckIn`.
- Crie a `Thread B` (Logística), que simula um loop inserindo 3 bagagens chamando `registrarBagagem`.
- Dispare as duas threads simultaneamente.

### Console

Ao rodar o programa, as mensagens de registro de passageiros e de bagagens devem se misturar de forma limpa.

Você deve perceber que, enquanto uma thread está executando o bloco seguro de passageiros, a outra thread não fica bloqueada para registrar a bagagem, pois os cadeados digitais são completamente diferentes.

**Objetivo:** aprender a trabalhar com concorrência fina (*fine-grained locking*), provando que threads acessando recursos diferentes no mesmo objeto não precisam esperar uma pela outra na fila se usarem trancas separadas.
