import java.rmi.Naming;

public class CalculadoraCliente {
    public static void main(String[] args) {
        try {
            // Procura o serviço no servidor local
            CalculadoraInterface calc = (CalculadoraInterface) Naming.lookup("rmi://localhost/CalculadoraRemota");

            System.out.println("--- Testando Calculadora ---");
            
            double a = 10.0;
            double b = 5.0;

            System.out.println(a + " + " + b + " = " + calc.somar(a, b));
            System.out.println(a + " - " + b + " = " + calc.subtrair(a, b));
            System.out.println(a + " * " + b + " = " + calc.multiplicar(a, b));
            System.out.println(a + " / " + b + " = " + calc.dividir(a, b));

        } catch (Exception e) {
            System.out.println("Erro no Cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
