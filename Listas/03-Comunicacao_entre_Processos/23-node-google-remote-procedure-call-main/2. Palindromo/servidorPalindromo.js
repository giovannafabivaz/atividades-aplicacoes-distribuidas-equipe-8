import grpc from '@grpc/grpc-js';
import protoLoader from '@grpc/proto-loader';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const caminhoProto = path.join(__dirname, 'palindromo.proto');

const definicaoPacote = protoLoader.loadSync(caminhoProto, { keepCase: true });
const protoVerificador = grpc.loadPackageDefinition(definicaoPacote).verificador_texto;

function normalizarTexto(texto) {
    return texto
        .normalize('NFD')
        .replace(/\p{M}/gu, '')
        .toLowerCase()
        .replace(/[^\p{L}\p{N}]/gu, '');
}

function verificarPalindromo(chamada, respostaCallback) {
    const texto = normalizarTexto(chamada.request.texto ?? '');
    const invertido = Array.from(texto).reverse().join('');

    respostaCallback(null, { ehPalindromo: texto === invertido });
}

const servidor = new grpc.Server();
servidor.addService(protoVerificador.VerificadorTexto.service, {
    VerificarPalindromo: verificarPalindromo
});

servidor.bindAsync('0.0.0.0:50052', grpc.ServerCredentials.createInsecure(), (erro, porta) => {
    if (erro) {
        console.error('Erro ao iniciar o servidor de palíndromo:', erro);
        return;
    }
    console.log(`Servidor gRPC de palíndromo rodando na porta ${porta}`);
});