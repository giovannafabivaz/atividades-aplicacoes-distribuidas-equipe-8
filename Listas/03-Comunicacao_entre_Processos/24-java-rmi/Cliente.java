import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
  public static void main(String[] args) {
    String host = (args.length < 1) ? null : args[0];

    try {
      Registry registry = LocateRegistry.getRegistry(host);
      Cumprimento stub = (Cumprimento) registry.lookup("ServiçoDeCumprimentoRemoto");

      int resposta = stub.gerarNumero();

      System.out.println("Número aleatório: " + resposta);
    } catch (Exception e) {
      System.err.println("Erro no cliente: " + e.toString());
      e.printStackTrace();
    }
  }
}