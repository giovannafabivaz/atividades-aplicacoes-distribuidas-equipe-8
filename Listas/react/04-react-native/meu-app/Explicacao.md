# Explicação do Projeto: Aplicativo Contador Avançado com Context API e Expo Router

## 1. Estruturação Base e Navegação Global

Nesta etapa, o projeto estabeleceu a arquitetura fundamental do aplicativo, combinando navegação moderna baseada em rotas com centralização de estado.

A organização técnica compreendeu:
* Utilização do **Expo Router**, organizando o fluxo de navegação baseado na hierarquia de arquivos e diretórios;
* Criação de um contexto centralizado via **Context API** (`createContext` e `useContext`), permitindo que as variáveis e métodos de manipulação do contador trafeguem livremente por toda a árvore de componentes;
* Compartilhamento de um estado global persistente durante a transição entre telas, eliminando a dependência de repasse manual de propriedades (*prop drilling*).

Essa estrutura desacopla a regra de negócio da interface, garantindo que qualquer rota do aplicativo possa consultar ou despachar alterações no contador com sincronização em tempo real.

---

## 2. Operações Aritméticas e Validações de Entrada

Nesta fase, o foco foi a proteção das regras de negócio aritméticas básicas e a sanitização de dados inseridos pelos usuários.

Os pontos centrais da implementação foram:
* **Trava de Não Negatividade:** No decremento do contador, adicionou-se uma validação prévia que impede valores menores que zero ($contador \ge 0$), blindando o estado contra estados inválidos;
* **Definição Direta de Valor:** Implementação de uma tela com campo de digitação (`TextInput`) para sobreposição direta do contador;
* **Sanitização de Dados:** Aplicação de filtros para rejeitar entradas inválidas, como caracteres não numéricos (*NaN - Not-a-Number*), espaços em branco ou números negativos, antes de converter e persistir o valor no estado global.

O tratamento prévio dessas entradas evita inconsistências lógicas e falhas em tempo de execução (*runtime errors*).

---

## 3. Lógica Avançada, Proteções Matemáticas e Histórico

Nesta etapa, foi desenvolvida uma central de cálculos matemáticos acoplada a um sistema de auditoria visual de transições.

Os principais aspectos técnicos implementados foram:
* **Operações Combinadas:** Disponibilização de cálculos matemáticos (soma, subtração, multiplicação e divisão) atuando diretamente sobre o montante atual do contador;
* **Proteções Matemáticas:** Implementação de barreiras contra indeterminações matemáticas, bloqueando especificamente tentativas de divisão por zero ($x / 0$) e aplicando arredondamento para números inteiros ou com casas decimais controladas;
* **Rastreamento de Histórico:** Armazenamento automático das mutações de estado em um vetor (`array`) no contexto global, mantendo ordenados os últimos 10 valores calculados;
* **Listagem Eficiente:** Renderização otimizada desse histórico por meio do componente `FlatList`, complementado por uma ação dedicada para resetar e limpar o log de atividades.

---

## 4. Refinamento, Confirmação de Ação e Suporte Multiplataforma

Nesta fase final, foram integrados mecanismos de segurança operacional para ações destrutivas, mantendo a compatibilidade transparente entre plataformas móveis e Web.

A implementação abrangeu:
* **Ação de Reset Seguro:** Inclusão de um botão para zerar o contador, exigindo confirmação explícita do usuário para prevenir toques acidentais;
* **Comportamento Condicional Multiplataforma (`Platform.OS`):** 
  * Em ambiente **Web**, a confirmação é acionada por meio da API nativa do navegador (`window.confirm`);
  * Em dispositivos móveis (**Android/iOS**), a confirmação dispara o modal nativo do sistema operacional através do módulo `Alert.alert`.

A validação de plataforma preserva a consistência e a usabilidade esperada em cada ambiente sem quebrar o ciclo de vida da aplicação.

---

## Conclusão

O desenvolvimento deste aplicativo permitiu exercitar padrões arquiteturais indispensáveis no ecossistema React Native. 

Além de consolidar o controle de rotas dinâmicas com o Expo Router e a gerência de estado unificado com a Context API, o projeto enfatizou a importância da escrita de código defensivo — desde a higienização de formulários e tratamento de exceções numéricas até o tratamento de APIs com comportamentos divergentes entre plataformas móveis e Web.