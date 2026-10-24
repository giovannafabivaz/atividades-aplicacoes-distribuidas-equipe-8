import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { useRouter, useLocalSearchParams } from 'expo-router';

export default function CarrinhoScreen() {
  const router = useRouter();
  const { nome, preco } = useLocalSearchParams<{ nome: string; preco: string }>();

  // Função segura de retorno com canGoBack()
  const handleVoltar = () => {
    if (router.canGoBack()) {
      router.back();
    } else {
      router.replace('/');
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.summaryCard}>
        <Text style={styles.header}>Resumo do Pedido</Text>
        
        <View style={styles.itemRow}>
          <Text style={styles.itemText}>{nome || 'Item Selecionado'}</Text>
          <Text style={styles.itemPrice}>{preco || 'R$ 0,00'}</Text>
        </View>

        {/* Substitui a tela do carrinho pela de confirmação (Exercício 3) */}
        <TouchableOpacity 
          style={styles.checkoutButton}
          onPress={() => router.replace('/confirmacao')}
        >
          <Text style={styles.checkoutText}>Finalizar Compra (replace)</Text>
        </TouchableOpacity>

        {/* Retorno seguro à tela anterior ou início */}
        <TouchableOpacity 
          style={styles.backButton}
          onPress={handleVoltar}
        >
          <Text style={styles.backText}>← Voltar aos Detalhes (router.back)</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F9FAFB',
    padding: 20,
    justifyContent: 'center',
  },
  summaryCard: {
    backgroundColor: '#FFF',
    padding: 24,
    borderRadius: 16,
    elevation: 3,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.08,
    shadowRadius: 8,
  },
  header: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#111827',
    marginBottom: 16,
  },
  itemRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 24,
    paddingBottom: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#E5E7EB',
  },
  itemText: {
    fontSize: 16,
    color: '#374151',
    fontWeight: '500',
  },
  itemPrice: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#111827',
  },
  checkoutButton: {
    backgroundColor: '#10B981',
    paddingVertical: 14,
    borderRadius: 10,
    alignItems: 'center',
  },
  checkoutText: {
    color: '#FFF',
    fontWeight: 'bold',
    fontSize: 16,
  },
  backButton: {
    marginTop: 12,
    paddingVertical: 12,
    alignItems: 'center',
  },
  backText: {
    color: '#4B5563',
    fontSize: 15,
  },
});