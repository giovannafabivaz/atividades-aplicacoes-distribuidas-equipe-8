# Explicação Exercícios aula 10: Synchronized e Concorrência em Java

Este documento resume o raciocínio por trás da construção dos 5 exercícios sobre a utilização de `synchronized`. A ideia é entender não apenas **o que** o código faz, mas **por que** cada tipo de sincronização foi escolhido.

---

## 1. Sistema de Vendas de Ingressos (`synchronized` no método)

**O que foi usado:** `public synchronized void comprarIngresso(...)`.

**Por que essa estrutura:** O estoque de ingressos é um recurso compartilhado entre várias threads. Ao sincronizar o método inteiro, garantimos que somente um cliente por vez consiga verificar e diminuir a quantidade disponível.

Isso evita condições de corrida e impede que o sistema venda mais ingressos do que realmente existem.

---

## 2. Atualização de Perfil de Usuário (`synchronized(this)`)

**O que foi usado:** Bloco `synchronized(this)` apenas na atualização dos dados.

**Por que essa estrutura:** A validação do texto com `Thread.sleep(200)` não modifica nenhuma informação compartilhada, então ela pode ser executada simultaneamente pelas duas threads.

Somente a alteração da `biografia` e do `status` precisa ser protegida. Dessa forma, diminuímos o tamanho da região crítica e melhoramos a concorrência do programa.

---

## 3. Caixa de Supermercado (Objeto Privado de Tranca)

**O que foi usado:** `private final Object travaSaldo = new Object();` junto com `synchronized(travaSaldo)`.

**Por que essa estrutura:** As operações de venda e sangria alteram o mesmo `saldoCaixa`, portanto precisam utilizar a mesma trava.

A utilização de um objeto privado evita que códigos externos tenham acesso ao mecanismo de sincronização da classe, deixando o controle do saldo mais seguro e encapsulado.

---

## 4. Controlador de Downloads (Múltiplas Trancas Privadas)

**O que foi usado:** Duas trancas diferentes: `travaSucesso` e `travaFalha`.

**Por que essa estrutura:** Os contadores de downloads concluídos e downloads falhados são independentes. Por isso, não existe necessidade de bloquear os dois recursos utilizando uma única trava.

Com duas trancas diferentes, uma thread pode registrar um sucesso enquanto outra registra uma falha ao mesmo tempo. Essa técnica é conhecida como `fine-grained locking`.

---

## 5. Painel de Controle de Aeroporto (Múltiplas Trancas)

**O que foi usado:** Uma trava para passageiros e outra para bagagens.

**Por que essa estrutura:** O processo de check-in de passageiros é independente do registro de bagagens. Utilizar `synchronized(this)` bloquearia toda a classe sem necessidade.

Com `travaPassageiros` e `travaBagagens`, as duas threads conseguem trabalhar simultaneamente em recursos diferentes, mantendo os dados protegidos sem reduzir desnecessariamente a concorrência.