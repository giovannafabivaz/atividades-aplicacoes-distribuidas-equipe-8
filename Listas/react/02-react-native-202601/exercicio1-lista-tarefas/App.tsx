import { StatusBar } from 'expo-status-bar'
import { StyleSheet, View } from 'react-native'
import TelaDaListaDeTarefas from './screens/TelaDaListaDeTarefas'

export default function App() {
  return (
    <View style={styles.container}>
      <TelaDaListaDeTarefas />
      <StatusBar style="light" />
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#111827',
  },
})
