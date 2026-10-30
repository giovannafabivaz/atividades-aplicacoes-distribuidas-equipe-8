package servidor;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import servico.Cumprimento;
import servico.Servico;

public class Servidor {
  public static void main(String args[]) {
    try {
      Servico servico = new Servico();
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
