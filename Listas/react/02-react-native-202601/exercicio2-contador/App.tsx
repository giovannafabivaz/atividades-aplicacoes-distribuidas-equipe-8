import { StatusBar } from 'expo-status-bar'
import { View, StyleSheet } from 'react-native'
import Contador from './components/Contador'

export default function App() {
  return (
    <View style={styles.container}>
      <Contador />
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
