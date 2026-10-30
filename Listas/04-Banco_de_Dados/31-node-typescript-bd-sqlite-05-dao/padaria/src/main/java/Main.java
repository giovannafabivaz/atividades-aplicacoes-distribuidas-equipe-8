import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal do sistema Padaria.
 * Oferece um CRUD (Create, Read, Update, Delete) completo para cada tabela,
 * utilizando o padrão DAO com banco de dados SQLite.
 */
public class Main {

    /** Leitor de dados digitados no teclado. */
    private static final Scanner teclado = new Scanner(System.in);

    /** DAO de clientes. */
    private static final ClienteDAO clienteDAO = new ClienteDAO();
    /** DAO de produtos. */
    private static final ProdutoDAO produtoDAO = new ProdutoDAO();
    /** DAO de pedidos. */
    private static final PedidoDAO pedidoDAO = new PedidoDAO();
    /** DAO de itens do pedido. */
    private static final ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();

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
            System.out.println("\n=== PADARIA ===");
            System.out.println("1 - Clientes");
            System.out.println("2 - Produtos");
            System.out.println("3 - Pedidos");
            System.out.println("4 - Itens do pedido");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuProduto();
                    break;
                case 3:
                    menuPedido();
                    break;
                case 4:
                    menuItemPedido();
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
     * Exibe o submenu de {@code Produtos} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuProduto() {
        int opcao;
        do {
            System.out.println("\n=== PRODUTOS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProduto();
                    break;
                case 3:
                    atualizarProduto();
                    break;
                case 4:
                    excluirProduto();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Produto (Create).
     */
    private static void cadastrarProduto() {
        System.out.println("\n--- Cadastrar Produto ---");
        Produto produto = new Produto();
        produto.setNome(lerTexto("Nome"));
        produto.setPreco(lerDouble("Preço (R$)"));

        try {
            produtoDAO.inserir(produto);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Produto (Read).
     */
    private static void listarProduto() {
        try {
            List<Produto> lista = produtoDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Produto cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Produto ---");
            for (Produto item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Produto (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarProduto() {
        listarProduto();
        System.out.println("\n--- Atualizar Produto ---");
        int idProduto = lerInt("ID do produto");

        try {
            Produto produto = produtoDAO.buscarPorId(idProduto);
            if (produto == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            produto.setNome(lerTextoOpcional("Nome", produto.getNome()));
            produto.setPreco(lerDoubleOpcional("Preço (R$)", produto.getPreco()));
            if (produtoDAO.atualizar(produto)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Produto (Delete).
     */
    private static void excluirProduto() {
        listarProduto();
        System.out.println("\n--- Excluir Produto ---");
        int idProduto = lerInt("ID do produto");

        try {
            if (produtoDAO.excluir(idProduto)) {
                System.out.println("Excluído com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível excluir (existem registros ligados a este?): " + ex.getMessage());
        }
    }

    /**
     * Exibe o submenu de {@code Pedidos} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuPedido() {
        int opcao;
        do {
            System.out.println("\n=== PEDIDOS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarPedido();
                    break;
                case 2:
                    listarPedido();
                    break;
                case 3:
                    atualizarPedido();
                    break;
                case 4:
                    excluirPedido();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Pedido (Create).
     */
    private static void cadastrarPedido() {
        System.out.println("\n--- Cadastrar Pedido ---");
        Pedido pedido = new Pedido();
        pedido.setIdCliente(lerInt("ID do cliente"));
        pedido.setDataPedido(lerData("Data do pedido"));
        pedido.setTotal(lerDouble("Total (R$)"));

        try {
            pedidoDAO.inserir(pedido);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Pedido (Read).
     */
    private static void listarPedido() {
        try {
            List<Pedido> lista = pedidoDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Pedido cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Pedido ---");
            for (Pedido item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Pedido (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarPedido() {
        listarPedido();
        System.out.println("\n--- Atualizar Pedido ---");
        int idPedido = lerInt("ID do pedido");

        try {
            Pedido pedido = pedidoDAO.buscarPorId(idPedido);
            if (pedido == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            pedido.setIdCliente(lerIntOpcional("ID do cliente", pedido.getIdCliente()));
            pedido.setDataPedido(lerDataOpcional("Data do pedido", pedido.getDataPedido()));
            pedido.setTotal(lerDoubleOpcional("Total (R$)", pedido.getTotal()));
            if (pedidoDAO.atualizar(pedido)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Pedido (Delete).
     */
    private static void excluirPedido() {
        listarPedido();
        System.out.println("\n--- Excluir Pedido ---");
        int idPedido = lerInt("ID do pedido");

        try {
            if (pedidoDAO.excluir(idPedido)) {
                System.out.println("Excluído com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível excluir (existem registros ligados a este?): " + ex.getMessage());
        }
    }

    /**
     * Exibe o submenu de {@code Itens do pedido} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuItemPedido() {
        int opcao;
        do {
            System.out.println("\n=== ITENS DO PEDIDO ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarItemPedido();
                    break;
                case 2:
                    listarItemPedido();
                    break;
                case 3:
                    atualizarItemPedido();
                    break;
                case 4:
                    excluirItemPedido();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Item do pedido (Create).
     */
    private static void cadastrarItemPedido() {
        System.out.println("\n--- Cadastrar Item do pedido ---");
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setIdPedido(lerInt("ID do pedido"));
        itemPedido.setIdProduto(lerInt("ID do produto"));
        itemPedido.setQuantidade(lerInt("Quantidade"));

        try {
            itemPedidoDAO.inserir(itemPedido);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Item do pedido (Read).
     */
    private static void listarItemPedido() {
        try {
            List<ItemPedido> lista = itemPedidoDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Item do pedido cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Item do pedido ---");
            for (ItemPedido item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Item do pedido (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarItemPedido() {
        listarItemPedido();
        System.out.println("\n--- Atualizar Item do pedido ---");
        int idPedido = lerInt("ID do pedido");
        int idProduto = lerInt("ID do produto");

        try {
            ItemPedido itemPedido = itemPedidoDAO.buscarPorId(idPedido, idProduto);
            if (itemPedido == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            itemPedido.setQuantidade(lerIntOpcional("Quantidade", itemPedido.getQuantidade()));
            if (itemPedidoDAO.atualizar(itemPedido)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Item do pedido (Delete).
     */
    private static void excluirItemPedido() {
        listarItemPedido();
        System.out.println("\n--- Excluir Item do pedido ---");
        int idPedido = lerInt("ID do pedido");
        int idProduto = lerInt("ID do produto");

        try {
            if (itemPedidoDAO.excluir(idPedido, idProduto)) {
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
