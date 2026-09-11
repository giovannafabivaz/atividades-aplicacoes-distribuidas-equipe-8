class CaixaRegistradora {

    private double saldoCaixa = 100.0;

    private final Object travaSaldo = new Object();

    public void registrarVenda(double valor, String operador) {

        synchronized (travaSaldo) {

            saldoCaixa += valor;

            System.out.printf(
                "[%s] Venda de R$ %.2f registrada. Saldo: R$ %.2f%n",
                operador,
                valor,
                saldoCaixa
            );
        }
    }

    public void realizarSangria(double valor, String operador) {

        synchronized (travaSaldo) {

            saldoCaixa -= valor;

            System.out.printf(
                "[%s] Sangria de R$ %.2f realizada. Saldo: R$ %.2f%n",
                operador,
                valor,
                saldoCaixa
            );
        }
    }
}

public class Ex3_CaixaRegistradora {

    public static void main(String[] args) {

        CaixaRegistradora caixa = new CaixaRegistradora();

        Thread operador1 = new Thread(() -> {
            caixa.registrarVenda(50.0, "Operador 1");
        });

        Thread operador2 = new Thread(() -> {
            caixa.realizarSangria(30.0, "Operador 2");
        });

        Thread operador3 = new Thread(() -> {
            caixa.registrarVenda(80.0, "Operador 3");
        });

        operador1.start();
        operador2.start();
        operador3.start();
    }
}