import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
  public static void main(String args[]) {
    try {
      Servico servico = new Servico();
      /*
       * O segundo parâmetro zero (0) indica o uso de uma porta anônima 
       * (ou porta dinâmica alocada pelo sistema operacional). O RMI (Remote 
       * Method Invocation) pede ao sistema operacional para escolher uma 
       * porta TCP livre e disponível de forma automática para escutar as 
       * chamadas dos clientes. 
       * A porta 0 define onde o objeto remoto (servico) vai rodar para receber 
       * os métodos. Ela não é a porta do serviço de registro (rmiregistry, 
       * que por padrão costuma ser a 1099.
       */
      Cumprimento stub = (Cumprimento) UnicastRemoteObject.exportObject(servico, 0);

      // Faz o bind do stub do objeto remoto no registry:
      Registry registry = LocateRegistry.getRegistry();
      registry.bind("ServiçoDeCumprimentoRemoto", stub);

      System.out.println("Servidor rodando com sucesso...");
    } catch (Exception e) {
      System.err.println("Erro no servidor: " + e.toString());
      e.printStackTrace();
    }
  }
}
