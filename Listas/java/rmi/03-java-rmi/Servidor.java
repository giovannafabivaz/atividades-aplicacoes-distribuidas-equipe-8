import  java.rmi.*;
import java.rmi.server.*;

public class Servidor {
  public static void main (String[] args) {  
    try {
      // Cria e registra o objeto remoto:
      Servico servico = new Servico();
      Naming.rebind ("ServiçoRemoto", servico);

      System.out.println ("Servidor operando!");
    } catch (Exception e) {
      System.err.println ("Erro no servidor: " + e.toString ()); 
      e.printStackTrace ();
    }
  }
}
 