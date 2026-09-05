import { useEffect, useState } from 'react';
import { View, Text, StyleSheet, FlatList, ActivityIndicator, TouchableOpacity } from 'react-native';
import { tarefasMockadas } from '../data/Tarefa';
import { Tarefa } from '../types/Tarefa';

export default function TelaDaListaDeTarefas() {
  const [tarefas, setTarefas] = useState<Tarefa[]>([]);
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setTarefas(tarefasMockadas);
      setCarregando(false);
    }, 2000); // Reduzi para 2 segundos para o teste ser mais rápido

    return () => clearTimeout(timer);
  }, []);

  const alternarConclusao = (id: number) => {
    setTarefas(prev => prev.map(t => 
      t.id === id ? { ...t, concluida: !t.concluida } : t
    ));
  };

  const totalConcluidas = tarefas.filter(t => t.concluida).length;

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Minhas Tarefas</Text>
      
      {!carregando && (
        <Text style={styles.contador}>Concluídas: {totalConcluidas} de {tarefas.length}</Text>
      )}

      {carregando ? (
        <View style={styles.carregando}>
          <ActivityIndicator size="large" color="#60a5fa" />
          <Text style={styles.textoCarregando}>Carregando tarefas...</Text>
        </View>
      ) : (
        <FlatList
          data={tarefas}
          keyExtractor={(item) => item.id.toString()}
          renderItem={({ item }) => (
            <TouchableOpacity 
              style={styles.itemContainer} 
              onPress={() => alternarConclusao(item.id)}
            >
              <Text style={[styles.itemTexto, item.concluida && styles.textoRiscado]}>
                {item.id} - {item.titulo}
              </Text>
              <Text style={styles.status}>
                {item.concluida ? "✅" : "⭕"}
              </Text>
            </TouchableOpacity>
          )}
        />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, backgroundColor: '#000000' },
  title: { fontSize: 28, fontWeight: 'bold', color: '#60a5fa', marginBottom: 10, marginTop: 40 },
  contador: { fontSize: 16, color: '#aaaaaa', marginBottom: 20 },
  carregando: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  textoCarregando: { color: '#ffffff', marginTop: 10 },
  itemContainer: { 
    flexDirection: 'row', 
    justifyContent: 'space-between', 
    alignItems: 'center',
    paddingVertical: 15, 
    borderBottomWidth: 1, 
    borderColor: '#333333' 
  },
  itemTexto: { fontSize: 18, color: '#ffffff' },
  textoRiscado: { textDecorationLine: 'line-through', color: '#555555' },
  status: { fontSize: 18 }
});
