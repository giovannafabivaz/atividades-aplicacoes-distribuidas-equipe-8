import { useState, useRef } from 'react';
import { View, Text, TouchableOpacity } from 'react-native';

// Importamos o objeto de estilos compartilhado:
import { styles } from '../../styles/styles';

export default function TesteContador() {
    let contadorVariavel = 0;
    const [contadorEstado, setContadorEstado] = useState<number>(0);
    const contadorRef = useRef(0);

    const mostrarValoresDosContadores = () => {
        console.log(`contadorVariavel: ${contadorVariavel}`);
        console.log(`contadorEstado: ${contadorEstado}`);
        console.log(`contadorRef.current: ${contadorRef.current}`);
        console.log("");
    };

    // Esses logs rodam TODA VEZ que o componente é atualizado (redesenhado) na tela:
    console.log("O componente foi atualizado (redesenhado) na tela!");
    mostrarValoresDosContadores();

    const atualizarContadorVariavel = () => {
        contadorVariavel++;
        mostrarValoresDosContadores();
    };

    const atualizarContadorEstado = () => {
        setContadorEstado(prev => prev + 1);
        mostrarValoresDosContadores();
    };

    const atualizarContadorRef = () => {
        contadorRef.current = contadorRef.current + 1;
        mostrarValoresDosContadores();
    };

    return (
        // Caixa branca externa que agrupa o exemplo:
        <View style={styles.caixa}>
            {/* Texto de cabeçalho do exemplo */}
            <Text style={styles.subtitulo}>Contadores</Text>

            <Text style={[styles.texto, styles.textoMaior]}>contadorVariavel: {contadorVariavel}</Text>
            <Text style={[styles.texto, styles.textoMaior]}>contadorEstado: {contadorEstado}</Text>
            <Text style={[styles.texto, styles.textoMaior]}>contadorRef.current: {contadorRef.current}</Text>

            <TouchableOpacity style={[styles.botaoAtivo, styles.configBotaoAtivo]} onPress={atualizarContadorVariavel}>
                <Text style={styles.textoBotaoAtivo}>Aumentar contadorVariavel</Text>
            </TouchableOpacity>

            <TouchableOpacity style={[styles.botaoAtivo, styles.configBotaoAtivo]} onPress={atualizarContadorEstado}>
                <Text style={styles.textoBotaoAtivo}>Aumentar contadorEstado</Text>
            </TouchableOpacity>
            
            <TouchableOpacity style={[styles.botaoAtivo, styles.configBotaoAtivo]} onPress={atualizarContadorRef}>
                <Text style={styles.textoBotaoAtivo}>Aumentar contadorRef.current</Text>
            </TouchableOpacity>
        </View>
    );
}