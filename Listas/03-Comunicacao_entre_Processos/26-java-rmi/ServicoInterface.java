import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServicoInterface extends Remote {
  Object executarTarefa (Parametro parametro) throws RemoteException;
}
