import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculadoraServidor {
    public static void main(String[] args) {
        try {
            // Inicia o registro RMI na porta 1099 automaticamente
            try {
                LocateRegistry.createRegistry(1099);
            } catch (Exception e) {
                System.out.println("Registro RMI já está em execução.");
            }

            Calculadora calc = new Calculadora();
            Naming.rebind("CalculadoraRemota", calc);
            
            System.out.println("Servidor da Calculadora pronto e operando!");
        } catch (Exception e) {
            System.out.println("Erro no Servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
