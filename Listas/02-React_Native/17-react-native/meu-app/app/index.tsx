import { View, Text, Image, TouchableOpacity, StyleSheet } from 'react-native';
import { Link } from 'expo-router';

export default function TelaInicial() {
  return (
    <View style={styles.container}>
      
      <View style={styles.cabecalho}>
        <Image
          source={require('../assets/images/logo.png')}
          style={styles.logo}
        />
        <Text style={styles.titulo}>Tela Inicial</Text>
      </View>

      <Link href="/screens/login" asChild>
        <TouchableOpacity style={styles.botao}>
          <Text style={styles.textoDoBotao}>Ir para a tela de login</Text>
        </TouchableOpacity>
      </Link>

      <Link href="/screens/cadastro" asChild>
        <TouchableOpacity style={styles.botao}>
          <Text style={styles.textoDoBotao}>Ir para a tela de cadastro</Text>
        </TouchableOpacity>
      </Link>

      <Link href="/screens/home" asChild>
        <TouchableOpacity style={styles.botao}>
          <Text style={styles.textoDoBotao}>Ir para a tela home</Text>
        </TouchableOpacity>
      </Link>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { margin: 20, padding: 20, marginTop: 50, borderWidth: 1, borderColor: 'blue' },
  cabecalho: {
    flexDirection: 'row', // coloca elementos lado a lado
    justifyContent: 'center', // alinha horizontalmente
    alignContent: 'center',
    marginBottom: 20,
  },
  logo: {
    width: 40,
    height: 40,
    marginRight: 10, // espaço entre imagem e texto
  },
  titulo: { fontSize: 24, marginBottom: 20 },
  botao: {
    backgroundColor: '#007AFF',
    padding: 12,
    borderRadius: 6,
    marginBottom: 12, // margem inferior
  },
  textoDoBotao: {
    color: '#fff',
    textAlign: 'center',
    fontSize: 16,
  },
});
