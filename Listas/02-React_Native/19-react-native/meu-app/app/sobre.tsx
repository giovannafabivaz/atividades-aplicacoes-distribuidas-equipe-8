import { StyleSheet, View, Text } from 'react-native';
import { Link } from 'expo-router';

export default function Sobre() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Tela Sobre</Text>
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
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: 'green',
  },
  link: {
    color: 'blue',
    marginTop: 10,
  },
});
