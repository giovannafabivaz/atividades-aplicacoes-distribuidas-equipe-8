import { useState, useRef, useEffect } from 'react';
import { View, Text, TouchableOpacity } from 'react-native';

import { styles } from '../../styles/styles';

export default function Cronometro() {
    const [segundos, setSegundos] = useState<number>(0);

    /* 
     * O useRef armazena o ID do timer e não causa re-render ao mudar.
     * Usando ReturnType<typeof setInterval>, você diz ao TypeScript: 
     * "Pegue o tipo exato do que a função setInterval retorna neste ambiente".
     */
    const timerRef = useRef<ReturnType<typeof setInterval> | null>(null);

    useEffect(() => {
        console.log(`timerRef.current: ${timerRef.current}, Segundos: ${segundos}`);
    }, [segundos]);

    const iniciarCronometro = () => {
        if (timerRef.current !== null)
            return; // Evita múltiplos timers ativos.

        /*
         * A função setInterval retorna um identificador numérico único (um ID 
         * inteiro, como 1, 2, 3...). Quando você executa o setInterval, é criada 
         * uma tarefa em segundo plano. Para controlar essa tarefa, é necessário 
         * manter esse ID em uma lista interna. Esse ID é usado mais tarde para
         * destruir a tarefa com clearInterval. O useRef é ideal para armazenar esse ID,
         * pois ele persiste entre renderizações sem causar re-renderizações adicionais.
         */
        timerRef.current = setInterval(() => {
            setSegundos((valor) => valor + 1);
        }, 1000);
    };

    const pararCronometro = () => {
        if (timerRef.current !== null) {
            // Destruimos o timer ativo usando o ID armazenado na referência:
            clearInterval(timerRef.current);
            timerRef.current = null; // Reseta a referência.
        }
    };

    const limparCronometro = () => {
        if (timerRef.current !== null) {
            // Destruimos o timer ativo usando o ID armazenado na referência:
            clearInterval(timerRef.current);
        }
        timerRef.current = null; // Reseta a referência.
        setSegundos(0); // Reseta o contador de segundos.
    };

    return (
        <View style={styles.caixa}>
            <Text style={[styles.texto, styles.textoMaior]}>Tempo: {segundos}s</Text>
            <TouchableOpacity
                style={[styles.botaoAtivo, styles.configBotaoAtivo]}
                onPress={iniciarCronometro}
            >
                <Text style={styles.textoBotaoAtivo}>Iniciar</Text>
            </TouchableOpacity>
            <TouchableOpacity
                style={[styles.botaoAtivo, styles.configBotaoAtivo, styles.botaoParar]}
                onPress={pararCronometro}
            >
                <Text style={styles.textoBotaoAtivo}>Parar</Text>
            </TouchableOpacity>
            <TouchableOpacity
                style={[styles.botaoAtivo, styles.configBotaoAtivo, styles.botaoLimpar]}
                onPress={limparCronometro}
            >
                <Text style={styles.textoBotaoAtivo}>Limpar</Text>
            </TouchableOpacity>
        </View>
    );
}
