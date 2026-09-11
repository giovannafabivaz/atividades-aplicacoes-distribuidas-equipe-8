import { useState } from 'react';
import { View, Text, TouchableOpacity, ScrollView } from 'react-native';

// Importação da folha de estilos externa compartilhada:
import { styles } from '../styles/styles';

// Importação dos componentes de exemplo:
import TesteContador from './exemplos/TesteContador';
import Formulario1 from './exemplos/Formulario1';
import Formulario2 from './exemplos/Formulario2';
import Formulario3 from './exemplos/Formulario3';
import Rolagem from './exemplos/Rolagem';
import Cronometro from './exemplos/Cronometro';

// Componente principal (Tela de entrada principal):
export default function Principal() {
  // Estado numérico que armazena qual exemplo está ativo na tela no momento (0 significa nenhum/home):
  const [opcaoSelecionada, setOpcaoSelecionada] = useState<number>(0);
  
  const opcoes = [
    { numero: 1, titulo: 'Contadores' },
    { numero: 2, titulo: 'Foco por botão' },
    { numero: 3, titulo: 'Foco automático' },
    { numero: 4, titulo: 'Formulário' },
    { numero: 5, titulo: 'Rolagem' },
    { numero: 6, titulo: 'Cronômetro' },
  ];

  // Função com switch-case que lê o estado atual e decide qual componente renderizar dinamicamente na tela:
  const renderizarExemplo = () => {
    switch (opcaoSelecionada) {
      case 1:
        return <TesteContador />;
      case 2:
        return <Formulario1 />;
      case 3:
        return <Formulario2 />;
      case 4:
        return <Formulario3 />;
      case 5:
        return <Rolagem />;
      case 6:
        return <Cronometro />;
      default:
        return (
          <View style={styles.boasVindas}>
            <Text style={styles.textoHome}>
              Selecione um dos exemplos acima para visualizar a aplicação prática do hook useRef.
            </Text>
          </View>
        );
    }
  };

  return (
    // Container base da tela:
    <View style={styles.container}>

      {/* Título de cabeçalho visível o tempo todo no topo da aplicação: */}
      <Text style={styles.tituloApp}>Exemplos de Uso do Hook useRef</Text>

      {/* Menu Horizontal deslizável contendo os botões de seleção de exemplos: */}
      <ScrollView horizontal showsHorizontalScrollIndicator={false} style={styles.menuHorizontal}>

        {/* Mapeia as opções para desenhar os botões do menu: */}
        {opcoes.map(({ numero, titulo }) => (
          <TouchableOpacity
            key={numero} // Atribui uma chave única exigida pelo React para cada item mapeado.
            style={[
              styles.botaoMenu, // Aplica o design básico do botão.
              opcaoSelecionada === numero && styles.botaoAtivo // Se este botão for o selecionado, injeta a cor azul de ativo.
            ]}
            // Ao clicar no botão, atualiza o estado para o número correspondente, disparando o switch-case:
            onPress={() => setOpcaoSelecionada(numero)}
          >

            {/* Texto exibido dentro do botão: */}
            <Text style={[styles.textoBotaoMenu, opcaoSelecionada === numero && styles.textoBotaoAtivo]}>
              {titulo}
            </Text>

          </TouchableOpacity>
        ))}

        {/* Renderização Condicional: se alguma opção estiver aberta (diferente de 0), exibe o botão vermelho de limpar (reset): */}
        {opcaoSelecionada !== 0 && (
          <TouchableOpacity style={styles.botaoReset} onPress={() => setOpcaoSelecionada(0)}>
            <Text style={styles.textoBotaoReset}>Limpar</Text>
          </TouchableOpacity>
        )}
      </ScrollView>

      {/* Área reservada onde o switch-case injetará o código do componente selecionado: */}
      <View style={styles.conteudoDinamico}>
        {renderizarExemplo()}
      </View>
    </View>
  );
}
