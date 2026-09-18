// Arquivo Servidor.java

import CalculadoraApp.Calculadora;
import CalculadoraApp.Produto;
import CalculadoraApp._CalculadoraImplBase;

import org.omg.CORBA.BAD_PARAM;
import org.omg.CORBA.ORB;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import java.util.ArrayList;
import java.util.List;

class CalculadoraImpl extends _CalculadoraImplBase {

  private final List<Produto> produtos;

  public CalculadoraImpl() {
    produtos = new ArrayList<Produto>();

    produtos.add(
      new Produto(
        "Notebook",
        10,
        3500.00
      )
    );

    produtos.add(
      new Produto(
        "Mouse",
        50,
        80.00
      )
    );

    produtos.add(
      new Produto(
        "Teclado",
        30,
        150.00
      )
    );

    produtos.add(
      new Produto(
        "Monitor",
        15,
        900.00
      )
    );
  }

  @Override
  public double somar(
    double numero1,
    double numero2
  ) {
    return numero1 + numero2;
  }

  @Override
  public double subtrair(
    double numero1,
    double numero2
  ) {
    return numero1 - numero2;
  }

  @Override
  public double multiplicar(
    double numero1,
    double numero2
  ) {
    return numero1 * numero2;
  }

  @Override
  public double dividir(
    double numero1,
    double numero2
  ) {
    if (numero2 == 0) {
      throw new BAD_PARAM(
        "Não é possível dividir por zero."
      );
    }

    return numero1 / numero2;
  }

  @Override
  public synchronized Produto[] listarProdutos() {
    Produto[] resultado = new Produto[produtos.size()];

    for (int i = 0; i < produtos.size(); i++) {
      Produto produto = produtos.get(i);

      resultado[i] = new Produto(
        produto.nome,
        produto.quantidade,
        produto.preco
      );
    }

    return resultado;
  }

  @Override
  public synchronized boolean alterarQuantidade(
    String nomeProduto,
    int novaQuantidade
  ) {
    if (novaQuantidade < 0) {
      return false;
    }

    for (Produto produto : produtos) {
      if (produto.nome.equalsIgnoreCase(nomeProduto)) {
        produto.quantidade = novaQuantidade;
        return true;
      }
    }

    return false;
  }

  @Override
  public synchronized boolean alterarPreco(
    String nomeProduto,
    double novoPreco
  ) {
    if (novoPreco < 0) {
      return false;
    }

    for (Produto produto : produtos) {
      if (produto.nome.equalsIgnoreCase(nomeProduto)) {
        produto.preco = novoPreco;
        return true;
      }
    }

    return false;
  }
}

public class Servidor {

  public static void main(String[] args) {

    try {
      ORB orb = ORB.init(args, null);

      CalculadoraImpl objetoServidor =
        new CalculadoraImpl();

      orb.connect(objetoServidor);

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

      contextoNome.rebind(
        nome,
        objetoServidor
      );

      System.out.println(
        "Servidor CORBA iniciado."
      );

      System.out.println(
        "Serviço registrado com o nome: Calculadora"
      );

      System.out.println(
        "Aguardando requisições dos clientes..."
      );

      Object sincronizacao = new Object();

      synchronized (sincronizacao) {
        sincronizacao.wait();
      }

    } catch (Exception e) {
      System.err.println(
        "Erro no servidor CORBA:"
      );

      e.printStackTrace();
    }
  }
}

