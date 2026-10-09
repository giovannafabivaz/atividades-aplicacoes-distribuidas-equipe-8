import java.util.List;
import java.util.Scanner;

/**
 * Classe principal do sistema.
 */
public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        VeiculoDAO dao = new VeiculoDAO();

        dao.criarTabela();

        int opcao;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar veículo");
            System.out.println("2 - Consultar veículos");
            System.out.println("3 - Atualizar veículo");
            System.out.println("4 - Excluir veículo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:

                    Veiculo v = new Veiculo();

                    System.out.print("Marca: ");
                    v.setMarca(sc.nextLine());

                    System.out.print("Modelo: ");
                    v.setModelo(sc.nextLine());

                    System.out.print("Número do Chassi: ");
                    v.setNumeroChassi(sc.nextLine());

                    System.out.print("Placa: ");
                    v.setPlaca(sc.nextLine());

                    System.out.print("Cor: ");
                    v.setCor(sc.nextLine());

                    dao.inserir(v);

                    System.out.println("Veículo cadastrado com sucesso.");
                    break;

                case 2:

                    List<Veiculo> veiculos = dao.listar();

                    if (veiculos.isEmpty()) {
                        System.out.println("Nenhum veículo cadastrado.");
                    } else {
                        veiculos.forEach(System.out::println);
                    }

                    break;

                case 3:

                    Veiculo atualizar = new Veiculo();

                    System.out.print("ID do veículo: ");
                    atualizar.setId(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Nova marca: ");
                    atualizar.setMarca(sc.nextLine());

                    System.out.print("Novo modelo: ");
                    atualizar.setModelo(sc.nextLine());

                    System.out.print("Novo chassi: ");
                    atualizar.setNumeroChassi(sc.nextLine());

                    System.out.print("Nova placa: ");
                    atualizar.setPlaca(sc.nextLine());

                    System.out.print("Nova cor: ");
                    atualizar.setCor(sc.nextLine());

                    dao.atualizar(atualizar);

                    System.out.println("Veículo atualizado.");
                    break;

                case 4:

                    System.out.print("ID do veículo: ");
                    int id = sc.nextInt();

                    dao.excluir(id);

                    System.out.println("Veículo excluído.");
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }
}