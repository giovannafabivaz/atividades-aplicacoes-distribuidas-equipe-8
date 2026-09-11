import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servico extends UnicastRemoteObject implements ServicoInterface {
  public Servico () throws RemoteException {
    super ();
  }

  public Object executarTarefa (Parametro parametro) throws RemoteException {
    return 2 * (Double) parametro.executar();
  }
}
