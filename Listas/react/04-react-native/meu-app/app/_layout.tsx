import { Slot } from 'expo-router';
import { ContadorProvider } from './context/ContadorContext';

export default function Layout() {
  return (
    <ContadorProvider>
      <Slot />
    </ContadorProvider>
  );
}