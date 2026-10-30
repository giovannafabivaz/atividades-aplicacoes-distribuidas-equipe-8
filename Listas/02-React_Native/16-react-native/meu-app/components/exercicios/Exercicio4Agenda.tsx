import React, { useState, useRef } from 'react';
import { View, Text, TextInput, FlatList, TouchableOpacity, StyleSheet } from 'react-native';
import { styles } from '../../styles/styles';

export default function Exercicio4Agenda() {
  const [tarefas, setTarefas] = useState<string[]>([
    'Configurar ambiente Expo',
    'Estudar o hook useRef',
    'Desenvolver os exercícios práticos',
  ]);
  const [novaTarefa, setNovaTarefa] = useState<string>('');
  const flatListRef = useRef<FlatList>(null);

  const inserirTarefa = () => {
    if (novaTarefa.trim() === '') {
      return;
    }

    setTarefas((prevTarefas) => [...prevTarefas, novaTarefa.trim()]);
    setNovaTarefa('');

    setTimeout(() => {
      flatListRef.current?.scrollToEnd({ animated: true });
    }, 50);
  };

  return (
    <View style={[styles.caixa, styles.caixaRolagem]}>
      <Text style={styles.subtitulo}>Exercício 4: Agenda de Tarefas</Text>

      <TextInput
        style={styles.input}
        placeholder="Digite a nova tarefa..."
        value={novaTarefa}
        onChangeText={setNovaTarefa}
        onSubmitEditing={inserirTarefa}
      />

      <FlatList
        ref={flatListRef}
        data={tarefas}
        keyExtractor={(_, index) => index.toString()}
        renderItem={({ item }) => (
          <Text style={[styles.item, localStyles.itemTarefa]}>{item}</Text>
        )}
        style={styles.caixaRolagem}
      />

      <TouchableOpacity
        style={[styles.botaoAtivo, styles.configBotaoAtivo]}
        onPress={inserirTarefa}
      >
        <Text style={styles.textoBotaoAtivo}>Inserir</Text>
      </TouchableOpacity>
    </View>
  );
}

const localStyles = StyleSheet.create({
  itemTarefa: {
    borderRadius: 6,
    borderWidth: 1,
    borderColor: '#e5e5ea',
  },
});