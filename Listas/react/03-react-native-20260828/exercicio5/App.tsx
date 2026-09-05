import { StatusBar } from 'expo-status-bar';
import { StyleSheet, View } from 'react-native';
import TelaDePostagens from './components/TelaDePostagens';

export default function App() {
  return (
    <View style={styles.container}>
      <TelaDePostagens />
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
