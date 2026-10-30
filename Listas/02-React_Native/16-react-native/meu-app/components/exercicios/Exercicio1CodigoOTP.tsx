import React, { useRef, useState } from 'react';
import { View, Text, TextInput, StyleSheet } from 'react-native';
import { styles as globalStyles } from '../../styles/styles';

export default function Exercicio1CodigoOTP() {
  const [otp, setOtp] = useState<string[]>(['', '', '', '', '', '']);

  const input0Ref = useRef<TextInput>(null);
  const input1Ref = useRef<TextInput>(null);
  const input2Ref = useRef<TextInput>(null);
  const input3Ref = useRef<TextInput>(null);
  const input4Ref = useRef<TextInput>(null);
  const input5Ref = useRef<TextInput>(null);

  const inputsRefs = [input0Ref, input1Ref, input2Ref, input3Ref, input4Ref, input5Ref];

  const lidarComMudanca = (texto: string, indice: number) => {
    
    const novoOtp = [...otp];
    novoOtp[indice] = texto;
    setOtp(novoOtp);

    if (texto.length > 0 && indice < 5) {
      inputsRefs[indice + 1].current?.focus();
    }
  };

  return (
    <View style={globalStyles.caixa}>
      <Text style={globalStyles.subtitulo}>Exercício 1: Validador OTP</Text>
      <Text style={globalStyles.texto}>
        Digite os 6 dígitos. O cursor avança automaticamente:
      </Text>

      <View style={localStyles.linhaOtp}>
        {inputsRefs.map((ref, indice) => (
          <TextInput
            key={indice}
            ref={ref}
            style={[globalStyles.input, localStyles.inputDigito]}
            keyboardType="numeric"
            maxLength={1}
            value={otp[indice]}
            onChangeText={(texto) => lidarComMudanca(texto, indice)}
            autoFocus={indice === 0}
          />
        ))}
      </View>
    </View>
  );
}

const localStyles = StyleSheet.create({
  linhaOtp: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginVertical: 12,
  },
  inputDigito: {
    width: 44,
    height: 50,
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 0,
  },
});