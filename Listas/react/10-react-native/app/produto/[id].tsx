
import { View, Text, StyleSheet } from 'react-native';
import { Stack, useLocalSearchParams } from 'expo-router';

export default function DetalhesProduto() {
  const { id, nome, preco, descricao, tamanho } = useLocalSearchParams();

  return (
    <>
      <Stack.Screen options={{ title: 'Detalhes do Produto' }} />

      <View style={styles.container}>
        <Text style={styles.titulo}>{nome}</Text>
        <Text style={styles.texto}>Código: {id}</Text>
        <Text style={styles.texto}>Preço: R$ {preco}</Text>
        <Text style={styles.texto}>Descrição: {descricao}</Text>
        <Text style={styles.texto}>Tamanho: {tamanho}</Text>
      </View>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
    backgroundColor: '#F7F9FC',
  },
  titulo: {
    fontSize: 26,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  texto: {
    fontSize: 18,
    marginBottom: 12,
    textAlign: 'center',
  },
});
