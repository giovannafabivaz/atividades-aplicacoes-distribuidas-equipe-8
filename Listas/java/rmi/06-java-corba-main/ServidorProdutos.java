import ProdutosApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

class ProdutosImpl extends _ProdutosImplBase {

  private Produto[] produtos = {
    new Produto("Teclado", 10, 150.00),
    new Produto("Mouse", 20, 80.00),
    new Produto("Monitor", 5, 900.00)
  };

  public Produto[] listarProdutos() {
    return produtos;
  }

  public void alterarQuantidade(String nome, int quantidade) {
    for (int i = 0; i < produtos.length; i++) {
      if (produtos[i].nome.equalsIgnoreCase(nome)) {
        produtos[i].quantidade = quantidade;
      }
    }
  }

  public void alterarPreco(String nome, double preco) {
    for (int i = 0; i < produtos.length; i++) {
      if (produtos[i].nome.equalsIgnoreCase(nome)) {
        produtos[i].preco = preco;
      }
    }
  }
}

public class ServidorProdutos {
  public static void main(String args[]) {
    try {
      ORB orb = ORB.init(args, null);

      ProdutosImpl produtos = new ProdutosImpl();
      orb.connect(produtos);

      org.omg.CORBA.Object objeto =
          orb.resolve_initial_references("NameService");

      NamingContext contextoNome =
          NamingContextHelper.narrow(objeto);

      NameComponent componenteNome =
          new NameComponent("Produtos", "");

      NameComponent nome[] = { componenteNome };

      contextoNome.rebind(nome, produtos);

      java.lang.Object sincronizacao = new java.lang.Object();

      synchronized(sincronizacao) {
        sincronizacao.wait();
      }

    } catch(Exception e) {
      System.err.println("ERRO: " + e);
      e.printStackTrace(System.out);
    }
  }
}