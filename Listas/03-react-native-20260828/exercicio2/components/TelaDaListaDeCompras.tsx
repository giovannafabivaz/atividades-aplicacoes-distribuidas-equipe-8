import { useEffect, useState } from 'react';
import { View, Text, StyleSheet, FlatList, ActivityIndicator } from 'react-native';

interface ItemCompra {
  id: string;
  nome: string;
  quantidade: number;
}

const comprasSimuladas: ItemCompra[] = [
  { id: '1', nome: 'Arroz 5kg', quantidade: 1 },
  { id: '2', nome: 'Feijão Carioca', quantidade: 2 },
  { id: '3', nome: 'Leite Integral', quantidade: 12 },
  { id: '4', nome: 'Café Torrado', quantidade: 3 },
  { id: '5', nome: 'Açúcar Refinado', quantidade: 1 },
];

export default function TelaDaListaDeCompras() {
  const [itens, setItens] = useState<ItemCompra[]>([]);
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setItens(comprasSimuladas);
      setCarregando(false);
    }, 2000);

    return () => clearTimeout(timer);
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Lista de Compras</Text>

      {carregando ? (
        <View style={styles.centro}>
          <ActivityIndicator size="large" color="#60a5fa" />
          <Text style={styles.textoCarregando}>Carregando lista...</Text>
        </View>
      ) : (
        <FlatList
          data={itens}
          keyExtractor={(item) => item.id}
          renderItem={({ item }) => (
            <View style={styles.itemContainer}>
              <View>
                <Text style={styles.itemNome}>{item.nome}</Text>
                <Text style={styles.itemId}>ID: {item.id}</Text>
              </View>
              <Text style={styles.itemQtd}>Qtd: {item.quantidade}</Text>
            </View>
          )}
        />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, backgroundColor: '#000000' },
  title: { fontSize: 28, fontWeight: 'bold', color: '#60a5fa', marginBottom: 20, marginTop: 40 },
  centro: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  textoCarregando: { color: '#ffffff', marginTop: 10 },
  itemContainer: { 
    padding: 15, 
    borderBottomWidth: 1, 
    borderColor: '#333333',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center'
  },
  itemNome: { fontSize: 18, color: '#ffffff', fontWeight: '500' },
  itemId: { fontSize: 12, color: '#666666' },
  itemQtd: { fontSize: 16, color: '#aaaaaa' }
});
