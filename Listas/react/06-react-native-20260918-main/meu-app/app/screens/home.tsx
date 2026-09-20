import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
} from 'react-native';
import { Link, useRouter } from 'expo-router';

export default function Home() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Home</Text>

      <Link href="/screens/login" asChild>
        <TouchableOpacity style={styles.botao}>
          <Text style={styles.textoDoBotao}>
            Ir para a tela de login
          </Text>
        </TouchableOpacity>
      </Link>

      <Link href="/screens/cadastro" asChild>
        <TouchableOpacity style={styles.botao}>
          <Text style={styles.textoDoBotao}>
            Ir para a tela de cadastro
          </Text>
        </TouchableOpacity>
      </Link>

      <Link href="/screens/perfil" asChild>
        <TouchableOpacity style={styles.botao}>
          <Text style={styles.textoDoBotao}>
            Ir para a tela de perfil
          </Text>
        </TouchableOpacity>
      </Link>

      <Link href="/screens/produtos" asChild>
        <TouchableOpacity style={styles.botao}>
          <Text style={styles.textoDoBotao}>
            Ir para a tela de produtos
          </Text>
        </TouchableOpacity>
      </Link>

      <TouchableOpacity
        style={styles.botao}
        onPress={() => router.push('/')}
      >
        <Text style={styles.textoDoBotao}>
          Voltar para a tela inicial
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
    borderColor: 'green',
  },

  titulo: {
    fontSize: 24,
    marginBottom: 20,
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