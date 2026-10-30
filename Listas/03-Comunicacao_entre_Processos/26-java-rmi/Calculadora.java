import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Calculadora extends UnicastRemoteObject implements CalculadoraInterface {
    
    // O construtor precisa lançar RemoteException
    public Calculadora() throws RemoteException {
        super();
    }

    @Override
    public double somar(double a, double b) throws RemoteException {
        System.out.println("Servidor: Somando " + a + " + " + b);
        return a + b;
    }

    @Override
    public double subtrair(double a, double b) throws RemoteException {
        System.out.println("Servidor: Subtraindo " + a + " - " + b);
        return a - b;
    }

    @Override
    public double multiplicar(double a, double b) throws RemoteException {
        System.out.println("Servidor: Multiplicando " + a + " * " + b);
        return a * b;
    }

    @Override
    public double dividir(double a, double b) throws RemoteException {
        if (b == 0) {
            throw new RemoteException("Erro: Divisão por zero!");
        }
        System.out.println("Servidor: Dividindo " + a + " / " + b);
        return a / b;
    }
}
