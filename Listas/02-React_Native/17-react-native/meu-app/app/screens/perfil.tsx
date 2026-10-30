import {
  View,
  Text,
  Image,
  TouchableOpacity,
  StyleSheet,
} from 'react-native';
import { useRouter } from 'expo-router';

export default function Perfil() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Perfil do Usuário</Text>

      <Image
        source={require('../../assets/images/logo.png')}
        style={styles.avatar}
      />

      <Text style={styles.informacao}>Nome: Usuário</Text>
      <Text style={styles.informacao}>E-mail: usuario@email.com</Text>

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
    borderColor: 'purple',
  },

  titulo: {
    fontSize: 24,
    marginBottom: 20,
    textAlign: 'center',
  },

  avatar: {
    width: 120,
    height: 120,
    alignSelf: 'center',
    marginBottom: 20,
    borderRadius: 60,
  },

  informacao: {
    fontSize: 16,
    marginBottom: 10,
  },

  botao: {
    backgroundColor: '#007AFF',
    padding: 12,
    borderRadius: 6,
    marginTop: 10,
  },

  textoDoBotao: {
    color: '#fff',
    textAlign: 'center',
    fontSize: 16,
  },
});