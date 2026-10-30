// Arquivo Cliente.java

import CalculadoraApp.Calculadora;
import CalculadoraApp.CalculadoraHelper;
import CalculadoraApp.Produto;

import org.omg.CORBA.ORB;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import java.util.Scanner;

public class Cliente {

  private static void exibirMenu() {
    System.out.println();
    System.out.println(
      "========== MENU CORBA =========="
    );
    System.out.println(
      "1 - Realizar operações matemáticas"
    );
    System.out.println(
      "2 - Listar produtos"
    );
    System.out.println(
      "3 - Alterar quantidade em estoque"
    );
    System.out.println(
      "4 - Alterar preço do produto"
    );
    System.out.println(
      "0 - Sair"
    );
    System.out.println(
      "================================"
    );
    System.out.print(
      "Escolha uma opção: "
    );
  }

  private static Calculadora conectarAoServidor(
    String[] args
  ) throws Exception {

    ORB orb = ORB.init(args, null);

    org.omg.CORBA.Object objetoNome =
      orb.resolve_initial_references(
        "NameService"
      );

    NamingContext contextoNome =
      NamingContextHelper.narrow(objetoNome);

    NameComponent componenteNome =
      new NameComponent(
        "Calculadora",
        ""
      );

    NameComponent[] nome = {
      componenteNome
    };

    org.omg.CORBA.Object referencia =
      contextoNome.resolve(nome);

    return CalculadoraHelper.narrow(
      referencia
    );
  }

  private static void realizarOperacoesMatematicas(
    Calculadora calculadora,
    Scanner scanner
  ) {
    System.out.print(
      "Digite o primeiro número: "
    );

    double numero1 = scanner.nextDouble();

    System.out.print(
      "Digite o segundo número: "
    );

    double numero2 = scanner.nextDouble();

    System.out.println();
    System.out.println(
      "Resultado da soma: "
      + calculadora.somar(numero1, numero2)
    );

    System.out.println(
      "Resultado da subtração: "
      + calculadora.subtrair(numero1, numero2)
    );

    System.out.println(
      "Resultado da multiplicação: "
      + calculadora.multiplicar(numero1, numero2)
    );

    if (numero2 == 0) {
      System.out.println(
        "Resultado da divisão: "
        + "não é possível dividir por zero."
      );
    } else {
      System.out.println(
        "Resultado da divisão: "
        + calculadora.dividir(numero1, numero2)
      );
    }
  }

  private static void listarProdutos(
    Calculadora calculadora
  ) {
    Produto[] produtos =
      calculadora.listarProdutos();

    System.out.println();
    System.out.println(
      "========== LISTA DE PRODUTOS =========="
    );

    if (produtos.length == 0) {
      System.out.println(
        "Nenhum produto cadastrado."
      );
      return;
    }

    for (Produto produto : produtos) {
      System.out.println(
        "Produto: " + produto.nome
      );

      System.out.println(
        "Quantidade em estoque: "
        + produto.quantidade
      );

      System.out.printf(
        "Preço: R$ %.2f%n",
        produto.preco
      );

      System.out.println(
        "---------------------------------------"
      );
    }
  }

  private static void alterarQuantidade(
    Calculadora calculadora,
    Scanner scanner
  ) {
    scanner.nextLine();

    System.out.print(
      "Digite o nome do produto: "
    );

    String nomeProduto =
      scanner.nextLine();

    System.out.print(
      "Digite a nova quantidade: "
    );

    int novaQuantidade =
      scanner.nextInt();

    boolean alterado =
      calculadora.alterarQuantidade(
        nomeProduto,
        novaQuantidade
      );

    if (alterado) {
      System.out.println(
        "Quantidade alterada com sucesso."
      );
    } else {
      System.out.println(
        "Não foi possível alterar a quantidade."
      );

      System.out.println(
        "Verifique o nome do produto e o valor informado."
      );
    }
  }

  private static void alterarPreco(
    Calculadora calculadora,
    Scanner scanner
  ) {
    scanner.nextLine();

    System.out.print(
      "Digite o nome do produto: "
    );

    String nomeProduto =
      scanner.nextLine();

    System.out.print(
      "Digite o novo preço: "
    );

    double novoPreco =
      scanner.nextDouble();

    boolean alterado =
      calculadora.alterarPreco(
        nomeProduto,
        novoPreco
      );

    if (alterado) {
      System.out.println(
        "Preço alterado com sucesso."
      );
    } else {
      System.out.println(
        "Não foi possível alterar o preço."
      );

      System.out.println(
        "Verifique o nome do produto e o valor informado."
      );
    }
  }

  public static void main(String[] args) {

    Scanner scanner =
      new Scanner(System.in);

    try {
      Calculadora calculadora =
        conectarAoServidor(args);

      System.out.println(
        "Cliente conectado ao servidor CORBA."
      );

      int opcao;

      do {
        exibirMenu();

        opcao = scanner.nextInt();

        switch (opcao) {
          case 1:
            realizarOperacoesMatematicas(
              calculadora,
              scanner
            );
            break;

          case 2:
            listarProdutos(calculadora);
            break;

          case 3:
            alterarQuantidade(
              calculadora,
              scanner
            );
            break;

          case 4:
            alterarPreco(
              calculadora,
              scanner
            );
            break;

          case 0:
            System.out.println(
              "Cliente encerrado."
            );
            break;

          default:
            System.out.println(
              "Opção inválida."
            );
        }

      } while (opcao != 0);

    } catch (Exception e) {
      System.err.println(
        "Erro no cliente CORBA:"
      );

      e.printStackTrace();

    } finally {
      scanner.close();
    }
  }
}

