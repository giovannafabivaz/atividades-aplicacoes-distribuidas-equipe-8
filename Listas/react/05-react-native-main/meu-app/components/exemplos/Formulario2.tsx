import { useRef } from 'react';
import { View, TextInput } from 'react-native';

import { styles } from '../../styles/styles';

export default function Formulario2() {
    const segundoInputRef = useRef<TextInput>(null);

    const tratarAlteracaoDeTexto = (text: string) => {
        // Se o usuário digitou 4 caracteres (ex: ano de nascimento), move para o próximo campo:
        if (text.length === 4) {
            segundoInputRef.current?.focus();
        }
    };

    return (
        <View style={styles.caixa}>
            <TextInput
                style={styles.input}
                placeholder="Informe o ano (com 4 dígitos)"
                autoFocus
                maxLength={4}
                keyboardType="numeric"
                onChangeText={tratarAlteracaoDeTexto}
            />
            <TextInput
                ref={segundoInputRef}
                style={styles.input}
                placeholder="Informe o mês (com 2 dígitos)"
                maxLength={2}
                keyboardType="numeric"
            />
        </View>
    );
}
