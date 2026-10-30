import { StatusBar } from 'expo-status-bar';
import { StyleSheet, View } from 'react-native';
import TelaDeUsuarios from './components/TelaDeUsuarios';

export default function App() {
  return (
    <View style={styles.container}>
      <TelaDeUsuarios />
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
