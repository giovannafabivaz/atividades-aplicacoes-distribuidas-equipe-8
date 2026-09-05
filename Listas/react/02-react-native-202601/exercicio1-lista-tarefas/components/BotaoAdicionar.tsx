import { TouchableOpacity, Text, StyleSheet } from 'react-native'

interface BotaoAdicionarProps {
  onPress: () => void
}

export default function BotaoAdicionar({ onPress }: BotaoAdicionarProps) {
  return (
    <TouchableOpacity style={styles.botao} onPress={onPress}>
      <Text style={styles.texto}>Adicionar tarefa</Text>
    </TouchableOpacity>
  )
}

const styles = StyleSheet.create({
  botao: {
    backgroundColor: '#2563eb',
    padding: 12,
    borderRadius: 6,
    alignItems: 'center',
  },
  texto: {
    color: '#ffffff',
    fontWeight: 'bold',
    fontSize: 16,
  },
})
