import React, { useState, useRef, useEffect } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet } from 'react-native';
import { styles } from '../../styles/styles';

const TEMPO_INICIAL_SEGUNDOS = 1500; 

export default function Exercicio3Pomodoro() {
  const [segundos, setSegundos] = useState<number>(TEMPO_INICIAL_SEGUNDOS);
  const [tarefa, setTarefa] = useState<string>('');
  const timerRef = useRef<ReturnType<typeof setInterval> | null>(null);

  useEffect(() => {
    return () => {
      if (timerRef.current !== null) {
        clearInterval(timerRef.current);
      }
    };
  }, []);

  const iniciar = () => {
    if (timerRef.current !== null || segundos === 0) {
      return;
    }

    timerRef.current = setInterval(() => {
      setSegundos((prev) => {
        if (prev <= 1) {
          if (timerRef.current !== null) {
            clearInterval(timerRef.current);
            timerRef.current = null;
          }
          return 0;
        }
        return prev - 1;
      });
    }, 1000);
  };

  const pausar = () => {
    if (timerRef.current !== null) {
      clearInterval(timerRef.current);
      timerRef.current = null;
    }
  };

  const finalizar = () => {
    pausar();
    setSegundos(0);
  };

  const obterCorFundo = () => {
    const tempoDecorrido = TEMPO_INICIAL_SEGUNDOS - segundos;
    const blocosDeCincoMinutos = Math.floor(tempoDecorrido / 300);

    switch (blocosDeCincoMinutos) {
      case 0:
        return '#E8F5E9'; // 0 a 5 min: Verde claro
      case 1:
        return '#FFF3E0'; // 5 a 10 min: Laranja claro
      case 2:
        return '#E1F5FE'; // 10 a 15 min: Azul claro
      case 3:
        return '#F3E5F5'; // 15 a 20 min: Roxo claro
      default:
        return '#FFEBEE'; // 20 a 25 min: Vermelho claro
    }
  };

  const formatarTempo = (totalSegundos: number) => {
    const minutos = Math.floor(totalSegundos / 60);
    const seg = totalSegundos % 60;
    return `${String(minutos).padStart(2, '0')}:${String(seg).padStart(2, '0')}`;
  };

  return (
    <View style={[styles.caixa, { backgroundColor: obterCorFundo() }]}>
      <Text style={styles.subtitulo}>Exercício 3: Pomodoro (25 Minutos)</Text>

      <TextInput
        style={styles.input}
        placeholder="Informe o nome da tarefa"
        value={tarefa}
        onChangeText={setTarefa}
      />

      <Text style={[styles.texto, styles.textoMaior, localStyles.displayTempo]}>
        {formatarTempo(segundos)}
      </Text>

      <TouchableOpacity
        style={[styles.botaoAtivo, styles.configBotaoAtivo]}
        onPress={iniciar}
      >
        <Text style={styles.textoBotaoAtivo}>Iniciar</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={[styles.botaoAtivo, styles.configBotaoAtivo, styles.botaoParar]}
        onPress={pausar}
      >
        <Text style={styles.textoBotaoAtivo}>Pausar</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={[styles.botaoAtivo, styles.configBotaoAtivo, styles.botaoLimpar]}
        onPress={finalizar}
      >
        <Text style={styles.textoBotaoAtivo}>Finalizar</Text>
      </TouchableOpacity>
    </View>
  );
}

const localStyles = StyleSheet.create({
  displayTempo: {
    textAlign: 'center',
    fontSize: 34,
    fontWeight: 'bold',
    marginVertical: 12,
  },
});