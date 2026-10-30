import { View, Text, Button, StyleSheet, TextInput, Alert, Platform } from 'react-native';
import { useState } from 'react';
import { useContador } from './context/ContadorContext';
import { useRouter } from 'expo-router';

export default function Operacoes() {
  const { valor, definirValor } = useContador();
  const router = useRouter();
  const [inputValor, setInputValor] = useState('');

  // Função para exibir erro compativel com Web e Celular
  const exibirErro = (mensagem: string) => {
    if (Platform.OS === 'web') {
      window.alert(mensagem);
    } else {
      Alert.alert("Erro", mensagem);
    }
  };

  const realizarOperacao = (operacao: '+' | '-' | '*' | '/') => {
    const numero = parseFloat(inputValor);

    if (isNaN(numero)) {
      exibirErro("Por favor, insira um número válido para a operação.");
      return;
    }

    let novoValor = valor;

    switch (operacao) {
      case '+': novoValor = valor + numero; break;
      case '-': novoValor = valor - numero; break;
      case '*': novoValor = valor * numero; break;
      case '/': 
        if (numero === 0) {
          exibirErro("Não é possível dividir por zero.");
          return;
        }
        novoValor = valor / numero; 
        break;
    }

    // Impede que a conta fique negativa
    if (novoValor < 0) {
      exibirErro("A operação resultou em um valor negativo. Ajustando para 0.");
      novoValor = 0;
    } else {
      // Arredonda para evitar números quebrados gigantes (ex: 2.3333333)
      novoValor = parseFloat(novoValor.toFixed(2));
    }

    definirValor(novoValor);
    setInputValor('');
    router.push("/");
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Operações Matemáticas</Text>
      <Text style={styles.subtitle}>Valor atual: {valor}</Text>
      
      <TextInput
        style={styles.input}
        keyboardType="numeric"
        value={inputValor}
        onChangeText={setInputValor}
        placeholder="Digite o número para a operação"
      />
      
      <View style={styles.botoesOperacoes}>
        <View style={styles.linhaBotoes}>
          <Button title="Somar (+)" onPress={() => realizarOperacao('+')} />
          <Button title="Subtrair (-)" onPress={() => realizarOperacao('-')} />
        </View>
        <View style={styles.linhaBotoes}>
          <Button title="Multiplicar (x)" onPress={() => realizarOperacao('*')} />
          <Button title="Dividir (÷)" onPress={() => realizarOperacao('/')} />
        </View>
      </View>

      <View style={{marginTop: 20}}>
        <Button title="Voltar" onPress={() => router.push("/")} color="gray" />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { padding: 20, marginTop: 50, gap: 15 },
  title: { fontSize: 24, textAlign: 'center' },
  subtitle: { fontSize: 18, textAlign: 'center', marginBottom: 10, color: '#333' },
  input: { borderWidth: 1, borderColor: '#999', padding: 15, fontSize: 18, borderRadius: 8, textAlign: 'center', marginBottom: 10 },
  botoesOperacoes: { gap: 15 },
  linhaBotoes: { flexDirection: 'row', justifyContent: 'space-evenly' }
});