import { View, Text, Button, StyleSheet } from 'react-native';
import { useContador } from './context/ContadorContext';
import { useRouter } from 'expo-router';

export default function Incrementar() {
  const { valor, incrementar, decrementar } = useContador();
  const router = useRouter();
  
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Valor atual: {valor}</Text>
      <Button title="Incrementar" onPress={incrementar} />
      <Button title="Decrementar" onPress={decrementar} />
      
      <Button title="Voltar" onPress={() => router.push("/")} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 20,
    marginTop: 50,
    gap: 20
  },
  title: {
    fontSize: 24,
    marginBottom: 20
  },
});