import { StatusBar } from 'expo-status-bar'
import { View, StyleSheet } from 'react-native'
import TelaDeFavoritos from './screens/TelaDeFavoritos'

export default function App() {
  return (
    <View style={styles.container}>
      <TelaDeFavoritos />
      <StatusBar style="light" />
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000000',
  },
})
