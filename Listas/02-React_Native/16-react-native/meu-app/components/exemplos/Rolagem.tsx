import { useState, useRef } from 'react';
import { View, Text, FlatList, TouchableOpacity } from 'react-native';

import { styles } from '../../styles/styles';

export default function Rolagem() {
    const [itens, setItens] = useState<string[]>(['Mensagem 1', 'Mensagem 2', 'Mensagem 3']);
    const flatListRef = useRef<FlatList>(null);

    const adicionarItem = () => {
        setItens((vetor) => [...vetor, `Mensagem ${vetor.length + 1}`]);

        // Aguarda para garantir que o item já foi renderizado na tela:
        setTimeout(() => {
            flatListRef.current?.scrollToEnd({ animated: true });
        }, 50);
    };

    return (
        <View style={[styles.caixa, styles.caixaRolagem]}>
            <FlatList
                ref={flatListRef}
                data={itens}
                keyExtractor={(item) => item}
                renderItem={({ item }) => <Text style={styles.item}>{item}</Text>}
                style={styles.caixaRolagem}
            />
            <TouchableOpacity
                style={[styles.botaoAtivo, styles.configBotaoAtivo]}
                onPress={adicionarItem}
            >
                <Text style={styles.textoBotaoAtivo}>Adicionar e Rolar pro Fim</Text>
            </TouchableOpacity>
        </View>
    );
}
