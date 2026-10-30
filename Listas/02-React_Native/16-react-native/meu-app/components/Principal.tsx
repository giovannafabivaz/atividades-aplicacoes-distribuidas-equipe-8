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

// Importação dos 5 exercícios práticos:
import Exercicio1CodigoOTP from './exercicios/Exercicio1CodigoOTP';
import Exercicio2AntiDoubleClick from './exercicios/Exercicio2AntiDoubleClick';
import Exercicio3Pomodoro from './exercicios/Exercicio3Pomodoro';
import Exercicio4Agenda from './exercicios/Exercicio4Agenda';
import Exercicio5ContadorOculto from './exercicios/Exercicio5ContadorOculto';

export default function Principal() {
  const [opcaoSelecionada, setOpcaoSelecionada] = useState<number>(0);

  const opcoes = [
    { numero: 7, titulo: 'Ex 1: OTP' },
    { numero: 8, titulo: 'Ex 2: Anti-Double' },
    { numero: 9, titulo: 'Ex 3: Pomodoro' },
    { numero: 10, titulo: 'Ex 4: Agenda' },
    { numero: 11, titulo: 'Ex 5: Contador Oculto' },
  ];

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
      case 7:
        return <Exercicio1CodigoOTP />;
      case 8:
        return <Exercicio2AntiDoubleClick />;
      case 9:
        return <Exercicio3Pomodoro />;
      case 10:
        return <Exercicio4Agenda />;
      case 11:
        return <Exercicio5ContadorOculto />;
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
    <View style={styles.container}>
      <Text style={styles.tituloApp}>Exemplos de Uso do Hook useRef</Text>

      <ScrollView
        horizontal
        showsHorizontalScrollIndicator={false}
        style={styles.menuHorizontal}
      >
        {opcoes.map(({ numero, titulo }) => (
          <TouchableOpacity
            key={numero}
            style={[
              styles.botaoMenu,
              opcaoSelecionada === numero && styles.botaoAtivo,
            ]}
            onPress={() => setOpcaoSelecionada(numero)}
          >
            <Text
              style={[
                styles.textoBotaoMenu,
                opcaoSelecionada === numero && styles.textoBotaoAtivo,
              ]}
            >
              {titulo}
            </Text>
          </TouchableOpacity>
        ))}

        {opcaoSelecionada !== 0 && (
          <TouchableOpacity
            style={styles.botaoReset}
            onPress={() => setOpcaoSelecionada(0)}
          >
            <Text style={styles.textoBotaoReset}>Limpar</Text>
          </TouchableOpacity>
        )}
      </ScrollView>

      <View style={styles.conteudoDinamico}>{renderizarExemplo()}</View>
    </View>
  );
}