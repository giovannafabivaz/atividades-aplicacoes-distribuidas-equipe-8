import grpc from '@grpc/grpc-js';
import protoLoader from '@grpc/proto-loader';
import path from 'path';
import { fileURLToPath } from 'url';

// Resolução do caminho do arquivo .proto
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const caminhoProto = path.join(__dirname, 'login.proto');

const definicaoPacote = protoLoader.loadSync(caminhoProto, { keepCase: true });
const protoAutenticacao = grpc.loadPackageDefinition(definicaoPacote).servico_autenticacao;

// Credenciais padrão para autenticação
const USUARIO_CORRETO = 'admin';
const SENHA_CORRETA = 'senha123';

// Implementação da lógica de autenticação
function autenticar(chamada, respostaCallback) {
    const { usuario, senha } = chamada.request;

    console.log(`[SERVIDOR] Tentativa de login recebida para o usuário: "${usuario}"`);

    if (usuario === USUARIO_CORRETO && senha === SENHA_CORRETA) {
        respostaCallback(null, {
            sucesso: true,
            mensagem: 'Acesso concedido! Bem-vindo ao sistema.'
        });
    } else {
        respostaCallback(null, {
            sucesso: false,
            mensagem: 'Credenciais inválidas. Usuário ou senha incorretos.'
        });
    }
}

function iniciarServidor() {
    const servidor = new grpc.Server();

    servidor.addService(protoAutenticacao.SistemaAutenticacao.service, {
        Autenticar: autenticar
    });

    // Porta 50053 para evitar conflitos de porta
    servidor.bindAsync('0.0.0.0:50053', grpc.ServerCredentials.createInsecure(), (erro, porta) => {
        if (erro) {
            console.error('Erro ao iniciar o servidor de login:', erro);
            return;
        }
        console.log(`Servidor de Autenticação gRPC rodando com sucesso na porta ${porta}`);
    });
}

iniciarServidor();