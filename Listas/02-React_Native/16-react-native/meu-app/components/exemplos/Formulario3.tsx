import { useState, useRef } from 'react';
import { View, Text, TextInput, TouchableOpacity } from 'react-native';

// Importação do objeto de estilos compartilhado:
import { styles } from '../../styles/styles';

export default function Formulario() {
    /*
     * Criamos uma referência para o TextInput de nome. 
     * Indicamos que ela guarda um TextInput e inicia com null.
     */
    const nomeInputRef = useRef<TextInput>(null);

    /*
     * Criamos uma referência para o TextInput de e-mail. 
     * Indicamos que ela guarda um TextInput e inicia com null.
     */
    const emailInputRef = useRef<TextInput>(null);

    /*
     * Criamos uma referência para o TextInput de senha. 
     * Indicamos que ela guarda um TextInput e inicia com null.
     */
    const senhaInputRef = useRef<TextInput>(null);

    // useStates para os dados do formulário:
    const [nome, setNome] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [senha, setSenha] = useState<string>('');

    // useState para "simular" o envo dos dados para o servidor:
    const [enviado, setEnviado] = useState<boolean>(false);
    const [campoAtual, setCampoAtual] = useState<'nome' | 'email' | 'senha' | 'cadastro'>('nome');

    const avancarCampo = () => {
        if (enviado) {
            setEnviado(false);
            setCampoAtual('nome');
            nomeInputRef.current?.focus();
        } else if (campoAtual === 'nome') {
            setCampoAtual('email');
            emailInputRef.current?.focus();
        } else if (campoAtual === 'email') {
            setCampoAtual('senha');
            senhaInputRef.current?.focus();
        } else if (campoAtual === 'senha') {
            setCampoAtual('cadastro');
        } else {
            setCampoAtual('nome');
            // Retorna o foco para o primeiro campo:
            nomeInputRef.current?.focus();
        }
    };

    const tratarEnvio = () => {
        if (nome.trim() === '' || email.trim() === '' || senha.trim() === '') {
            console.log('Por favor, preencha todos os campos!');
            return;
        }

        setEnviado(true);
        console.log('Enviado: ', { nome, email, senha });

        // Limpa os campos do formulário:
        setNome('');
        setEmail('');
        setSenha('');
    };

    return (
        // Caixa branca externa que agrupa o exemplo:
        <View style={styles.caixa}>
            {/* Texto de cabeçalho do exemplo */}
            <Text style={styles.subtitulo}>Foco Automático em Inputs</Text>

            {/* Input de Nome (Primeiro Campo): */}
            <TextInput
                value={nome}
                onChangeText={(texto) => {
                    setNome(texto);
                    setEnviado(false);
                }}
                ref={nomeInputRef} // Vincula este campo à referência 'nomeInputRef'.
                style={styles.input} 
                placeholder="Nome (Pressione Avançar)" 
                onPressIn={() => setEnviado(false)}
                onFocus={() => setCampoAtual('nome')}
                returnKeyType="next" // Modifica o botão de conclusão do teclado do celular para "Avançar".
                // Evento disparado quando o usuário clica no botão "Avançar" do teclado:
                // Ele acessa a referência do email e dispara o método nativo focus() para abrir o teclado lá.
                onSubmitEditing={() => emailInputRef.current?.focus()}
                autoFocus
            />

            {/* Input de E-mail (Segundo Campo): */}
            <TextInput
                value={email}
                onChangeText={(texto) => {
                    setEmail(texto);
                    setEnviado(false);
                }}
                ref={emailInputRef} // Vincula este campo à referência 'emailInputRef'.
                style={styles.input} 
                placeholder="E-mail (Pressione Avançar)" 
                onPressIn={() => setEnviado(false)}
                onFocus={() => setCampoAtual('email')}
                keyboardType="email-address" // Configura o teclado para o formato de e-mail (com @ visível).
                returnKeyType="next" // Modifica o botão de conclusão do teclado para "Avançar".
                // Evento disparado ao avançar: move o cursor diretamente para o campo de senha.
                onSubmitEditing={() => senhaInputRef.current?.focus()}
            />

            {/* Input de Senha (Terceiro e Ùltimo Campo): */}
            <TextInput
                value={senha}
                onChangeText={(texto) => {
                    setSenha(texto);
                    setEnviado(false);
                }}
                ref={senhaInputRef} // Vincula este campo com a referência 'senhaInputRef'.
                style={styles.input} 
                placeholder="Senha (Concluir)" 
                onPressIn={() => setEnviado(false)}
                onFocus={() => setCampoAtual('senha')}
                secureTextEntry // Oculta os caracteres digitados substituindo por bolinhas por segurança.
                returnKeyType="done" // Modifica o botão de conclusão do teclado para "Concluído/Pronto".
            />

            {/* 
              * Botão de Submissão do Formulário: 
              * Se enviado for true, o botão muda de título.
              * OBS: TouchableOpacity não possui .focus().
              */}
            <TouchableOpacity
                style={[styles.botaoAtivo, styles.configBotaoAtivo, enviado && styles.botaoDesativado]}
                disabled={enviado}
                onPress={tratarEnvio}
            >
                <Text style={styles.textoBotaoAtivo}>
                    {enviado ? "Cadastro Enviado!" : "Enviar Cadastro"}
                </Text>
            </TouchableOpacity>

            <TouchableOpacity
                style={[styles.botaoAtivo, styles.configBotaoAtivo]}
                onPress={avancarCampo}
            >
                <Text style={styles.textoBotaoAtivo}>Avançar</Text>
            </TouchableOpacity>
        </View>
    );
}
