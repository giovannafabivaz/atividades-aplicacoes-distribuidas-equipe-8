import { Text, StyleSheet } from 'react-native'

interface TituloProps {
  texto: string
}

export default function Titulo({ texto }: TituloProps) {
  return <Text style={styles.titulo}>{texto}</Text>
}

const styles = StyleSheet.create({
  titulo: {
    color: '#60a5fa',
    fontSize: 26,
    fontWeight: 'bold',
    marginBottom: 16,
  },
})
