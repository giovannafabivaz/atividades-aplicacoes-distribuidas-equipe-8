import { View, Text, Button, StyleSheet, TextInput, Alert } from 'react-native';
import { useState } from 'react';
import { useContador } from './context/ContadorContext';
import { useRouter } from 'expo-router';

export default function DefinirValor() {
  const { definirValor } = useContador();
  const router = useRouter();
  

  const [inputValor, setInputValor] = useState('');


  const handleSalvar = () => {
    const numero = parseInt(inputValor, 10);
    
    if (isNaN(numero) || numero < 0) {
      Alert.alert("Erro", "Por favor, insira um número válido e maior ou igual a zero.");
      return;
    }

    definirValor(numero);
    router.push("/");
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Definir Valor Específico</Text>
      
      <TextInput
        style={styles.input}
        keyboardType="numeric"
        value={inputValor}
        onChangeText={setInputValor}
        placeholder="Digite um número"
      />
      
      <Button title="Confirmar" onPress={handleSalvar} />
      <Button title="Voltar" onPress={() => router.push("/")} color="gray" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 20,
    marginTop: 50,
    gap: 20
  },
  title: {
    fontSize: 24,
    textAlign: 'center'
  },
  input: {
    borderWidth: 1,
    borderColor: '#999',
    padding: 15,
    fontSize: 20,
    borderRadius: 8,
    textAlign: 'center'
  }
});