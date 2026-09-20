import {
  View,
  Text,
  Image,
  TouchableOpacity,
  StyleSheet,
} from 'react-native';
import { useRouter } from 'expo-router';

export default function TelaInicial() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Image
        source={require('../assets/images/icon.png')}
        style={styles.logo}
      />

      <Text style={styles.titulo}>Viaje Mais</Text>

      <Text style={styles.subtitulo}>
        Encontre seu próximo destino
      </Text>

      <TouchableOpacity
        style={styles.botao}
        onPress={() => router.push('/screens/destinos')}
      >
        <Text style={styles.textoBotao}>Ver destinos</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={styles.botao}
        onPress={() => router.push('/screens/reservas')}
      >
        <Text style={styles.textoBotao}>Minhas reservas</Text>
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
    borderColor: 'blue',
  },

  logo: {
    width: 100,
    height: 100,
    alignSelf: 'center',
    marginBottom: 20,
  },

  titulo: {
    fontSize: 28,
    textAlign: 'center',
    marginBottom: 10,
  },

  subtitulo: {
    fontSize: 16,
    textAlign: 'center',
    marginBottom: 20,
  },

  botao: {
    backgroundColor: '#007AFF',
    padding: 12,
    borderRadius: 6,
    marginBottom: 12,
  },

  textoBotao: {
    color: '#fff',
    textAlign: 'center',
    fontSize: 16,
  },
});