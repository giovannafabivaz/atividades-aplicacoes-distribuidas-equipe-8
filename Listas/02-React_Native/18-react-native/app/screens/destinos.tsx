import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
} from 'react-native';
import { useRouter } from 'expo-router';

export default function Destinos() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Destinos</Text>

      <View style={styles.card}>
        <Text style={styles.destino}>Rio de Janeiro</Text>
        <Text>Praias, turismo e lazer.</Text>
      </View>

      <View style={styles.card}>
        <Text style={styles.destino}>Gramado</Text>
        <Text>Serra, gastronomia e passeios.</Text>
      </View>

      <View style={styles.card}>
        <Text style={styles.destino}>Salvador</Text>
        <Text>História, cultura e praias.</Text>
      </View>

      <TouchableOpacity
        style={styles.botao}
        onPress={() => router.push('/')}
      >
        <Text style={styles.textoBotao}>Voltar</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    margin: 20,
    padding: 20,
    marginTop: 50,
  },

  titulo: {
    fontSize: 26,
    marginBottom: 20,
  },

  card: {
    borderWidth: 1,
    borderColor: 'gray',
    padding: 15,
    borderRadius: 6,
    marginBottom: 12,
  },

  destino: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 5,
  },

  botao: {
    backgroundColor: '#007AFF',
    padding: 12,
    borderRadius: 6,
    marginTop: 10,
  },

  textoBotao: {
    color: '#fff',
    textAlign: 'center',
    fontSize: 16,
  },
});