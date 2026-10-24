import { StyleSheet, View, Text } from 'react-native';
import { Link } from 'expo-router';

export default function Promocoes() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Catálogo de Promoções</Text>
      
      <View style={styles.card}>
        <Text style={styles.product}>Notebook Gamer</Text>
        <Text style={styles.price}>Desconto: 20% OFF</Text>
      </View>

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
    fontSize: 22,
    fontWeight: 'bold',
    color: '#d9534f',
    marginBottom: 16,
  },
  card: {
    backgroundColor: '#fcf8e3',
    padding: 16,
    borderRadius: 8,
    width: '90%',
    marginBottom: 20,
  },
  product: {
    fontSize: 16,
    fontWeight: '600',
  },
  price: {
    fontSize: 14,
    color: '#3c763d',
    marginTop: 4,
  },
  link: {
    color: 'blue',
    fontSize: 16,
  },
});