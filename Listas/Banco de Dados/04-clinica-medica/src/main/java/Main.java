import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal do sistema Clínica Médica.
 * Oferece um CRUD (Create, Read, Update, Delete) completo para cada tabela,
 * utilizando o padrão DAO com banco de dados SQLite.
 */
public class Main {

    /** Leitor de dados digitados no teclado. */
    private static final Scanner teclado = new Scanner(System.in);

    /** DAO de pacientes. */
    private static final PacienteDAO pacienteDAO = new PacienteDAO();
    /** DAO de médicos. */
    private static final MedicoDAO medicoDAO = new MedicoDAO();
    /** DAO de consultas. */
    private static final ConsultaDAO consultaDAO = new ConsultaDAO();

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
            System.out.println("\n=== CLÍNICA MÉDICA ===");
            System.out.println("1 - Pacientes");
            System.out.println("2 - Médicos");
            System.out.println("3 - Consultas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    menuPaciente();
                    break;
                case 2:
                    menuMedico();
                    break;
                case 3:
                    menuConsulta();
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
     * Exibe o submenu de {@code Pacientes} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuPaciente() {
        int opcao;
        do {
            System.out.println("\n=== PACIENTES ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarPaciente();
                    break;
                case 2:
                    listarPaciente();
                    break;
                case 3:
                    atualizarPaciente();
                    break;
                case 4:
                    excluirPaciente();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Paciente (Create).
     */
    private static void cadastrarPaciente() {
        System.out.println("\n--- Cadastrar Paciente ---");
        Paciente paciente = new Paciente();
        paciente.setNome(lerTexto("Nome"));

        try {
            pacienteDAO.inserir(paciente);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Paciente (Read).
     */
    private static void listarPaciente() {
        try {
            List<Paciente> lista = pacienteDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Paciente cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Paciente ---");
            for (Paciente item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Paciente (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarPaciente() {
        listarPaciente();
        System.out.println("\n--- Atualizar Paciente ---");
        int idPaciente = lerInt("ID do paciente");

        try {
            Paciente paciente = pacienteDAO.buscarPorId(idPaciente);
            if (paciente == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            paciente.setNome(lerTextoOpcional("Nome", paciente.getNome()));
            if (pacienteDAO.atualizar(paciente)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Paciente (Delete).
     */
    private static void excluirPaciente() {
        listarPaciente();
        System.out.println("\n--- Excluir Paciente ---");
        int idPaciente = lerInt("ID do paciente");

        try {
            if (pacienteDAO.excluir(idPaciente)) {
                System.out.println("Excluído com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível excluir (existem registros ligados a este?): " + ex.getMessage());
        }
    }

    /**
     * Exibe o submenu de {@code Médicos} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuMedico() {
        int opcao;
        do {
            System.out.println("\n=== MÉDICOS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarMedico();
                    break;
                case 2:
                    listarMedico();
                    break;
                case 3:
                    atualizarMedico();
                    break;
                case 4:
                    excluirMedico();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Médico (Create).
     */
    private static void cadastrarMedico() {
        System.out.println("\n--- Cadastrar Médico ---");
        Medico medico = new Medico();
        medico.setNome(lerTexto("Nome"));
        medico.setEspecialidade(lerTexto("Especialidade"));

        try {
            medicoDAO.inserir(medico);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Médico (Read).
     */
    private static void listarMedico() {
        try {
            List<Medico> lista = medicoDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Médico cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Médico ---");
            for (Medico item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Médico (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarMedico() {
        listarMedico();
        System.out.println("\n--- Atualizar Médico ---");
        int idMedico = lerInt("ID do médico");

        try {
            Medico medico = medicoDAO.buscarPorId(idMedico);
            if (medico == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            medico.setNome(lerTextoOpcional("Nome", medico.getNome()));
            medico.setEspecialidade(lerTextoOpcional("Especialidade", medico.getEspecialidade()));
            if (medicoDAO.atualizar(medico)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Médico (Delete).
     */
    private static void excluirMedico() {
        listarMedico();
        System.out.println("\n--- Excluir Médico ---");
        int idMedico = lerInt("ID do médico");

        try {
            if (medicoDAO.excluir(idMedico)) {
                System.out.println("Excluído com sucesso!");
            } else {
                System.out.println("Registro não encontrado.");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível excluir (existem registros ligados a este?): " + ex.getMessage());
        }
    }

    /**
     * Exibe o submenu de {@code Consultas} (cadastrar, listar, atualizar e excluir).
     */
    private static void menuConsulta() {
        int opcao;
        do {
            System.out.println("\n=== CONSULTAS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarConsulta();
                    break;
                case 2:
                    listarConsulta();
                    break;
                case 3:
                    atualizarConsulta();
                    break;
                case 4:
                    excluirConsulta();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    /**
     * Cadastra um novo registro de Consulta (Create).
     */
    private static void cadastrarConsulta() {
        System.out.println("\n--- Cadastrar Consulta ---");
        Consulta consulta = new Consulta();
        consulta.setIdPaciente(lerInt("ID do paciente"));
        consulta.setIdMedico(lerInt("ID do médico"));
        consulta.setDataConsulta(lerData("Data da consulta"));
        consulta.setValor(lerDouble("Valor (R$)"));

        try {
            consultaDAO.inserir(consulta);
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Lista todos os registros de Consulta (Read).
     */
    private static void listarConsulta() {
        try {
            List<Consulta> lista = consultaDAO.listar();
            if (lista.isEmpty()) {
                System.out.println("Nenhum registro de Consulta cadastrado.");
                return;
            }
            System.out.println("\n--- Registros de Consulta ---");
            for (Consulta item : lista) {
                System.out.println(item);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar: " + ex.getMessage());
        }
    }

    /**
     * Atualiza um registro de Consulta (Update).
     * Se o usuário apertar ENTER em um campo, o valor atual é mantido.
     */
    private static void atualizarConsulta() {
        listarConsulta();
        System.out.println("\n--- Atualizar Consulta ---");
        int idConsulta = lerInt("ID da consulta");

        try {
            Consulta consulta = consultaDAO.buscarPorId(idConsulta);
            if (consulta == null) {
                System.out.println("Registro não encontrado.");
                return;
            }
            System.out.println("Pressione ENTER para manter o valor atual.");
            consulta.setIdPaciente(lerIntOpcional("ID do paciente", consulta.getIdPaciente()));
            consulta.setIdMedico(lerIntOpcional("ID do médico", consulta.getIdMedico()));
            consulta.setDataConsulta(lerDataOpcional("Data da consulta", consulta.getDataConsulta()));
            consulta.setValor(lerDoubleOpcional("Valor (R$)", consulta.getValor()));
            if (consultaDAO.atualizar(consulta)) {
                System.out.println("Atualizado com sucesso!");
            } else {
                System.out.println("Nada foi atualizado.");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar (os ids informados existem?): " + ex.getMessage());
        }
    }

    /**
     * Exclui um registro de Consulta (Delete).
     */
    private static void excluirConsulta() {
        listarConsulta();
        System.out.println("\n--- Excluir Consulta ---");
        int idConsulta = lerInt("ID da consulta");

        try {
            if (consultaDAO.excluir(idConsulta)) {
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
