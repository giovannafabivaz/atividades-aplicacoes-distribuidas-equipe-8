
import { View, Text, TouchableOpacity, StyleSheet, ScrollView } from 'react-native';
import { useRouter } from 'expo-router';

const produtos = [
  { id: '1', nome: 'Camiseta', preco: '49,90', descricao: 'Camiseta de algodão', tamanho: 'M' },
  { id: '2', nome: 'Calça Jeans', preco: '119,90', descricao: 'Calça jeans azul', tamanho: '42' },
  { id: '3', nome: 'Jaqueta', preco: '199,90', descricao: 'Jaqueta preta', tamanho: 'G' },
  { id: '4', nome: 'Moletom', preco: '149,90', descricao: 'Moletom cinza', tamanho: 'M' },
];

export default function Home() {
  const router = useRouter();

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.titulo}>Loja de Roupas</Text>
      <TouchableOpacity
  style={styles.botaoAlunos}
  onPress={() => router.push('/alunos')}
>
  <Text style={styles.textoBotao}>Ver Alunos da Universidade →</Text>
</TouchableOpacity>

      {produtos.map((produto) => (
        <TouchableOpacity
          key={produto.id}
          style={styles.card}
          onPress={() =>
            router.push({
              pathname: '/produto/[id]',
              params: produto,
            })
          }
        >
          <Text style={styles.nome}>{produto.nome}</Text>
          <Text style={styles.preco}>R$ {produto.preco}</Text>
          <Text style={styles.detalhes}>Ver detalhes →</Text>
        </TouchableOpacity>
      ))}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    padding: 20,
    backgroundColor: '#F7F9FC',
  },
  titulo: {
    fontSize: 26,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 25,
  },
  card: {
    backgroundColor: '#FFFFFF',
    padding: 20,
    borderRadius: 10,
    marginBottom: 15,
    elevation: 3,
  },
  nome: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  preco: {
    fontSize: 18,
    color: '#2563EB',
    marginBottom: 10,
  },
  detalhes: {
    color: '#555555',
  },
  botaoAlunos: {
  backgroundColor: '#2563EB',
  padding: 15,
  borderRadius: 10,
  alignItems: 'center',
  marginBottom: 25,
},
textoBotao: {
  color: '#FFFFFF',
  fontSize: 16,
  fontWeight: 'bold',
},
});
