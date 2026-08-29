import { useEffect, useState } from 'react';
import { View, Text, StyleSheet, FlatList, ActivityIndicator, TouchableOpacity } from 'react-native';

interface Notificacao {
  id: string;
  mensagem: string;
  lida: boolean;
}

const notificacoesSimuladas: Notificacao[] = [
  { id: '1', mensagem: 'Sua entrega saiu para entrega!', lida: false },
  { id: '2', mensagem: 'Você recebeu um novo e-mail.', lida: true },
  { id: '3', mensagem: 'Sua senha foi alterada com sucesso.', lida: false },
  { id: '4', mensagem: 'Promoção: 50% de desconto em eletrônicos!', lida: false },
];

export default function TelaDeNotificacoes() {
  const [notificacoes, setNotificacoes] = useState<Notificacao[]>([]);
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setNotificacoes(notificacoesSimuladas);
      setCarregando(false);
    }, 2000);

    return () => clearTimeout(timer);
  }, []);

  // Função para marcar como lida ao clicar
  const lerNotificacao = (id: string) => {
    setNotificacoes(prev => prev.map(n => 
      n.id === id ? { ...n, lida: true } : n // Corrigido: 'n' em vez de 't'
    ));
  };

  // Correção rápida no código acima:
  const marcarLida = (id: string) => {
    setNotificacoes(prev => prev.map(n => 
      n.id === id ? { ...n, lida: true } : n
    ));
  };

  const naoLidas = notificacoes.filter(n => !n.lida).length;

  if (carregando) {
    return (
      <View style={[styles.container, styles.centro]}>
        <ActivityIndicator size="large" color="#60a5fa" />
        <Text style={styles.textoCarregando}>Verificando notificações...</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Notificações</Text>
      
      {notificacoes.length > 0 ? (
        <>
          <Text style={styles.contador}>Você tem {naoLidas} notificações não lidas</Text>
          <FlatList
            data={notificacoes}
            keyExtractor={(item) => item.id}
            renderItem={({ item }) => (
              <TouchableOpacity 
                style={styles.notificacaoCard}
                onPress={() => marcarLida(item.id)}
                activeOpacity={0.7}
              >
                <Text style={[styles.mensagem, !item.lida && styles.naoLida]}>
                  {item.mensagem}
                </Text>
                {!item.lida && <View style={styles.pontoAzul} />}
              </TouchableOpacity>
            )}
          />
        </>
      ) : (
        <View style={styles.centro}>
          <Text style={styles.vazio}>Nenhuma notificação encontrada</Text>
        </View>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 20, backgroundColor: '#000000' },
  centro: { flex: 1, justifyContent: 'center', alignItems: 'center' },
  title: { fontSize: 28, fontWeight: 'bold', color: '#60a5fa', marginBottom: 10, marginTop: 40 },
  contador: { fontSize: 16, color: '#aaaaaa', marginBottom: 20 },
  textoCarregando: { color: '#ffffff', marginTop: 10 },
  notificacaoCard: { 
    padding: 18, 
    backgroundColor: '#111111', 
    borderRadius: 10, 
    marginBottom: 12,
    borderWidth: 1,
    borderColor: '#333333',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center'
  },
  mensagem: { fontSize: 16, color: '#888888', flex: 1 }, // Mensagens lidas ficam mais escuras
  naoLida: { fontWeight: 'bold', color: '#ffffff' }, // Não lidas ficam brancas e negrito
  pontoAzul: { width: 10, height: 10, borderRadius: 5, backgroundColor: '#60a5fa', marginLeft: 10 },
  vazio: { color: '#666666', fontSize: 18, textAlign: 'center' }
});
