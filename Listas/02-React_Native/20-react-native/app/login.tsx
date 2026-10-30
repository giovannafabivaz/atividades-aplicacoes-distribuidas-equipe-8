import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { useRouter } from 'expo-router';

export default function LoginScreen() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Tela de Login</Text>
      <Text style={styles.subtitle}>Ao entrar, o login sairá da pilha de histórico.</Text>

      <TouchableOpacity 
        style={styles.button}
        onPress={() => router.replace('/')}
      >
        <Text style={styles.buttonText}>Entrar (replace para Home)</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: '#F3F4F6', justifyContent: 'center', alignItems: 'center', padding: 20 },
  title: { fontSize: 24, fontWeight: 'bold', color: '#111827', marginBottom: 8 },
  subtitle: { fontSize: 14, color: '#6B7280', textAlign: 'center', marginBottom: 24 },
  button: { backgroundColor: '#2563EB', paddingVertical: 14, paddingHorizontal: 32, borderRadius: 8 },
  buttonText: { color: '#FFF', fontWeight: 'bold', fontSize: 16 },
});
