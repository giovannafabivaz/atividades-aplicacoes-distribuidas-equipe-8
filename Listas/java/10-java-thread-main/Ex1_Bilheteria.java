class Bilheteria {

    private int ingressosDisponiveis = 10;

    public synchronized void comprarIngresso(String nomeCliente) {

        if (ingressosDisponiveis > 0) {
            ingressosDisponiveis--;

            System.out.println(
                "[Sucesso] " + nomeCliente +
                " comprou um ingresso. Restam: " +
                ingressosDisponiveis
            );
        } else {
            System.out.println(
                "[Esgotado] Não há mais ingressos disponíveis para " +
                nomeCliente
            );
        }
    }
}

public class Ex1_Bilheteria {

    public static void main(String[] args) {

        Bilheteria bilheteria = new Bilheteria();

        for (int i = 1; i <= 12; i++) {

            String nomeCliente = "Cliente " + i;

            Thread cliente = new Thread(() -> {
                bilheteria.comprarIngresso(nomeCliente);
            });

            cliente.start();
        }
    }
}