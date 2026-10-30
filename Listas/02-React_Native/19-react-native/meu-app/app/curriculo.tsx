import { StyleSheet, View, Text } from 'react-native';
import { Link } from 'expo-router';

export default function Curriculo() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Currículo Profissional</Text>
      
      <View style={styles.card}>
        <Text style={styles.sectionHeader}>Competências Técnicas:</Text>
        <Text style={styles.item}>• React Native, TypeScript e Expo Router</Text>
        <Text style={styles.item}>• Desenvolvimento Frontend e Mobile</Text>
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
    marginBottom: 16,
    color: '#1a1a1a',
  },
  card: {
    backgroundColor: '#f7f9fa',
    padding: 16,
    borderRadius: 8,
    width: '90%',
    marginBottom: 20,
  },
  sectionHeader: {
    fontWeight: 'bold',
    fontSize: 15,
    marginBottom: 6,
  },
  item: {
    fontSize: 14,
    color: '#444',
    marginVertical: 2,
  },
  link: {
    color: 'blue',
    fontSize: 16,
  },
});