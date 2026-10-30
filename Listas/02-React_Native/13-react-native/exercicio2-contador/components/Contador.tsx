import { useState } from 'react'
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native'

export default function Contador() {
  const [valor, setValor] = useState<number>(0)

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Contador</Text>

      <Text style={styles.valor}>{valor}</Text>

      <View style={styles.linhaBotoes}>
        <TouchableOpacity
          style={styles.botao}
          onPress={() => setValor(valor + 1)}
        >
          <Text style={styles.textoBotao}>Incrementar</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={styles.botao}
          onPress={() => setValor(valor - 1)}
        >
          <Text style={styles.textoBotao}>Decrementar</Text>
        </TouchableOpacity>
      </View>

      <TouchableOpacity
        style={styles.botaoZerar}
        onPress={() => setValor(0)}
      >
        <Text style={styles.textoBotao}>Zerar</Text>
      </TouchableOpacity>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#111827',
    alignItems: 'center',
    justifyContent: 'center',
    padding: 20,
  },
  titulo: {
    color: '#60a5fa',
    fontSize: 28,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  valor: {
    color: '#f9fafb',
    fontSize: 64,
    fontWeight: 'bold',
    marginBottom: 30,
  },
  linhaBotoes: {
    flexDirection: 'row',
    gap: 10,
    marginBottom: 15,
  },
  botao: {
    backgroundColor: '#2563eb',
    padding: 14,
    borderRadius: 8,
  },
  botaoZerar: {
    backgroundColor: '#dc2626',
    padding: 14,
    borderRadius: 8,
    minWidth: 150,
    alignItems: 'center',
  },
  textoBotao: {
    color: '#ffffff',
    fontWeight: 'bold',
  },
})
