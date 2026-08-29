import { useEffect, useState } from 'react';
import { View, Text, StyleSheet, FlatList, ActivityIndicator } from 'react-native';

interface Usuario {
  id: string;
  nome: string;
  telefone: string;
  email: string;
  cidade: string;
}

const usuariosSimulados: Usuario[] = [
  { id: '1', nome: 'João Silva', telefone: '(11) 98888-8888', email: 'joao@email.com', cidade: 'São Paulo' },
  { id: '2', nome: 'Maria Souza', telefone: '(21) 97777-7777', email: 'maria@email.com', cidade: 'Rio de Janeiro' },
  { id: '3', nome: 'Carlos Oliveira', telefone: '(31) 96666-6666', email: 'carlos@email.com', cidade: 'Belo Horizonte' },
  { id: '4', nome: 'Ana Costa', telefone: '(41) 95555-5555', email: 'ana@email.com', cidade: 'Curitiba' },
];

export default function TelaDeUsuarios() {
  const [usuarios, setUsuarios] = useState<Usuario[]>([]);
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setUsuarios(usuariosSimulados);
      setCarregando(false);
    }, 2500); // Simulação de 2.5 segundos

    return () => clearTimeout(timer);
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Usuários Cadastrados</Text>

      {carregando ? (
        <View style={styles.centro}>
          <ActivityIndicator size="large" color="#60a5fa" />
          <Text style={styles.textoCarregando}>Buscando usuários...</Text>
        </View>
      ) : (
        <FlatList
          data={usuarios}
          keyExtractor={(item) => item.id}
          renderItem={({ item }) => (
            <View style={styles.card}>
              <Text style={styles.nome}>{item.nome}</Text>
              <Text style={styles.info}>📞 {item.telefone}</Text>
              <Text style={styles.info}>✉️ {item.email}</Text>
              <Text style={styles.info}>📍 {item.cidade}</Text>
            </View>
          )}
        />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, backgroundColor: '#000000' },
  title: { fontSize: 28, fontWeight: 'bold', color: '#60a5fa', marginBottom: 20, marginTop: 40 },
  centro: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  textoCarregando: { color: '#ffffff', marginTop: 10, fontSize: 16 },
  card: { 
    padding: 20, 
    backgroundColor: '#111111', 
    borderRadius: 12, 
    marginBottom: 15,
    borderWidth: 1,
    borderColor: '#333333'
  },
  nome: { fontSize: 20, color: '#ffffff', fontWeight: 'bold', marginBottom: 8 },
  info: { fontSize: 14, color: '#aaaaaa', marginBottom: 4 }
});
