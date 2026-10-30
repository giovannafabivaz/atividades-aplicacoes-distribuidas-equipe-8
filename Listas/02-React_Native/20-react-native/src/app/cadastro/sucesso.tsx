import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { useRouter } from 'expo-router';

export default function SucessoScreen() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.icon}>✅</Text>
      <Text style={styles.title}>Cadastro Concluído!</Text>
      <Text style={styles.description}>
        Todas as etapas foram salvas com sucesso.
      </Text>

      <TouchableOpacity 
        style={styles.button}
        onPress={() => router.dismissAll()}
      >
        <Text style={styles.buttonText}>Concluir e Voltar ao Início</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 24, justifyContent: 'center', alignItems: 'center', backgroundColor: '#FFF' },
  icon: { fontSize: 56, marginBottom: 16 },
  title: { fontSize: 24, fontWeight: 'bold', marginBottom: 8 },
  description: { fontSize: 15, color: '#6B7280', textAlign: 'center', marginBottom: 28 },
  button: { backgroundColor: '#10B981', paddingVertical: 14, paddingHorizontal: 24, borderRadius: 8 },
  buttonText: { color: '#FFF', fontWeight: 'bold', fontSize: 16 },
});
