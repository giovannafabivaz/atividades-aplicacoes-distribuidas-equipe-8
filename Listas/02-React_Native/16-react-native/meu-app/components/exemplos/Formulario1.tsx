import { useRef } from 'react';
import { View, Text, TextInput, TouchableOpacity } from 'react-native';

import { styles } from '../../styles/styles';

export default function Formulario1() {
    // Criamos a referência com valor inicial null:
    const inputRef = useRef<TextInput>(null);

    const tratarFoco = () => {
        // Acessamos o método .focus() do componente através da propriedade .current:
        inputRef.current?.focus();
    };

    return (
        <View style={styles.caixa}>
            <TextInput
                ref={inputRef} // Associamos a referência ao TextInput.
                style={styles.input}
                placeholder="Clique no botão para focar aqui."
            />

            <TouchableOpacity
                style={[styles.botaoAtivo, styles.configBotaoAtivo]}
                onPress={tratarFoco}
            >
                <Text style={styles.textoBotaoAtivo}>Focar no Input</Text>
            </TouchableOpacity>
        </View>
    );
}
