import React, { useState, useRef } from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { styles } from '../../styles/styles';

export default function Exercicio5ContadorOculto() {
  const [contadorEstado, setContadorEstado] = useState<number>(0);
  const [corFundo, setCorFundo] = useState<string>('#ffffff'); 
  const contadorRef = useRef<number>(0);

  console.log('A tela renderizou!');

  const incrementarEstado = () => {
    setContadorEstado((prev) => prev + 1);
    setCorFundo('#c8e6c9'); 
  };

  const incrementarRef = () => {
    contadorRef.current += 1;
    console.log(`Valor atual da Ref: ${contadorRef.current}`);
    setCorFundo('#bbdefb'); 
  };

  return (
    <View style={[styles.caixa, { backgroundColor: corFundo }]}>
      <Text style={styles.subtitulo}>Exercício 5: Contador Oculto</Text>

      <Text style={[styles.texto, styles.textoMaior]}>
        Valor do Estado: {contadorEstado}
      </Text>

      <Text style={styles.texto}>
        O valor da Ref é atualizado silenciosamente e exibido apenas no terminal.
      </Text>

      <TouchableOpacity
        style={[styles.botaoAtivo, styles.configBotaoAtivo, localStyles.botaoVerde]}
        onPress={incrementarEstado}
      >
        <Text style={styles.textoBotaoAtivo}>Incrementar Estado</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={[styles.botaoAtivo, styles.configBotaoAtivo, localStyles.botaoAzul]}
        onPress={incrementarRef}
      >
        <Text style={styles.textoBotaoAtivo}>Incrementar Ref</Text>
      </TouchableOpacity>
    </View>
  );
}

const localStyles = StyleSheet.create({
  botaoVerde: {
    backgroundColor: '#2e7d32',
  },
  botaoAzul: {
    backgroundColor: '#1565c0',
  },
});