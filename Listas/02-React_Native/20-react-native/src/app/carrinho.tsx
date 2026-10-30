import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { useRouter, useLocalSearchParams } from 'expo-router';

export default function CarrinhoScreen() {
  const router = useRouter();
  const { nome, preco } = useLocalSearchParams<{ nome: string; preco: string }>();

  return (
    <View style={styles.container}>
      <View style={styles.summaryCard}>
        <Text style={styles.header}>Resumo do Pedido</Text>
        <View style={styles.itemRow}>
          <Text style={styles.itemText}>{nome || 'Item Selecionado'}</Text>
          <Text style={styles.itemPrice}>{preco || 'R$ 0,00'}</Text>
        </View>

        <TouchableOpacity 
          style={styles.checkoutButton}
          onPress={() => router.replace('/confirmacao')}
        >
          <Text style={styles.checkoutText}>Finalizar Compra (replace)</Text>
        </TouchableOpacity>

        <TouchableOpacity 
          style={styles.backButton}
          onPress={() => router.back()}
        >
          <Text style={styles.backText}>← Voltar aos Detalhes (router.back)</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: '#F9FAFB', padding: 20, justifyContent: 'center' },
  summaryCard: { backgroundColor: '#FFF', padding: 24, borderRadius: 16, elevation: 3 },
  header: { fontSize: 20, fontWeight: 'bold', color: '#111827', marginBottom: 16 },
  itemRow: { flexDirection: 'row', justifyContent: 'space-between', marginBottom: 24 },
  itemText: { fontSize: 16, color: '#374151' },
  itemPrice: { fontSize: 16, fontWeight: 'bold', color: '#111827' },
  checkoutButton: { backgroundColor: '#10B981', padding: 14, borderRadius: 10, alignItems: 'center' },
  checkoutText: { color: '#FFF', fontWeight: 'bold', fontSize: 16 },
  backButton: { marginTop: 12, padding: 12, alignItems: 'center' },
  backText: { color: '#4B5563', fontSize: 15 },
});
