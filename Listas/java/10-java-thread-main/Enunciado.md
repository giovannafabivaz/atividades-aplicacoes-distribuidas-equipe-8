# Enunciado Exercícios aula 10: Synchronized

## 1. Sistema de Vendas de Ingressos (Synchronized em Método)

Uma plataforma de eventos está vendendo ingressos para um show internacional de grande procura. Existe um número limitado de ingressos em estoque. Se o método de compra não for totalmente protegido, duas pessoas podem clicar no botão de compra ao mesmo tempo e o sistema venderá mais ingressos do que a capacidade do estádio suporta.

- Crie uma classe `Bilheteria` com um atributo privado `int ingressosDisponiveis = 10`.
- Implemente o método `public synchronized void comprarIngresso(String nomeCliente)`.
- Dentro do método, verifique se ainda há ingressos disponíveis (`> 0`).
- Se houver, diminua o estoque em 1 e exiba:
  - `"[Sucesso] " + nomeCliente + " comprou um ingresso. Restam: " + ingressosDisponiveis`
- Caso contrário, exiba uma mensagem informando que os ingressos estão esgotados.
- Na `Main`, crie 12 threads de clientes tentando comprar ingressos simultaneamente utilizando o mesmo objeto `Bilheteria`.

**Objetivo:** compreender a utilização do `synchronized` diretamente no método para proteger toda a rotina.

---

## 2. Atualização de Perfil de Usuário (Synchronized no bloco this)

Em uma rede social, um usuário decide atualizar suas informações de perfil a partir de dois dispositivos diferentes ao mesmo tempo. Para evitar que as escritas sejam misturadas, somente a parte responsável pela alteração dos dados deve ser protegida.

- Crie uma classe `PerfilUsuario` com os atributos `String biografia` e `String status`.
- Crie o método `public void atualizarPerfil(String novaBio, String novoStatus, String dispositivo)`.
- Fora do bloco sincronizado, simule uma validação utilizando uma mensagem e `Thread.sleep(200)`.
- Em seguida, utilize:

```java
synchronized(this) {
    ...
}