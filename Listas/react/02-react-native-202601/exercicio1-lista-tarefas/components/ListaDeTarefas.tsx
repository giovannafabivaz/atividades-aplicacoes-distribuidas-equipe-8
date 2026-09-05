import { View, StyleSheet } from 'react-native'
import Tarefa from './Tarefa'

interface ListaDeTarefasProps {
  tarefas: string[]
}

export default function ListaDeTarefas({ tarefas }: ListaDeTarefasProps) {
  return (
    <View style={styles.lista}>
      {tarefas.map((tarefa, indice) => (
        <Tarefa key={indice} texto={tarefa} />
      ))}
    </View>
  )
}

const styles = StyleSheet.create({
  lista: {
    marginTop: 20,
  },
})
