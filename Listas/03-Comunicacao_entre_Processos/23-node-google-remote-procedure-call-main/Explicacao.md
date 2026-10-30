# Explicação dos Exercícios

## 1. Calculadora Remota (gRPC)

Neste exercício, o serviço de calculadora foi estendido para suportar novas operações matemáticas através de Chamadas de Procedimento Remoto (RPC) utilizando Node.js e Protocol Buffers (`proto3`).

O contrato (`calculadora.proto`) define os tipos de dados e os métodos disponíveis:
- **`Somar`**: recebe dois números inteiros (`int32`) e retorna a soma.
- **`Subtrair`**: recebe dois números inteiros (`int32`) e retorna a subtração.
- **`Multiplicar`**: recebe dois números inteiros (`int32`) e retorna o produto da multiplicação.

O **Servidor** implementa as funções que realizam os cálculos correspondentes e fica em execução escutando na porta `50051`. 

O **Cliente** lê dois valores inteiros informados pelo usuário via terminal, envia a requisição para o servidor e exibe os três resultados calculados remotamente no console.

---

## 2. Verificador de Palíndromo (gRPC)

Neste exercício, foi desenvolvido um serviço chamado `VerificadorTexto` com o propósito de validar se uma palavra ou frase é um palíndromo (lida da mesma forma de frente para trás e de trás para frente).

- **Contrato (`palindromo.proto`):** define a mensagem de requisição contendo um campo de texto (`string`) e uma mensagem de resposta contendo um valor booleano (`bool ehPalindromo`).
- **Servidor:** escuta na porta `50052` e implementa a limpeza da string — removendo acentos via normalização Unicode, pontuações, espaços em branco e convertendo tudo para minúsculas. Em seguida, compara o texto tratado com sua versão invertida e devolve o booleano via callback.
- **Cliente:** lê uma expressão ou palavra informada pelo usuário no teclado, dispara a chamada remota e exibe uma mensagem amigável no console indicando com clareza se a entrada é ou não um palíndromo.

---

## 3. Sistema de Autenticação / Login (gRPC)

Neste exercício, foi implementado um simulador de autenticação de credenciais via RPC entre processos distintos.

- **Contrato (`login.proto`):** define a estrutura `DadosLogin` com os campos de texto `usuario` e `senha`, além da resposta `RespostaLogin` contendo um booleano (`bool sucesso`) e um texto de retorno (`string mensagem`).
- **Servidor:** escuta na porta `50053` e mantém credenciais de referência pré-definidas (`admin` / `senha123`). Ao receber uma requisição, compara os dados enviados: se corretos, responde com `sucesso = true` e confirma o acesso; caso contrário, devolve `false` com uma mensagem de erro.
- **Cliente:** apresenta uma tela de login no terminal, captura os dados digitados pelo usuário, transmite as credenciais para validação remota no servidor e exibe o feedback de autorização ou bloqueio no console.

---

## Conclusão

Os exercícios permitiram compreender na prática o funcionamento da comunicação entre processos (IPC) utilizando **gRPC** e **Protocol Buffers** no ambiente Node.js. 

Foi possível observar como o arquivo `.proto` atua como um contrato estrito de interface e como o cliente pode disparar procedimentos que são executados em servidores remotos de forma desacoplada e independente.