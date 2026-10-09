import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal do sistema Agência de Viagens.
 * Oferece um CRUD (Create, Read, Update, Delete) completo para cada tabela,
 * utilizando o padrão DAO com banco de dados SQLite.
 */
public class Main {

    /** Leitor de dados digitados no teclado. */
    private static final Scanner teclado = new Scanner(System.in);

    /** DAO de clientes. */
    private static final ClienteDAO clienteDAO = new ClienteDAO();
    /** DAO de destinos. */
    private static final DestinoDAO destinoDAO = new DestinoDAO();
    /** DAO de reservas. */
    private static final ReservaDAO reservaDAO = new ReservaDAO();

    /**
     * Construtor privado: a classe só possui métodos estáticos.
     */
    private Main() {
    }

    /**
     * Método principal. Cria as tabelas (se necessário) e abre o menu principal.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        try {
            Conexao.criarTabelas();
            menuPrincipal();
        } catch (SQLException e) {
            System.out.println("Erro de banco de dados: " + e.getMessage());
        }
    }

    /**
     * Exibe o menu principal até o usuário escolher sair.
     */
    private static void menuPrincipal() {
        int opcao;
        do {
            System.out.println("\n=== AGÊNCIA DE VIAGENS ===");
            System.out.println("1 - Clientes");
            System.out.println("2 - Destinos");
            System.out.println("3 - Reservas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuDestino();
                    break;
                case 3:
                    menuReserva();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Exibe o submenu de {@code Clientes} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuCliente() {
        int opcao;
        do {
            System.out.println("\n=== CLIENTES ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarCliente();
                    break;
                case 3:
                    atualizarCliente();
                    break;
                case 4:
                    excluirCliente();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Cliente (Create).
     */
    private static void cadastrarCliente() {
        System.out.println("\n--- Cadastrar Cliente ---");
        Cliente cliente = new Cliente();
        cliente.setNome(lerTexto("Nome"));

        try {
            clienteDAO.inserir(cliente);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Cliente (Read).
     */
    private static void listarCliente() {
        try {
            List<Cliente> lista = clienteDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Cliente cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Cliente ---");
            for (Cliente item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Cliente (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarCliente() {
        listarCliente();
        System.out.println("\n--- Atualizar Cliente ---");
        int idCliente = lerInt("ID do cliente");

        try {
            Cliente cliente = clienteDAO.buscarPorId(idCliente);
            if (cliente == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            cliente.setNome(lerTextoOpcional("Nome", cliente.getNome()));
            if (clienteDAO.atualizar(cliente)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Cliente (Delete).
     */
    private static void excluirCliente() {
        listarCliente();
        System.out.println("\n--- Excluir Cliente ---");
        int idCliente = lerInt("ID do cliente");

        try {
            if (clienteDAO.excluir(idCliente)) {
                System.out.println("Excluído com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível excluir (existem registros ligados a este?): " + ex.getMessage());
        }
    }

    /**
     * Exibe o submenu de {@code Destinos} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuDestino() {
        int opcao;
        do {
            System.out.println("\n=== DESTINOS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarDestino();
                    break;
                case 2:
                    listarDestino();
                    break;
                case 3:
                    atualizarDestino();
                    break;
                case 4:
                    excluirDestino();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Destino (Create).
     */
    private static void cadastrarDestino() {
        System.out.println("\n--- Cadastrar Destino ---");
        Destino destino = new Destino();
        destino.setNomeDestino(lerTexto("Nome do destino"));
        destino.setPais(lerTexto("País"));

        try {
            destinoDAO.inserir(destino);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Destino (Read).
     */
    private static void listarDestino() {
        try {
            List<Destino> lista = destinoDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Destino cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Destino ---");
            for (Destino item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Destino (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarDestino() {
        listarDestino();
        System.out.println("\n--- Atualizar Destino ---");
        int idDestino = lerInt("ID do destino");

        try {
            Destino destino = destinoDAO.buscarPorId(idDestino);
            if (destino == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            destino.setNomeDestino(lerTextoOpcional("Nome do destino", destino.getNomeDestino()));
            destino.setPais(lerTextoOpcional("País", destino.getPais()));
            if (destinoDAO.atualizar(destino)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Destino (Delete).
     */
    private static void excluirDestino() {
        listarDestino();
        System.out.println("\n--- Excluir Destino ---");
        int idDestino = lerInt("ID do destino");

        try {
            if (destinoDAO.excluir(idDestino)) {
                System.out.println("Excluído com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível excluir (existem registros ligados a este?): " + ex.getMessage());
        }
    }

    /**
     * Exibe o submenu de {@code Reservas} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuReserva() {
        int opcao;
        do {
            System.out.println("\n=== RESERVAS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarReserva();
                    break;
                case 2:
                    listarReserva();
                    break;
                case 3:
                    atualizarReserva();
                    break;
                case 4:
                    excluirReserva();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Reserva (Create).
     */
    private static void cadastrarReserva() {
        System.out.println("\n--- Cadastrar Reserva ---");
        Reserva reserva = new Reserva();
        reserva.setIdCliente(lerInt("ID do cliente"));
        reserva.setIdDestino(lerInt("ID do destino"));
        reserva.setDataViagem(lerData("Data da viagem"));
        reserva.setValor(lerDouble("Valor (R$)"));

        try {
            reservaDAO.inserir(reserva);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Reserva (Read).
     */
    private static void listarReserva() {
        try {
            List<Reserva> lista = reservaDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Reserva cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Reserva ---");
            for (Reserva item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Reserva (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarReserva() {
        listarReserva();
        System.out.println("\n--- Atualizar Reserva ---");
        int idReserva = lerInt("ID da reserva");

        try {
            Reserva reserva = reservaDAO.buscarPorId(idReserva);
            if (reserva == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            reserva.setIdCliente(lerIntOpcional("ID do cliente", reserva.getIdCliente()));
            reserva.setIdDestino(lerIntOpcional("ID do destino", reserva.getIdDestino()));
            reserva.setDataViagem(lerDataOpcional("Data da viagem", reserva.getDataViagem()));
            reserva.setValor(lerDoubleOpcional("Valor (R$)", reserva.getValor()));
            if (reservaDAO.atualizar(reserva)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Reserva (Delete).
     */
    private static void excluirReserva() {
        listarReserva();
        System.out.println("\n--- Excluir Reserva ---");
        int idReserva = lerInt("ID da reserva");

        try {
            if (reservaDAO.excluir(idReserva)) {
                System.out.println("Excluído com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível excluir (existem registros ligados a este?): " + ex.getMessage());
        }
    }

    /**
     * Lê uma opção de menu.
     *
     * @return número digitado ou -1 se a entrada for inválida
     */
    private static int lerOpcao() {
        try {
            return Integer.parseInt(teclado.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Lê um texto obrigatório (repete até o usuário digitar algo).
     *
     * @param rotulo nome do campo exibido ao usuário
     * @return texto digitado
     */
    private static String lerTexto(String rotulo) {
        String valor;
        do {
            System.out.print(rotulo + ": ");
            valor = teclado.nextLine().trim();
        } while (valor.isEmpty());
        return valor;
    }

    /**
     * Lê um texto opcional. Se o usuário apenas apertar ENTER, retorna o valor atual.
     *
     * @param rotulo nome do campo exibido ao usuário
     * @param atual  valor atual do campo
     * @return novo valor digitado ou o valor atual
     */
    private static String lerTextoOpcional(String rotulo, String atual) {
        System.out.print(rotulo + " [" + atual + "]: ");
        String valor = teclado.nextLine().trim();
        return valor.isEmpty() ? atual : valor;
    }

    /**
     * Lê um número inteiro maior ou igual a zero (repete até ser válido).
     *
     * @param rotulo nome do campo exibido ao usuário
     * @return número digitado
     */
    private static int lerInt(String rotulo) {
        while (true) {
            System.out.print(rotulo + ": ");
            try {
                int n = Integer.parseInt(teclado.nextLine().trim());
                if (n >= 0) {
                    return n;
                }
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            System.out.println("Digite um número inteiro maior ou igual a zero.");
        }
    }

    /**
     * Lê um número inteiro opcional. ENTER mantém o valor atual.
     *
     * @param rotulo nome do campo exibido ao usuário
     * @param atual  valor atual do campo
     * @return novo valor digitado ou o valor atual
     */
    private static int lerIntOpcional(String rotulo, int atual) {
        while (true) {
            System.out.print(rotulo + " [" + atual + "]: ");
            String texto = teclado.nextLine().trim();
            if (texto.isEmpty()) {
                return atual;
            }
            try {
                int n = Integer.parseInt(texto);
                if (n >= 0) {
                    return n;
                }
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            System.out.println("Digite um número inteiro maior ou igual a zero.");
        }
    }

    /**
     * Lê um número decimal maior ou igual a zero (aceita vírgula ou ponto).
     *
     * @param rotulo nome do campo exibido ao usuário
     * @return número digitado
     */
    private static double lerDouble(String rotulo) {
        while (true) {
            System.out.print(rotulo + ": ");
            try {
                double n = Double.parseDouble(teclado.nextLine().trim().replace(',', '.'));
                if (n >= 0) {
                    return n;
                }
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            System.out.println("Digite um valor numérico maior ou igual a zero (ex.: 12.50).");
        }
    }

    /**
     * Lê um número decimal opcional. ENTER mantém o valor atual.
     *
     * @param rotulo nome do campo exibido ao usuário
     * @param atual  valor atual do campo
     * @return novo valor digitado ou o valor atual
     */
    private static double lerDoubleOpcional(String rotulo, double atual) {
        while (true) {
            System.out.print(rotulo + " [" + atual + "]: ");
            String texto = teclado.nextLine().trim().replace(',', '.');
            if (texto.isEmpty()) {
                return atual;
            }
            try {
                double n = Double.parseDouble(texto);
                if (n >= 0) {
                    return n;
                }
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            System.out.println("Digite um valor numérico maior ou igual a zero (ex.: 12.50).");
        }
    }

    /**
     * Lê uma data no formato AAAA-MM-DD (repete até ser válida).
     *
     * @param rotulo nome do campo exibido ao usuário
     * @return data digitada, em texto
     */
    private static String lerData(String rotulo) {
        while (true) {
            System.out.print(rotulo + " (AAAA-MM-DD): ");
            try {
                return LocalDate.parse(teclado.nextLine().trim()).toString();
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Exemplo: 2026-10-25");
            }
        }
    }

    /**
     * Lê uma data opcional no formato AAAA-MM-DD. ENTER mantém o valor atual.
     *
     * @param rotulo nome do campo exibido ao usuário
     * @param atual  valor atual do campo
     * @return nova data digitada ou o valor atual
     */
    private static String lerDataOpcional(String rotulo, String atual) {
        while (true) {
            System.out.print(rotulo + " (AAAA-MM-DD) [" + atual + "]: ");
            String texto = teclado.nextLine().trim();
            if (texto.isEmpty()) {
                return atual;
            }
            try {
                return LocalDate.parse(texto).toString();
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Exemplo: 2026-10-25");
            }
        }
    }
}
