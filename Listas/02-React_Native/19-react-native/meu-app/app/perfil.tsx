import { StyleSheet, View, Text } from 'react-native';
import { Link } from 'expo-router';

export default function Perfil() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Perfil</Text>
      <Text style={styles.subtitle}>Informações do Usuário</Text>

      <Link href="/" style={styles.link}>
        Voltar para Home
      </Link>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
    backgroundColor: '#fff',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#2c3e50',
  },
  subtitle: {
    fontSize: 15,
    color: '#7f8c8d',
    marginTop: 8,
  },
  link: {
    color: 'blue',
    marginTop: 16,
    fontSize: 16,
  },
});