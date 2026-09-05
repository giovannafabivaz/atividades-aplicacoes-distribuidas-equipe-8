# 05-java-thread

## Compilação:
```bash
javac *.java
```

## Execução:
```bash
java Main
```

# Exercícios:

Em Java, implemente a aplicação abaixo.
Em Markdown, explique sua solução.

## 1) Processador de Relatórios Multithread

Você precisa criar um sistema que gera relatórios financeiros para diferentes departamentos da empresa em paralelo. Como cada relatório funciona como um processo independente e isolado, você utilizará a herança direta da classe Thread.

### Requisitos de Implementação:

#### A subclasse de Thread:
Crie uma classe chamada ProcessadorRelatorio que estende (extends) Thread. Essa classe deve ter um construtor que recebe uma String nomeDepartamento. Dentro do construtor, use o método this.setName(nomeDepartamento) para batizar a thread com o nome do departamento. Sobrescreva o método run(). Dentro dele, faça um loop que simula o processamento de 3 páginas do relatório. A cada página, imprima na tela: 

`"[Nome_da_Thread] Gerando página X..."`.

Para simular o tempo de escrita, coloque um `Thread.sleep(500)` a cada iteração (lembre-se de tratar a InterruptedException com try-catch). No final do método run(), exiba: 

`"[Nome_da_Thread] Relatório finalizado!"`.

#### A classe Principal (Main):
Dentro do método main, instancie duas threads de ProcessadorRelatorio: 

1. A primeira para o departamento "Faturamento".

2. A segunda para o departamento "Vendas".

Inicie as duas threads em paralelo usando o método .start().

#### Comportamento Esperado no Console:

As mensagens de "Faturamento" e "Vendas" vão se intercalar na tela conforme as páginas são geradas, mostrando que os relatórios estão sendo criados ao mesmo tempo.
