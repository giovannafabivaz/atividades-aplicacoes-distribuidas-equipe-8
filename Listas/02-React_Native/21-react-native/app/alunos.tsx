
import { View, Text, TouchableOpacity, ScrollView, StyleSheet } from 'react-native';
import { useRouter } from 'expo-router';

const alunos = [
  {
    id: '1',
    nome: 'Carlos Silva',
    matricula: '2024001',
    curso: 'Sistemas de Informação',
    disciplinas: JSON.stringify([
      { nome: 'Programação', nota: 8.5 },
      { nome: 'Banco de Dados', nota: 9.0 },
      { nome: 'Redes de Computadores', nota: 7.5 },
    ]),
  },
  {
    id: '2',
    nome: 'Ana Oliveira',
    matricula: '2024002',
    curso: 'Ciência da Computação',
    disciplinas: JSON.stringify([
      { nome: 'Algoritmos', nota: 9.5 },
      { nome: 'Matemática Discreta', nota: 8.0 },
      { nome: 'Engenharia de Software', nota: 9.0 },
    ]),
  },
  {
    id: '3',
    nome: 'Pedro Santos',
    matricula: '2024003',
    curso: 'Sistemas de Informação',
    disciplinas: JSON.stringify([
      { nome: 'Programação', nota: 7.0 },
      { nome: 'Banco de Dados', nota: 8.5 },
      { nome: 'Estatística', nota: 8.0 },
    ]),
  },
];

export default function Alunos() {
  const router = useRouter();

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.titulo}>Alunos da Universidade</Text>

      {alunos.map((aluno) => (
        <TouchableOpacity
          key={aluno.id}
          style={styles.card}
          onPress={() =>
            router.push({
              pathname: '/aluno/[id]',
              params: aluno,
            })
          }
        >
          <Text style={styles.nome}>{aluno.nome}</Text>
          <Text style={styles.curso}>{aluno.curso}</Text>
          <Text style={styles.detalhes}>Ver detalhes →</Text>
        </TouchableOpacity>
      ))}
    </ScrollView>
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
    marginBottom: 25,
  },
  card: {
    backgroundColor: '#FFFFFF',
    padding: 20,
    borderRadius: 10,
    marginBottom: 15,
    elevation: 3,
  },
  nome: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  curso: {
    fontSize: 16,
    color: '#2563EB',
    marginBottom: 10,
  },
  detalhes: {
    color: '#555555',
  },
});
