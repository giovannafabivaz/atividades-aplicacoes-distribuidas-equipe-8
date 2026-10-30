import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { useRouter } from 'expo-router';

export default function Etapa1Screen() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.step}>Passo 1 de 3</Text>
      <Text style={styles.title}>Qual é o seu Nome?</Text>
      <TouchableOpacity 
        style={styles.button} 
        onPress={() => router.push('/cadastro/etapa2')}
      >
        <Text style={styles.buttonText}>Avançar para E-mail →</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 24, justifyContent: 'center', backgroundColor: '#FFF' },
  step: { color: '#2563EB', fontWeight: 'bold', fontSize: 14, marginBottom: 8 },
  title: { fontSize: 22, fontWeight: 'bold', marginBottom: 24 },
  button: { backgroundColor: '#2563EB', padding: 14, borderRadius: 8, alignItems: 'center' },
  buttonText: { color: '#FFF', fontWeight: 'bold', fontSize: 16 },
});
