import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { useLocalSearchParams, useRouter, Stack } from 'expo-router';

export default function DetalhesScreen() {
  const router = useRouter();
  const params = useLocalSearchParams<{ id: string; nome: string; preco: string; descricao: string }>();

  return (
    <View style={styles.container}>
      <Stack.Screen options={{ title: params.nome || 'Detalhes do Produto' }} />

      <View style={styles.card}>
        <Text style={styles.label}>Produto #{params.id}</Text>
        <Text style={styles.title}>{params.nome}</Text>
        <Text style={styles.price}>{params.preco}</Text>
        <Text style={styles.description}>{params.descricao}</Text>

        <TouchableOpacity 
          style={styles.cartButton}
          onPress={() => router.push({
            pathname: '/carrinho',
            params: { nome: params.nome, preco: params.preco },
          })}
        >
          <Text style={styles.cartButtonText}>Adicionar e Abrir Carrinho</Text>
        </TouchableOpacity>

        <TouchableOpacity 
          style={styles.backButton}
          onPress={() => router.back()}
        >
          <Text style={styles.backButtonText}>← Voltar à Lista</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: '#F9FAFB', padding: 20, justifyContent: 'center' },
  card: { backgroundColor: '#FFF', padding: 24, borderRadius: 16, elevation: 3 },
  label: { fontSize: 12, color: '#9CA3AF', textTransform: 'uppercase', marginBottom: 4 },
  title: { fontSize: 24, fontWeight: 'bold', color: '#111827' },
  price: { fontSize: 22, color: '#2563EB', fontWeight: 'bold', marginVertical: 8 },
  description: { fontSize: 15, color: '#4B5563', lineHeight: 22, marginBottom: 24 },
  cartButton: { backgroundColor: '#2563EB', padding: 14, borderRadius: 10, alignItems: 'center' },
  cartButtonText: { color: '#FFF', fontWeight: 'bold', fontSize: 16 },
  backButton: { marginTop: 12, padding: 12, alignItems: 'center' },
  backButtonText: { color: '#4B5563', fontSize: 15 },
});
