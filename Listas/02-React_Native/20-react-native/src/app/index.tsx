import React from 'react';
import { View, Text, FlatList, TouchableOpacity, StyleSheet } from 'react-native';
import { useRouter } from 'expo-router';

const PRODUTOS = [
  { id: '1', nome: 'Tênis Esportivo', preco: 'R$ 299,00', descricao: 'Tênis de corrida leve e confortável com amortecimento.' },
  { id: '2', nome: 'Camiseta Dry-Fit', preco: 'R$ 89,90', descricao: 'Ideal para treinos intensos, tecido respirável.' },
  { id: '3', nome: 'Boné Esportivo', preco: 'R$ 59,90', descricao: 'Proteção UV e ajuste personalizado para corrida.' },
];

export default function CatalogoScreen() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Nossos Produtos</Text>
      
      <FlatList
        data={PRODUTOS}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <TouchableOpacity
            style={styles.card}
            onPress={() => router.push({
              pathname: '/detalhes',
              params: {
                id: item.id,
                nome: item.nome,
                preco: item.preco,
                descricao: item.descricao,
              },
            })}
          >
            <View>
              <Text style={styles.cardName}>{item.nome}</Text>
              <Text style={styles.cardPrice}>{item.preco}</Text>
            </View>
            <Text style={styles.arrow}>Ver mais →</Text>
          </TouchableOpacity>
        )}
      />

      <View style={styles.footerNav}>
        <TouchableOpacity 
          style={styles.extraButton} 
          onPress={() => router.push('/login')}
        >
          <Text style={styles.extraButtonText}>Ir para Exercício 5 (Login)</Text>
        </TouchableOpacity>

        <TouchableOpacity 
          style={[styles.extraButton, { backgroundColor: '#10B981' }]} 
          onPress={() => router.push('/cadastro/etapa1')}
        >
          <Text style={styles.extraButtonText}>Ir para Exercício 7 (Etapas)</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: '#F9FAFB', padding: 16 },
  title: { fontSize: 22, fontWeight: 'bold', color: '#111827', marginVertical: 12 },
  card: {
    backgroundColor: '#FFF',
    padding: 16,
    borderRadius: 12,
    marginBottom: 12,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    elevation: 2,
  },
  cardName: { fontSize: 16, fontWeight: '600', color: '#1F2937' },
  cardPrice: { fontSize: 14, color: '#2563EB', marginTop: 4, fontWeight: 'bold' },
  arrow: { fontSize: 14, color: '#6B7280' },
  footerNav: { marginTop: 16, gap: 8 },
  extraButton: {
    backgroundColor: '#4B5563',
    padding: 12,
    borderRadius: 8,
    alignItems: 'center',
  },
  extraButtonText: { color: '#FFF', fontWeight: 'bold' },
});
