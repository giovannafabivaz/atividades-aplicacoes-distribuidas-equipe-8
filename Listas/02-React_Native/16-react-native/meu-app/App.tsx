import { StatusBar } from 'expo-status-bar';
import Principal from './components/Principal';

export default function App() {
  return (
    <> {/* Fragment: fragmento para permitir o retorno de múltiplos elementos sem criar uma View extra.*/}
      <Principal />
      <StatusBar style="auto" />
    </>
  );
}
