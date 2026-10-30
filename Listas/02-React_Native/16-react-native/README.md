# 05-react-native

# Exercício

1. Validador de Código OTP (One-Time Password, em português, Senha de Uso Único): Foco em Cadeia.
   - Objetivo: fixar o uso de múltiplas referências para manipulação de elementos na tela.
   - Enunciado: crie uma tela de validação de token (como os enviados por SMS) com 6 campos de texto (TextInput), em que cada um aceita apenas 1 dígito. Quando o usuário digitar o número no primeiro campo, o cursor deve focar automaticamente no segundo. Ao preencher o segundo, deve focar automaticamente no terceiro. E, assim, sucessivamente.
   - Dicas:
     - Crie 6 useRef<TextInput>(null) diferentes (um para cada campo).
     - Use a propriedade maxLength={1} nos inputs.
     - No evento onChangeText, verifique se o texto não está vazio e use o .current?.focus() para avançar para o próximo campo.

---

2. Botão "Anti-Double-Click".
   - Objetivo: entender como o useRef retém valores para impedir ações indesejadas na interface.
   - Enunciado: crie um botão de "Enviar Pedido". Quando o usuário clicar no botão, o aplicativo deve exibir uma mensagem ('Pedido Enviado!'). No entanto, usuários ansiosos clicam no botão várias vezes seguidas, gerando pedidos duplicados. Use o useRef para guardar o estado de "clicado" e bloquear cliques adicionais por 3 segundos, sem criar novos estados de carregamento. Durante esse tempo, o botão deve ficar na cor cinza, indicando o bloqueio.
   - Dicas:
     - Crie uma referência const clicadoRef = useRef(false);.
     - No clique do botão, verifique se clicadoRef.current é true. Se for, dê um return e aborte a função.
     - Se for false, mude para true, exiba a mensagem e use um setTimeout para mudar de volta para false após 3000ms.

---

3. Cronômetro de Pomodoro Simples
   - Objetivo: praticar o uso do useRef para armazenar IDs de setInterval() sem perder a referência em re-renders.
   - Enunciado: desenvolva um contador regressivo simples de 25 minutos, para exibir o tempo de execução de uma tarefa. A tela deve exibir o tempo mudando segundo a segundo (use um useState para os segundos). Adicione 3 botões: "Iniciar", "Pausar" e "Finalizar". Se o usuário clicar em pausar, o tempo deve congelar exatamente onde parou. Se clicar em iniciar, deve continuar. Se clicar em finalizar, a contagem deve ser interrompida (o contador deve receber zero). Permita que o usuário informe o nome da tarefa. A cada 5 minutos a cor de fundo da tela deve ser alterada. Bloqueie múltiplos cliques nos botões. Exemplo: se o usuário clicar em "Iniciar" 5 vezes seguidas, ele criará 5 setInterval() atropelados na memória. Lembre-se que 25 minutos equivalem a 1500 segundos.
   - Dicas:
     - Use const timerRef = useRef<ReturnType<typeof setInterval> | null>(null); para guardar o ID do timer.
     - Lembre-se de limpar o timer com clearInterval(timerRef.current) ao pausar, finalizar e quanto o componente for desmontado.
     - Nos botões, verifique se .current já está preenchido ao ser clicado. Isso impede múltiplos cliques desnecessários.
     - Lembre-se de converter os 25 minutos para 1500 segundos no seu useState inicial.
     - A mudança de cor de fundo a cada 5 minutos equivale a cada 300 segundos (use o operador de resto da divisão % ou regras de três simples nos seus cálculos). Exemplo:

```js
// Regra de Negócio: mudar a cor de fundo a cada 5 minutos (300 segundos) de execução:

const TEMPO_INICIAL_SEGUNDOS = 1500; // 25 minutos = 1500 segundos.
...

const [segundos, setSegundos] = useState(TEMPO_INICIAL_SEGUNDOS);
...

const obterCorFundo = () => {
  // Tempo decorrido = Tempo total inicial (1500) - Tempo atual restante.
  const tempoDecorrido = TEMPO_INICIAL_SEGUNDOS - segundos;
  const blocosDeCincoMinutos = Math.floor(tempoDecorrido / 300);

  switch (blocosDeCincoMinutos) {
    case 0:
      return '#E8F5E9'; // 0 a 5 min: Verde claro.
    case 1:
      return '#FFF3E0'; // 5 a 10 min: Laranja claro.
    case 2:
      return '#E1F5FE'; // 10 a 15 min: Azul claro.
    case 3:
      return '#F3E5F5'; // 15 a 20 min: Roxo claro.
    default:
      return '#FFEBEE'; // 20 a 25 min: Vermelho claro.
  }
};

return (
  <View style={[styles.container, { backgroundColor: obterCorFundo() }]}>
    ...
  </View>
);
```

---

4. Agenda de Tarefas com Rolagem Automática (Auto-Scroll).
   - Objetivo: manipular métodos nativos de componentes de listagem: FlatList.
   - Enunciado: crie uma tela para uma agenda de cadastro de tarefas. Crie um campo para que o usuário informe a tarefa. Crie uma FlatList que renderiza um vetor de tarefas (strings). Adicione um botão fixo no rodapé chamado "Inserir". Toda vez que esse botão for clicado, a tarefa informada deve ser inserida na lista. A lista deve rolar automaticamente para a última tarefa inserida.
   - Dicas:
     - Conecte um useRef<FlatList>(null) na propriedade ref da sua lista.
     - Ao adicionar a mensagem, chame o método .current?.scrollToEnd({ animated: true }).
     - Envolva a rolagem em um setTimeout(..., 50) para dar tempo de o React desenhar o novo item antes da lista tentar rolar.

---

5. Contador de Renderizações Oculto.
   - Objetivo: evidenciar a diferença entre o comportamento do useState e do useRef.
   - Enunciado: crie uma tela com dois botões: "Incrementar Estado" e "Incrementar Ref". Exiba na tela o valor do estado. O valor da Ref não deve ser exibido na tela diretamente através de texto, mas sim com um console.log() no terminal que diz: Valor atual da Ref: X. Adicione também um console.log("A tela renderizou!") no corpo do componente. A cor de fundo padrão da tela do aplicativo deve ser branca. Ao clicar no botão "Incrementar Estado", a cor de fundo da tela deve mudar para verde. Ao clicar no botão "Incrementar Ref" a cor de fundo da tela deve mudar para azul. Clique algumas vezes no botão da Ref e analise o terminal. Depois, clique algumas vezes no botão do Estado e observe o que acontece.
   - OBS:
     - Este exercício serve para fixar o conceito de que alterar o .current é uma operação silenciosa na memória do celular.
