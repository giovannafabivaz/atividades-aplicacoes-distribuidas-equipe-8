# 03-java-rmi

## Ordem correta para executar:

### Compilação
```bash
javac *.java
```

### Registrar o serviço RMI
```bash
rmiregistry &
```

### Iniciar o servidor
```bash
java Servidor
```

### Executar o cliente
Em outro terminal:
```bash
java Cliente
```

## Exercício

1. Usando Java RMI, implemente uma calculadora remota.