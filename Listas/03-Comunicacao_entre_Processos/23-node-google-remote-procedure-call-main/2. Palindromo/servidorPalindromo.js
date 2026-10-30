import grpc from '@grpc/grpc-js';
import protoLoader from '@grpc/proto-loader';
import readline from 'readline';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const caminhoProto = path.join(__dirname, 'login.proto');

const definicaoPacote = protoLoader.loadSync(caminhoProto, { keepCase: true });
const protoAutenticacao = grpc.loadPackageDefinition(definicaoPacote).servico_autenticacao;

// Utilitário de leitura do teclado
function lerEntrada(pergunta) {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });

    return new Promise((resolve) => {
        rl.question(pergunta, (resposta) => {
            rl.close();
            resolve(resposta);
        });
    });
}

async function iniciarCliente() {
    // Conecta na porta 50053
    const cliente = new protoAutenticacao.SistemaAutenticacao('localhost:50053', grpc.credentials.createInsecure());

    console.log('--- TELA DE AUTENTICAÇÃO ---');
    const usuario = await lerEntrada('Usuário: ');
    const senha = await lerEntrada('Senha: ');

    const credenciais = { usuario, senha };

    console.log('\nEnviando credenciais para o servidor...');

    cliente.Autenticar(credenciais, (erro, resposta) => {
        if (erro) {
            console.error('Falha na chamada RPC de autenticação:', erro);
            return;
        }

        console.log('--------------------------------------------------');
        if (resposta.sucesso) {
            console.log(`🔓 SUCESSO: ${resposta.mensagem}`);
        } else {
            console.log(`🔒 FALHA: ${resposta.mensagem}`);
        }
        console.log('--------------------------------------------------');
    });
}

iniciarCliente();