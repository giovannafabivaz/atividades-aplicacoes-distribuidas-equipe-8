import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal do sistema Farmácia.
 * Oferece um CRUD (Create, Read, Update, Delete) completo para cada tabela,
 * utilizando o padrão DAO com banco de dados SQLite.
 */
public class Main {

    /** Leitor de dados digitados no teclado. */
    private static final Scanner teclado = new Scanner(System.in);

    /** DAO de medicamentos. */
    private static final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    /** DAO de fornecedores. */
    private static final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    /** DAO de compras. */
    private static final CompraDAO compraDAO = new CompraDAO();

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
            System.out.println("\n=== FARMÁCIA ===");
            System.out.println("1 - Medicamentos");
            System.out.println("2 - Fornecedores");
            System.out.println("3 - Compras");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    menuMedicamento();
                    break;
                case 2:
                    menuFornecedor();
                    break;
                case 3:
                    menuCompra();
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
     * Exibe o submenu de {@code Medicamentos} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuMedicamento() {
        int opcao;
        do {
            System.out.println("\n=== MEDICAMENTOS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarMedicamento();
                    break;
                case 2:
                    listarMedicamento();
                    break;
                case 3:
                    atualizarMedicamento();
                    break;
                case 4:
                    excluirMedicamento();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Medicamento (Create).
     */
    private static void cadastrarMedicamento() {
        System.out.println("\n--- Cadastrar Medicamento ---");
        Medicamento medicamento = new Medicamento();
        medicamento.setNome(lerTexto("Nome"));
        medicamento.setPreco(lerDouble("Preço (R$)"));

        try {
            medicamentoDAO.inserir(medicamento);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Medicamento (Read).
     */
    private static void listarMedicamento() {
        try {
            List<Medicamento> lista = medicamentoDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Medicamento cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Medicamento ---");
            for (Medicamento item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Medicamento (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarMedicamento() {
        listarMedicamento();
        System.out.println("\n--- Atualizar Medicamento ---");
        int idMedicamento = lerInt("ID do medicamento");

        try {
            Medicamento medicamento = medicamentoDAO.buscarPorId(idMedicamento);
            if (medicamento == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            medicamento.setNome(lerTextoOpcional("Nome", medicamento.getNome()));
            medicamento.setPreco(lerDoubleOpcional("Preço (R$)", medicamento.getPreco()));
            if (medicamentoDAO.atualizar(medicamento)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Medicamento (Delete).
     */
    private static void excluirMedicamento() {
        listarMedicamento();
        System.out.println("\n--- Excluir Medicamento ---");
        int idMedicamento = lerInt("ID do medicamento");

        try {
            if (medicamentoDAO.excluir(idMedicamento)) {
                System.out.println("Excluído com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível excluir (existem registros ligados a este?): " + ex.getMessage());
        }
    }

    /**
     * Exibe o submenu de {@code Fornecedores} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuFornecedor() {
        int opcao;
        do {
            System.out.println("\n=== FORNECEDORES ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarFornecedor();
                    break;
                case 2:
                    listarFornecedor();
                    break;
                case 3:
                    atualizarFornecedor();
                    break;
                case 4:
                    excluirFornecedor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Fornecedor (Create).
     */
    private static void cadastrarFornecedor() {
        System.out.println("\n--- Cadastrar Fornecedor ---");
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(lerTexto("Nome"));
        fornecedor.setCidade(lerTexto("Cidade"));

        try {
            fornecedorDAO.inserir(fornecedor);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Fornecedor (Read).
     */
    private static void listarFornecedor() {
        try {
            List<Fornecedor> lista = fornecedorDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Fornecedor cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Fornecedor ---");
            for (Fornecedor item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Fornecedor (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarFornecedor() {
        listarFornecedor();
        System.out.println("\n--- Atualizar Fornecedor ---");
        int idFornecedor = lerInt("ID do fornecedor");

        try {
            Fornecedor fornecedor = fornecedorDAO.buscarPorId(idFornecedor);
            if (fornecedor == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            fornecedor.setNome(lerTextoOpcional("Nome", fornecedor.getNome()));
            fornecedor.setCidade(lerTextoOpcional("Cidade", fornecedor.getCidade()));
            if (fornecedorDAO.atualizar(fornecedor)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Fornecedor (Delete).
     */
    private static void excluirFornecedor() {
        listarFornecedor();
        System.out.println("\n--- Excluir Fornecedor ---");
        int idFornecedor = lerInt("ID do fornecedor");

        try {
            if (fornecedorDAO.excluir(idFornecedor)) {
                System.out.println("Excluído com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível excluir (existem registros ligados a este?): " + ex.getMessage());
        }
    }

    /**
     * Exibe o submenu de {@code Compras} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuCompra() {
        int opcao;
        do {
            System.out.println("\n=== COMPRAS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarCompra();
                    break;
                case 2:
                    listarCompra();
                    break;
                case 3:
                    atualizarCompra();
                    break;
                case 4:
                    excluirCompra();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Compra (Create).
     */
    private static void cadastrarCompra() {
        System.out.println("\n--- Cadastrar Compra ---");
        Compra compra = new Compra();
        compra.setIdMedicamento(lerInt("ID do medicamento"));
        compra.setIdFornecedor(lerInt("ID do fornecedor"));
        compra.setQuantidade(lerInt("Quantidade"));
        compra.setDataCompra(lerData("Data da compra"));

        try {
            compraDAO.inserir(compra);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Compra (Read).
     */
    private static void listarCompra() {
        try {
            List<Compra> lista = compraDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Compra cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Compra ---");
            for (Compra item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Compra (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarCompra() {
        listarCompra();
        System.out.println("\n--- Atualizar Compra ---");
        int idCompra = lerInt("ID da compra");

        try {
            Compra compra = compraDAO.buscarPorId(idCompra);
            if (compra == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            compra.setIdMedicamento(lerIntOpcional("ID do medicamento", compra.getIdMedicamento()));
            compra.setIdFornecedor(lerIntOpcional("ID do fornecedor", compra.getIdFornecedor()));
            compra.setQuantidade(lerIntOpcional("Quantidade", compra.getQuantidade()));
            compra.setDataCompra(lerDataOpcional("Data da compra", compra.getDataCompra()));
            if (compraDAO.atualizar(compra)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Compra (Delete).
     */
    private static void excluirCompra() {
        listarCompra();
        System.out.println("\n--- Excluir Compra ---");
        int idCompra = lerInt("ID da compra");

        try {
            if (compraDAO.excluir(idCompra)) {
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
