import React, { useRef, useState } from 'react';
import { View, Text, TouchableOpacity } from 'react-native';
import { styles } from '../../styles/styles';

export default function Exercicio2AntiDoubleClick() {
  const [mensagem, setMensagem] = useState<string>('');
  const clicadoRef = useRef<boolean>(false);

  const enviarPedido = () => {
    if (clicadoRef.current) {
      return;
    }

    clicadoRef.current = true;
    setMensagem('Pedido Enviado!');

    setTimeout(() => {
      clicadoRef.current = false;
      setMensagem('');
    }, 3000);
  };

  const estaBloqueado = mensagem !== '';

  return (
    <View style={styles.caixa}>
      <Text style={styles.subtitulo}>Exercício 2: Anti-Double-Click</Text>
      <Text style={styles.texto}>
        {mensagem || 'Clique no botão abaixo para processar seu pedido:'}
      </Text>

      <TouchableOpacity
        style={[
          styles.botaoAtivo,
          styles.configBotaoAtivo,
          estaBloqueado && styles.botaoDesativado,
        ]}
        onPress={enviarPedido}
      >
        <Text style={styles.textoBotaoAtivo}>
          {estaBloqueado ? 'Aguarde 3s...' : 'Enviar Pedido'}
        </Text>
      </TouchableOpacity>
    </View>
  );
}