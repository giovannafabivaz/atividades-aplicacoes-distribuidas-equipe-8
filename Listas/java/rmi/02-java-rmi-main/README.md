# 01-java-rmi

---

# Ordem dos Comandos:

```bash
cd 01-cumprimento/
```
---

## Compilação:

```bash
javac servico/*.java
```

```bash
javac servidor/*.java
```

```bash
javac cliente/*.java
```

---

## Iniciar o RMI Registry:

```bash
rmiregistry 1099 &
```

---

## Iniciar o Servidor:

```bash
java -cp . servidor.Servidor &
```

---

## Iniciar o Cliente:

```bash
java -cp . cliente.Cliente 
```

---

## OBS: Matar Processo em Execução:

```bash
ps -aux | grep rmiregistry
```

```text
@wdiasmaciel ➜ /workspaces/01-java-rmi/01-cumprimento (main) $ ps -aux | grep rmiregistry
codespa+   29495  0.0  0.7 4669876 57384 pts/0   Sl   20:13   0:00 rmiregistry 1099
codespa+   34209  0.0  0.0   7084  2152 pts/1    S+   20:20   0:00 grep --color=auto rmiregistry
```

```bash
kill 29495
```

```bash
kill 34209
```
# Exercício

1. Usando Java RMI, implementar um serviço remoto que retorne um número aleatório no intervalo de 50 a 150.
