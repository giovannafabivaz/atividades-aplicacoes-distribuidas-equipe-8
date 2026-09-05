class Download implements Runnable {

    private final String nome;
    private final int totalEtapas;

    public Download(String nome, int totalEtapas) {
        this.nome = nome;
        this.totalEtapas = totalEtapas;
    }

    @Override
    public void run() {
        System.out.println("Iniciando download: " + this.nome + " (Total: " + this.totalEtapas + " partes)...");

        try {
            for (int etapa = 1; etapa <= this.totalEtapas; etapa++) {
                System.out.println("Download [" + this.nome + "] baixando parte " + etapa + " de " + this.totalEtapas + "...");
                Thread.sleep(1000);
            }

            System.out.println("-> SUCESSO: Download [" + this.nome + "] concluído e salvo!");

        } catch (InterruptedException e) {
            System.out.println("-> CRÍTICO: Download [" + this.nome + "] cancelado por timeout! Excluindo partes incompletas...");
            return;
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Thread threadPequeno = new Thread(new Download("Arquivo_Pequeno", 2));
        Thread threadMedio = new Thread(new Download("Arquivo_Medio", 4));
        Thread threadGrande = new Thread(new Download("Arquivo_Grande", 8));

        threadPequeno.start();
        threadMedio.start();
        threadGrande.start();

        try {
            long tempoToleranciaMs = 5000;
            Thread.sleep(tempoToleranciaMs);

            System.out.println("\n[Main] Verificando downloads pendentes apos 5 segundos...\n");

            Thread[] downloads = { threadPequeno, threadMedio, threadGrande };

            for (Thread t : downloads) {
                if (t.isAlive()) {
                    t.interrupt();
                }
            }

            threadPequeno.join();
            threadMedio.join();
            threadGrande.join();

        } catch (InterruptedException e) {
            System.err.println("A thread principal foi interrompida.");
        }

        System.out.println("\n[Main] Gerenciador de downloads finalizado.");
    }
}