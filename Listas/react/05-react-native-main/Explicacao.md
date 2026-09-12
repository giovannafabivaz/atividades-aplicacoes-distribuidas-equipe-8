# Explicação dos Exercícios: Fundamentos do React Native com o Hook `useRef`

## 1. Validador de Código OTP (Foco em Cadeia)

Neste exercício, o componente foi estruturado para receber um código de uso único (OTP) composto por 6 dígitos numéricos, distribuídos em caixas de entrada separadas[cite: 2].

O comportamento dos campos baseia-se em:
* Cada `TextInput` possui a propriedade `maxLength={1}`, aceitando apenas um único dígito[cite: 2];
* Foram criadas 6 referências independentes utilizando `useRef<TextInput>(null)`, associadas a cada campo[cite: 2];
* No manipulador de texto (`onChangeText`), verifica-se se o caractere foi preenchido e, caso não seja o último índice, dispara-se o método nativo `.current?.focus()` para passar o foco diretamente ao próximo campo em cadeia[cite: 2].

Esse mecanismo melhora a usabilidade móvel, guiando o cursor do usuário automaticamente sem a necessidade de toques manuais em cada entrada[cite: 2].

---

## 2. Botão "Anti-Double-Click"

Neste exercício, o componente bloqueia múltiplos cliques em lote ao submeter um pedido, evitando a duplicação indevida de solicitações no aplicativo[cite: 2].

A implementação apoia-se em:
* Uma referência booleana `clicadoRef = useRef(false)` que opera como uma trava síncrona de memória[cite: 2];
* Ao clicar no botão, verifica-se imediatamente o valor de `clicadoRef.current`[cite: 2]. Caso o valor seja `true`, a função é abortada na mesma hora com um `return`[cite: 2];
* Quando a solicitação é válida, `clicadoRef.current` torna-se `true`, a mensagem de confirmação é ativada e um temporizador via `setTimeout` reverte o valor da referência para `false` após 3000 ms (3 segundos)[cite: 2].

Durante o período de bloqueio, o botão adota um estilo visual desativado (cinza)[cite: 2], demonstrando como o `useRef` retém valores mutáveis sem disparar novos ciclos desnecessários de re-renderização na interface[cite: 2].

---

## 3. Cronômetro de Pomodoro Simples

Neste exercício, foi desenvolvido um contador regressivo de 25 minutos (1500 segundos) para gerenciar o tempo de dedicação a uma tarefa informada pelo usuário[cite: 2].

Os principais aspectos técnicos compreendem:
* O identificador do intervalo é armazenado em `timerRef = useRef<ReturnType<typeof setInterval> | null>(null)`, garantindo que o ID do timer persista entre as renderizações da tela sem se perder[cite: 2];
* O botão "Iniciar" valida se `timerRef.current !== null` antes de disparar o `setInterval`, prevenindo múltiplos timers executando em segundo plano por cliques repetidos[cite: 2];
* Os botões "Pausar" e "Finalizar" interrompem o ciclo por meio de `clearInterval(timerRef.current)` e restauram o ponteiro da referência para `null`[cite: 2];
* A cada 5 minutos decorridos (blocos de 300 segundos calculados pela divisão inteira), a cor de fundo do container é atualizada de forma dinâmica (transitando entre verde, laranja, azul, roxo e vermelho claro)[cite: 2].

---

## 4. Agenda de Tarefas com Rolagem Automática (Auto-Scroll)

Neste exercício, a aplicação gerencia um quadro de cadastro de tarefas em formato de lista interativa com rolagem automática[cite: 2].

A integração com o componente de lista segue a estrutura:
* A lista de tarefas foi renderizada com o componente `FlatList`, mantendo um vetor de strings no estado[cite: 2];
* Foi associada uma referência `flatListRef = useRef<FlatList>(null)` diretamente à propriedade `ref` da lista[cite: 2];
* Ao acionar o botão "Inserir", o novo item é adicionado ao estado e o método imperativo `.current?.scrollToEnd({ animated: true })` é chamado[cite: 2];
* A rolagem é executada dentro de um `setTimeout(..., 50)`, garantindo uma folga de 50 milissegundos para que o React Native compute o layout e desenhe o novo item antes do comando de rolagem ser executado[cite: 2].

---

## 5. Contador de Renderizações Oculto

Neste exercício, foi estruturado um ambiente comparativo para evidenciar visualmente e no console as diferenças comportamentais entre o `useState` e o `useRef`[cite: 2].

O funcionamento baseia-se nos seguintes pontos:
* No corpo principal do componente foi inserido um `console.log("A tela renderizou!")`, disparado a cada atualização e renderização do componente[cite: 2];
* Ao clicar no botão **Incrementar Estado**, o valor do estado numérico é somado, a cor de fundo é alterada para verde, o componente é redesenhado na tela e o log de renderização é impresso[cite: 2];
* Ao clicar no botão **Incrementar Ref**, o valor de `contadorRef.current` é incrementado diretamente na memória e impresso via `console.log("Valor atual da Ref: X")`, alterando o fundo para azul[cite: 2].

A alteração direta de uma referência mutável via `.current` ocorre de forma silenciosa na memória do dispositivo sem forçar o ciclo de redesenho do React[cite: 2], consolidando a finalidade do `useRef` para valores mutáveis independentes da renderização de tela[cite: 2].

---

## Conclusão

Os exercícios permitiram fixar o funcionamento do hook `useRef` no React Native e suas principais aplicações práticas[cite: 2]. 

Foi possível compreender tanto a manipulação direta e imperativa de elementos nativos da interface (foco em campos `TextInput` e controle de rolagem na `FlatList`)[cite: 2] quanto o armazenamento persistente de dados mutáveis sem causar re-renderizações (travas síncronas contra duplo clique, gerenciamento de identificadores de timers assíncronos e controle de valores em memória)[cite: 2].