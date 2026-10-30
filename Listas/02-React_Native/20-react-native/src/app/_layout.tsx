import { Stack } from 'expo-router';

export default function RootLayout() {
  return (
    <Stack
      screenOptions={{
        headerStyle: { backgroundColor: '#2563EB' },
        headerTintColor: '#fff',
        headerTitleStyle: { fontWeight: 'bold' },
      }}
    >
      <Stack.Screen 
        name="index" 
        options={{ title: 'Início - Vitrine' }} 
      />
      <Stack.Screen 
        name="detalhes" 
        options={{ title: 'Detalhes do Produto' }} 
      />
      <Stack.Screen 
        name="carrinho" 
        options={{ title: 'Seu Carrinho' }} 
      />
      <Stack.Screen 
        name="confirmacao" 
        options={{ headerShown: false }} 
      />
      <Stack.Screen 
        name="login" 
        options={{ title: 'Acesso à Conta', headerShown: false }} 
      />
      <Stack.Screen 
        name="cadastro/etapa1" 
        options={{ title: 'Cadastro (1/3)' }} 
      />
      <Stack.Screen 
        name="cadastro/etapa2" 
        options={{ title: 'Cadastro (2/3)' }} 
      />
      <Stack.Screen 
        name="cadastro/etapa3" 
        options={{ title: 'Cadastro (3/3)' }} 
      />
      <Stack.Screen 
        name="cadastro/sucesso" 
        options={{ title: 'Finalizado', headerShown: false }} 
      />
    </Stack>
  );
}
