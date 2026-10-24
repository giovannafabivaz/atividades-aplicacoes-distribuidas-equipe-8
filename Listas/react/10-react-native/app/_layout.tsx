
import { Stack } from 'expo-router';

export default function RootLayout() {
  return (
    <Stack>
      <Stack.Screen
        name="index"
        options={{ title: 'Loja de Roupas' }}
      />
      <Stack.Screen
        name="produto/[id]"
        options={{ title: 'Detalhes do Produto' }}
      />
      <Stack.Screen
        name="alunos"
        options={{ title: 'Alunos da Universidade' }}
      />
      <Stack.Screen
        name="aluno/[id]"
        options={{ title: 'Detalhes do Aluno' }}
      />
    </Stack>
  );
}
