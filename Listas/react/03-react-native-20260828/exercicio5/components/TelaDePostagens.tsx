import { useEffect, useState } from 'react';
import { View, Text, StyleSheet, FlatList, ActivityIndicator } from 'react-native';

interface Post {
  userId: number;
  id: number;
  title: string;
  body: string;
}

export default function TelaDePostagens() {
  const [posts, setPosts] = useState<Post[]>([]);
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    // Buscando dados da API real
    fetch('https://jsonplaceholder.typicode.com/posts' )
      .then(response => response.json())
      .then(data => {
        setPosts(data);
        setCarregando(false);
      })
      .catch(error => {
        console.error("Erro ao buscar posts:", error);
        setCarregando(false);
      });
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Postagens da API</Text>

      {carregando ? (
        <View style={styles.centro}>
          <ActivityIndicator size="large" color="#60a5fa" />
          <Text style={styles.textoCarregando}>Carregando dados reais...</Text>
        </View>
      ) : (
        <FlatList
          data={posts}
          keyExtractor={(item) => item.id.toString()}
          renderItem={({ item }) => (
            <View style={styles.card}>
              <Text style={styles.postTitle}>{item.title}</Text>
              <Text style={styles.postBody}>{item.body}</Text>
              <Text style={styles.postId}>Post ID: {item.id}</Text>
            </View>
          )}
        />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, backgroundColor: '#000000' },
  centro: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  title: { fontSize: 28, fontWeight: 'bold', color: '#60a5fa', marginBottom: 20, marginTop: 40 },
  textoCarregando: { color: '#ffffff', marginTop: 10 },
  card: { 
    padding: 20, 
    backgroundColor: '#111111', 
    borderRadius: 12, 
    marginBottom: 15,
    borderWidth: 1,
    borderColor: '#333333'
  },
  postTitle: { 
    fontSize: 18, 
    color: '#ffffff', 
    fontWeight: 'bold', 
    marginBottom: 8,
    textTransform: 'capitalize' 
  },
  postBody: { fontSize: 14, color: '#aaaaaa', lineHeight: 20 },
  postId: { fontSize: 10, color: '#444444', marginTop: 10, textAlign: 'right' }
});
