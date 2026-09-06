// Importa a biblioteca principal do gRPC:
import grpc from '@grpc/grpc-js';
// Importa o carregador de arquivos de especificação (.proto):
import protoLoader from '@grpc/proto-loader';

// Carrega o arquivo do contrato de forma síncrona mantendo a grafia original das variáveis:
const definicaoPacote = protoLoader.loadSync('calculadora.proto', { keepCase: true });

// Extrai o objeto do serviço de dentro do pacote carregado:
const protoCalculadora = grpc.loadPackageDefinition(definicaoPacote).servico_calculadora;

// Função que implementa o procedimento remoto de Soma:
function somar(chamada, respostaCallback) {
    const { numero1, numero2 } = chamada.request;
    const totalSoma = numero1 + numero2;
    respostaCallback(null, { resultado: totalSoma });
}

// Função que implementa o procedimento remoto de Subtração:
function subtrair(chamada, respostaCallback) {
    const { numero1, numero2 } = chamada.request;
    const totalSubtracao = numero1 - numero2;
    respostaCallback(null, { resultado: totalSubtracao });
}

// Função que implementa o procedimento remoto de Multiplicação:
function multiplicar(chamada, respostaCallback) {
    const { numero1, numero2 } = chamada.request;
    const totalMultiplicacao = numero1 * numero2;
    respostaCallback(null, { resultado: totalMultiplicacao });
}

// Função principal que configura e inicializa o servidor de rede:
function iniciarServidor() {
    const servidor = new grpc.Server();
    
    // Vincula a interface do contrato .proto às funções JavaScript criadas acima:
    servidor.addService(protoCalculadora.ServicoCalculadora.service, { 
        Somar: somar,
        Subtrair: subtrair,
        Multiplicar: multiplicar
    });
    
    // Configura o servidor para rodar na porta 50051 sem chaves de criptografia:
    servidor.bindAsync('0.0.0.0:50051', grpc.ServerCredentials.createInsecure(), (erro, porta) => {
        if (erro) {
            console.error('Erro ao iniciar servidor:', erro);
            return;
        }
        console.log(`Servidor gRPC da Calculadora rodando com sucesso na porta ${porta}`);
    });
}

// Executa a função de inicialização
iniciarServidor();