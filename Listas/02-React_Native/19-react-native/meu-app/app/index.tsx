// app/index.tsx
import { StyleSheet, View, Text } from 'react-native';
import { Link } from 'expo-router';

export default function Home() {
  return (
    <View style={styles.container}>
      <Text style={styles.header}>Atividade 08 - React Native</Text>

      {/* Exercício 1 */}
      <View style={styles.section}>
        <Text style={styles.sectionTitle}>Exercício 1: Rotas Básicas</Text>
        <Link href="/sobre" style={styles.link}>Ir para Tela Sobre</Link>
        <Link href="/contato" style={styles.link}>Ir para Tela Contato</Link>
      </View>

      {/* Exercício 2 */}
      <View style={styles.section}>
        <Text style={styles.sectionTitle}>Exercício 2: Currículo Profissional</Text>
        <Link href="/curriculo" style={styles.link}>Aceder ao Currículo</Link>
      </View>

      {/* Exercício 3 */}
      <View style={styles.section}>
        <Text style={styles.sectionTitle}>Exercício 3: Catálogo Promocional</Text>
        <Link href="/promocoes" style={styles.link}>Aceder às Promoções</Link>
      </View>

      {/* Rota comum */}
      <View style={styles.section}>
        <Text style={styles.sectionTitle}>Geral</Text>
        <Link href="/perfil" style={styles.link}>Ver Perfil de Utilizador</Link>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 24,
    backgroundColor: '#f5f5f5',
  },
  header: {
    fontSize: 22,
    fontWeight: 'bold',
    marginBottom: 20,
    color: '#111',
  },
  section: {
    marginVertical: 8,
    alignItems: 'center',
  },
  sectionTitle: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 4,
  },
  link: {
    color: '#0066cc',
    fontSize: 15,
    marginVertical: 2,
  },
});