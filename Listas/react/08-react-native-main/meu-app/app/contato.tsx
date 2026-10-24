import { StyleSheet, View, Text } from 'react-native';
import { Link } from 'expo-router';

export default function Contato() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Contato</Text>
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
    backgroundColor: '#fff',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#008080',
  },
  link: {
    color: 'blue',
    marginTop: 12,
    fontSize: 16,
  },
});