import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cumprimento extends Remote {

  int gerarNumero() throws RemoteException;

}