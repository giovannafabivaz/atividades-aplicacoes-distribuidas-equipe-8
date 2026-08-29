import { StatusBar } from 'expo-status-bar';
import { StyleSheet, View } from 'react-native';
import TelaDeNotificacoes from './components/TelaDeNotificacoes';

export default function App() {
  return (
    <View style={styles.container}>
      <TelaDeNotificacoes />
      <StatusBar style="light" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000000',
  },
});
