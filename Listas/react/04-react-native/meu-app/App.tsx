import { ExpoRoot } from 'expo-router';
import { registerRootComponent } from 'expo';

export function App() {
  // @ts-ignore
  const ctx = require.context('./app');
  return <ExpoRoot context={ctx} />;
}

registerRootComponent(App);