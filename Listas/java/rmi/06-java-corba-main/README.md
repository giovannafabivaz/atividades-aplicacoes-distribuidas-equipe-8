# 06-java-corba


---

```bash
git add . && git commit -m "Exemplo" && git push
```

---

## Instalação no GitHub Codespaces

O `idlj` foi removido a partir do JDK 11. Neste Codespace, instale um JDK 8
com o SDKMAN e ative-o no terminal antes de compilar o exemplo:

```bash
sdk install java 8.0.504-amzn
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"
export PATH="$JAVA_HOME/bin:$PATH"
hash -r
```

- O primeiro comando precisa ser executado apenas uma vez. 
- O segundo deve ser substituido pelo bloco abaixo em cada novo terminal que for usado para este
projeto. 
- O `PATH` do Codespace pode colocar o Java 25 antes do SDKMAN, por isso o `JAVA_HOME` e o `PATH` são configurados explicitamente:

```bash
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"
export PATH="$JAVA_HOME/bin:$PATH"
hash -r
java -version
javac -version
idlj -version
```

---

## Compilação da interface IDL:

```bash
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"; export PATH="$JAVA_HOME/bin:$PATH"; hash -r
idlj -fclient -fserver -oldImplBase Calculadora.idl
```

---

## Compilação do Cliente e do Servidor:

```bash
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"; export PATH="$JAVA_HOME/bin:$PATH"; hash -r
javac *.java CalculadoraApp/*.java
```

---

## Execução:

---

### Servidor de Nomes (máquina localhost):

```bash
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"; export PATH="$JAVA_HOME/bin:$PATH"; hash -r
tnameserv -ORBInitialPort 1050
```

---

### Servidor de Aplicação
```bash
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"; export PATH="$JAVA_HOME/bin:$PATH"; hash -r
java Servidor -ORBInitialHost localhost -ORBInitialPort 1050
```

---

### Cliente
```bash
export JAVA_HOME="$(sdk home java 8.0.504-amzn)"; export PATH="$JAVA_HOME/bin:$PATH"; hash -r
java Cliente -ORBInitialHost localhost -ORBInitialPort 1050
```

O cliente solicita dois números, chama os métodos `somar` e `subtrair` do
servidor e exibe os dois resultados.

---

**OBS**:
`hash -r` é um comando interno do `Bash` que limpa o cache de localização dos executáveis. Ele não instala nem altera, apenas atualiza o cache de comandos do shell.

---

## Exercício

1. Altere o exemplo desta prática, acrescentando as operações de multiplicação e divisão.

2. Crie uma aplicação cliente-servior em CORBA usando Java que permita consultar a lista de produtos de uma empresa. A lista deve conter a quantidade do produto em estoque e respectivo preço. A aplicação também deve permitir alterar a quantidade de produto em estoque e o preço.