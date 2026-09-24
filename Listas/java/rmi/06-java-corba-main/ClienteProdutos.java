import ProdutosApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class ClienteProdutos {
  public static void main(String args[]) {
    try {
      ORB orb = ORB.init(args, null);

      org.omg.CORBA.Object objeto =
          orb.resolve_initial_references("NameService");

      NamingContext contextoNome =
          NamingContextHelper.narrow(objeto);

      NameComponent componenteNome =
          new NameComponent("Produtos", "");

      NameComponent nome[] = { componenteNome };

      Produtos produtos =
          ProdutosHelper.narrow(contextoNome.resolve(nome));

      Scanner scanner = new Scanner(System.in);

      int opcao;

      do {
        System.out.println("\n--- SISTEMA DE PRODUTOS ---");
        System.out.println("1 - Consultar produtos");
        System.out.println("2 - Alterar quantidade");
        System.out.println("3 - Alterar preço");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opção: ");

        opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
          Produto[] lista = produtos.listarProdutos();

          System.out.println("\n--- LISTA DE PRODUTOS ---");

          for (int i = 0; i < lista.length; i++) {
            System.out.println("Produto: " + lista[i].nome);
            System.out.println("Estoque: " + lista[i].quantidade);
            System.out.println("Preço: R$ " + lista[i].preco);
            System.out.println();
          }

        } else if (opcao == 2) {
          System.out.print("Digite o nome do produto: ");
          String nomeProduto = scanner.nextLine();

          System.out.print("Digite a nova quantidade: ");
          int quantidade = scanner.nextInt();
          scanner.nextLine();

          produtos.alterarQuantidade(nomeProduto, quantidade);

          System.out.println("Quantidade alterada com sucesso!");

        } else if (opcao == 3) {
          System.out.print("Digite o nome do produto: ");
          String nomeProduto = scanner.nextLine();

          System.out.print("Digite o novo preço: ");
          double preco = scanner.nextDouble();
          scanner.nextLine();

          produtos.alterarPreco(nomeProduto, preco);

          System.out.println("Preço alterado com sucesso!");

        } else if (opcao == 4) {
          System.out.println("Programa encerrado.");

        } else {
          System.out.println("Opção inválida!");
        }

      } while (opcao != 4);

      scanner.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}