# 01-java-rmi

---

# Ordem dos Comandos:

---

## Compilação:

```bash
javac *.java
```

---

## Iniciar o RMI Registry (somente se ainda não estiver ativo):

```bash
rmiregistry 1099
```

---

## Iniciar o Servidor (em outro terminal):
Se `rmiregistry 1099` retornar `Port already in use: 1099`, o registry já está ativo. Não inicie outro; continue com o servidor e o cliente.

## Iniciar o Servidor (em outro terminal):

```bash
java Servidor
```

---

## Iniciar o Cliente (em um terceiro terminal):

```bash
java Cliente 
```

---

## Encerrar o registry

Para encerrar o registry iniciado no terminal, pressione `Ctrl+C` nesse terminal.
---

# Exercício

1. Usando Java RMI, implementar um serviço remoto que retorne um número aleatório no intervalo de 0 a 100.
