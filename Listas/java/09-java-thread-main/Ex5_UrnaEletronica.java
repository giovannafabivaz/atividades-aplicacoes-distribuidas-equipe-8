import java.util.concurrent.locks.ReentrantLock;

public class Ex5_UrnaEletronica implements Runnable {
    private static int totalVotos = 0;
    private static ReentrantLock mutex = new ReentrantLock();

    @Override
    public void run() {
        int votosEnviados = 0;
        // Cada urna deve executar um loop para adicionar exatamente 100 votos
        while (votosEnviados < 100) {
            if (mutex.tryLock()) {
                try {
                    totalVotos++;
                    votosEnviados++;
                } finally {
                    mutex.unlock();
                }
            }
            // Se a thread não conseguir o lock de primeira, ela deve continuar tentando
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread u1 = new Thread(new Ex5_UrnaEletronica());
        Thread u2 = new Thread(new Ex5_UrnaEletronica());
        Thread u3 = new Thread(new Ex5_UrnaEletronica());

        u1.start(); u2.start(); u3.start();
        u1.join(); u2.join(); u3.join();

        System.out.println("Total acumulado de votos: " + totalVotos);
    }
}