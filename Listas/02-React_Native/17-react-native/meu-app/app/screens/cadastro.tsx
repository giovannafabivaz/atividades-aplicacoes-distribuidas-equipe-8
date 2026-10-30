import {
  View,
  Text,
  TextInput,
  TouchableOpacity,
  StyleSheet,
} from 'react-native';
import { useRouter } from 'expo-router';

export default function Cadastro() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Cadastro</Text>

      <TextInput
        style={styles.input}
        placeholder="Nome"
      />

      <TextInput
        style={styles.input}
        placeholder="E-mail"
      />

      <TextInput
        style={styles.input}
        placeholder="Senha"
        secureTextEntry
      />

      <TouchableOpacity style={styles.botao}>
        <Text style={styles.textoDoBotao}>Cadastrar</Text>
      </TouchableOpacity>

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
    borderColor: 'gray',
  },

  titulo: {
    fontSize: 24,
    marginBottom: 20,
  },

  input: {
    borderWidth: 1,
    borderColor: 'gray',
    padding: 12,
    borderRadius: 6,
    marginBottom: 12,
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