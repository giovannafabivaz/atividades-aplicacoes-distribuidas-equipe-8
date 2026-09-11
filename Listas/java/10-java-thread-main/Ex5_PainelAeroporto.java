class PainelAeroporto {

    private int passageiros = 0;
    private int bagagens = 0;

    private final Object travaPassageiros = new Object();
    private final Object travaBagagens = new Object();

    public void registrarCheckIn(String atendente) {

        synchronized (travaPassageiros) {

            passageiros++;

            System.out.println(
                "[CHECK-IN] " + atendente +
                " registrou um passageiro. Total: " +
                passageiros
            );

            System.out.flush();
        }
    }

    public void registrarBagagem(String esteira) {

        synchronized (travaBagagens) {

            bagagens++;

            System.out.println(
                "[BAGAGEM] " + esteira +
                " registrou uma bagagem. Total: " +
                bagagens
            );

            System.out.flush();
        }
    }
}

public class Ex5_PainelAeroporto {

    public static void main(String[] args) {

        PainelAeroporto painel =
            new PainelAeroporto();

        Thread atendimento = new Thread(() -> {

            for (int i = 1; i <= 3; i++) {
                painel.registrarCheckIn(
                    "Atendente " + i
                );
            }
        });

        Thread logistica = new Thread(() -> {

            for (int i = 1; i <= 3; i++) {
                painel.registrarBagagem(
                    "Esteira " + i
                );
            }
        });

        atendimento.start();
        logistica.start();
    }
}