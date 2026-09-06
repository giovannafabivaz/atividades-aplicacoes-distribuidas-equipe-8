import grpc from '@grpc/grpc-js';
import protoLoader from '@grpc/proto-loader';
import readline from 'readline';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const caminhoProto = path.join(__dirname, 'palindromo.proto');

const definicaoPacote = protoLoader.loadSync(caminhoProto, { keepCase: true });
const protoVerificador = grpc.loadPackageDefinition(definicaoPacote).verificador_texto;

// Leitor simples do terminal
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
    // Conecta na porta 50052
    const cliente = new protoVerificador.VerificadorTexto('localhost:50052', grpc.credentials.createInsecure());

    const textoInformado = await lerEntrada('Digite uma palavra ou frase para testar: ');

    console.log(`Enviando ao servidor: "${textoInformado}"...`);

    cliente.VerificarPalindromo({ texto: textoInformado }, (erro, resposta) => {
        if (erro) {
            console.error('Falha na chamada RPC:', erro);
            return;
        }

        console.log('--------------------------------------------------');
        if (resposta.ehPalindromo) {
            console.log(`✅ A expressão "${textoInformado}" É UM PALÍNDROMO!`);
        } else {
            console.log(`❌ A expressão "${textoInformado}" NÃO É UM PALÍNDROMO.`);
        }
        console.log('--------------------------------------------------');
    });
}

iniciarCliente();