
import { View, Text, ScrollView, StyleSheet } from 'react-native';
import { Stack, useLocalSearchParams } from 'expo-router';

export default function DetalhesAluno() {
  const { nome, matricula, curso, disciplinas } = useLocalSearchParams<{
    id: string;
    nome: string;
    matricula: string;
    curso: string;
    disciplinas: string;
  }>();

  const listaDisciplinas: { nome: string; nota: number }[] =
    disciplinas ? JSON.parse(disciplinas) : [];

  return (
    <>
      <Stack.Screen options={{ title: 'Detalhes do Aluno' }} />

      <ScrollView contentContainerStyle={styles.container}>
        <Text style={styles.titulo}>{nome}</Text>
        <Text style={styles.texto}>Matrícula: {matricula}</Text>
        <Text style={styles.texto}>Curso: {curso}</Text>

        <Text style={styles.subtitulo}>Disciplinas Cursadas</Text>

        {listaDisciplinas.map((disciplina, index) => (
          <View key={index} style={styles.card}>
            <Text style={styles.nome}>{disciplina.nome}</Text>
            <Text style={styles.texto}>
              Nota Final: {disciplina.nota.toFixed(1)}
            </Text>
          </View>
        ))}
      </ScrollView>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    padding: 20,
    backgroundColor: '#F7F9FC',
  },
  titulo: {
    fontSize: 26,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 20,
  },
  subtitulo: {
    fontSize: 22,
    fontWeight: 'bold',
    marginTop: 25,
    marginBottom: 15,
  },
  texto: {
    fontSize: 18,
    marginBottom: 8,
  },
  card: {
    backgroundColor: '#FFFFFF',
    padding: 15,
    borderRadius: 10,
    marginBottom: 12,
  },
  nome: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 8,
  },
});
