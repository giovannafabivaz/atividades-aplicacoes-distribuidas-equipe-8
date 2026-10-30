import { View, Text, StyleSheet } from 'react-native'

interface TarefaProps {
  texto: string
}

export default function Tarefa({ texto }: TarefaProps) {
  return (
    <View style={styles.item}>
      <Text style={styles.texto}>{texto}</Text>
    </View>
  )
}

const styles = StyleSheet.create({
  item: {
    padding: 12,
    borderBottomWidth: 1,
    borderColor: '#374151',
  },
  texto: {
    color: '#f9fafb',
    fontSize: 16,
  },
})
