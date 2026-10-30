import { View, Text, Button, StyleSheet, Alert, Platform } from 'react-native';
import { Link } from 'expo-router';
import { useContador } from './context/ContadorContext';

export default function Home() {
  const { valor, resetar } = useContador();
  const handleReset = () => {
    if (Platform.OS === 'web') {
      const confirmar = window.confirm("Tem certeza que deseja zerar o contador?");
      if (confirmar) resetar();
    } else {
      Alert.alert(
        "Atenção",
        "Tem certeza que deseja zerar o contador?",
        [
          { text: "Cancelar", style: "cancel" },
          { text: "Sim, zerar", onPress: resetar }
        ]
      );
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Valor atual: {valor}</Text>
      
      <View style={styles.botoesContainer}>
        <Link href="/incrementar" asChild>
          <Button title="Ir para Incrementar/Decrementar" />
        </Link>
        
        <Link href="/definir-valor" asChild>
          <Button title="Definir um valor específico" />
        </Link>

        {/* Novos botões adicionados */}
        <Link href="/operacoes" asChild>
          <Button title="Operações Matemáticas" color="#007BFF" />
        </Link>

        <Link href="/historico" asChild>
          <Button title="Ver Histórico de Valores" color="#28a745" />
        </Link>

        <Button title="Zerar Contador (Reset)" onPress={handleReset} color="#d9534f" />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { padding: 20, marginTop: 50 },
  title: { fontSize: 24, marginBottom: 20, textAlign: 'center' },
  botoesContainer: { gap: 15 }
});