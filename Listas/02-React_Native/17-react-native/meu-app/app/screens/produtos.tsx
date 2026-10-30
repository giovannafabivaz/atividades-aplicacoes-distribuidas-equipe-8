import {
  View,
  Text,
  Image,
  TouchableOpacity,
  StyleSheet,
} from 'react-native';
import { useRouter } from 'expo-router';

export default function Produtos() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Produtos</Text>

      <View style={styles.card}>
        <Image
          source={require('../../assets/images/logo.png')}
          style={styles.imagemProduto}
        />

        <Text style={styles.nomeProduto}>Produto Exemplo</Text>

        <Text style={styles.descricao}>
          Descrição do produto disponível para venda.
        </Text>

        <Text style={styles.preco}>R$ 99,90</Text>
      </View>

      <TouchableOpacity
        style={styles.botao}
        onPress={() => router.push('/screens/home')}
      >
        <Text style={styles.textoDoBotao}>
          Voltar para a tela Home
        </Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    margin: 20,
    padding: 20,
    marginTop: 50,
    borderWidth: 1,
    borderColor: 'orange',
  },

  titulo: {
    fontSize: 24,
    marginBottom: 20,
  },

  card: {
    borderWidth: 1,
    borderColor: 'gray',
    borderRadius: 8,
    padding: 15,
    marginBottom: 20,
  },

  imagemProduto: {
    width: 120,
    height: 120,
    alignSelf: 'center',
    marginBottom: 15,
  },

  nomeProduto: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 10,
  },

  descricao: {
    fontSize: 16,
    marginBottom: 10,
  },

  preco: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
  },

  botao: {
    backgroundColor: '#007AFF',
    padding: 12,
    borderRadius: 6,
    marginBottom: 12,
  },

  textoDoBotao: {
    color: '#fff',
    textAlign: 'center',
    fontSize: 16,
  },
});