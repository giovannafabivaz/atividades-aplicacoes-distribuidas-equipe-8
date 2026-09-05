import { View, Text, Button, StyleSheet, FlatList } from 'react-native';
import { useContador } from './context/ContadorContext';
import { useRouter } from 'expo-router';

export default function Historico() {
  const { historico, limparHistorico } = useContador();
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Histórico (Últimos 10)</Text>
      
      {historico.length === 0 ? (
        <Text style={styles.empty}>Nenhum histórico ainda.</Text>
      ) : (
        <FlatList
          data={historico}
          keyExtractor={(item, index) => index.toString()}
          renderItem={({ item, index }) => (
            <Text style={styles.item}>{index + 1}º - Valor: {item}</Text>
          )}
          style={styles.list}
        />
      )}
      
      <View style={styles.botoes}>
        <Button title="Limpar Histórico" onPress={limparHistorico} color="#d9534f" />
        <Button title="Voltar" onPress={() => router.push("/")} color="gray" />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { padding: 20, marginTop: 50, flex: 1 },
  title: { fontSize: 24, textAlign: 'center', marginBottom: 20 },
  empty: { textAlign: 'center', fontSize: 16, color: '#666', marginBottom: 20 },
  list: { marginBottom: 20 },
  item: { fontSize: 18, paddingVertical: 10, borderBottomWidth: 1, borderBottomColor: '#ccc' },
  botoes: { gap: 15 }
});