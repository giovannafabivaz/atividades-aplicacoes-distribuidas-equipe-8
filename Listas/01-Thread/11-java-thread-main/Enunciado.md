# 11-java-thread

## Como compilar e executar

É necessário ter o JDK instalado. Confira se os comandos `java` e `javac` estão disponíveis:

```bash
java -version
javac -version
```

No terminal, a partir da raiz do repositório, compile e execute:

```bash
cd Listas/01-Thread/11-java-thread-main
javac *.java
java Coletor
```

Ao iniciar, informe uma URL por linha. Pressione Enter em uma linha vazia para encerrar a entrada. Depois que as threads terminarem a coleta, escolha uma das opções de ordenação exibidas no terminal. É necessária uma conexão com a internet para acessar as páginas informadas.

---

## Exercício: Coletor concorrente de palavras em páginas web

Implemente um programa Java que receba URLs, distribua a coleta entre cinco threads e conte as palavras encontradas no conteúdo das páginas.

### Requisitos

1. Leia URLs do terminal até que seja informada uma linha vazia.
2. Distribua as URLs entre cinco threads, em rodízio.
3. Para cada página, obtenha o conteúdo HTML, remova tags, scripts, estilos e comentários, e conte as palavras do texto.
4. Aguarde a conclusão das threads e consolide as frequências de todas as páginas.
5. Exiba um menu para ordenar as palavras em ordem alfabética crescente ou decrescente, ou por frequência crescente ou decrescente.
6. Informe falhas ao acessar URLs sem interromper a coleta das demais páginas.
