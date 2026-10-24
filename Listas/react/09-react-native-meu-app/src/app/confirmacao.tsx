import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { useRouter } from 'expo-router';

export default function ConfirmacaoScreen() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.icon}>🎉</Text>
      <Text style={styles.title}>Pedido Confirmado!</Text>
      <Text style={styles.description}>
        Como usamos router.replace no carrinho, o carrinho foi removido da pilha.
      </Text>

      <TouchableOpacity 
        style={styles.homeButton}
        onPress={() => router.replace('/')}
      >
        <Text style={styles.homeButtonText}>Voltar ao Início</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: '#FFF', justifyContent: 'center', alignItems: 'center', padding: 24 },
  icon: { fontSize: 64, marginBottom: 16 },
  title: { fontSize: 26, fontWeight: 'bold', color: '#111827', marginBottom: 12 },
  description: { fontSize: 15, color: '#6B7280', textAlign: 'center', marginBottom: 32 },
  homeButton: { backgroundColor: '#2563EB', paddingVertical: 14, paddingHorizontal: 28, borderRadius: 10 },
  homeButtonText: { color: '#FFF', fontWeight: 'bold', fontSize: 16 },
});
