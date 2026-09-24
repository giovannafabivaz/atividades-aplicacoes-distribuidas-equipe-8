// Arquivo Servidor.java

import CumprimentadorApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

class CumprimentadorImpl extends _CumprimentadorImplBase {
  public String cumprimentar() {
    return "\nOlá, mundo!!\n";
  }
}

public class Servidor {
  public static void main(String args[]) {
    try {
      /*
       * Cria e inicializa o ORB. O segundo argumento é um objeto Properties 
       * com configurações adicionais do ORB. O null significa que nenhuma 
       * propriedade extra foi informada. O ORB usará apenas os argumentos 
       * da linha de comando (args: host e port).
       */
      ORB orb = ORB.init(args, null);

      // Cria e registra o objeto servidor:
      CumprimentadorImpl cumprimentador = new CumprimentadorImpl();
      orb.connect(cumprimentador);

      /* 
       * Obtém uma referência para o root naming context:
       * O método abaixo não busca qualquer nome arbitrário do projeto. 
       * Ele consulta uma chave interna do ORB e a chave correta do serviço 
       * de nomes CORBA é: "NameService".
       */
      org.omg.CORBA.Object objeto = orb.resolve_initial_references("NameService");
      NamingContext contextoNome = NamingContextHelper.narrow(objeto);

      /*
       * Associa a Object Reference em Naming:
       * Resolve o Object Reference in Naming. O segundo parâmetro é a
       * categoria ou tipo (kind) do nome. A string vazia significa que
       * nenhum tipo foi especificado.
       */
      NameComponent componenteNome = new NameComponent("Cumprimentador", "");
      NameComponent nome[] = { componenteNome };
      contextoNome.rebind(nome, cumprimentador);

      // Espera requisições dos clientes:
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