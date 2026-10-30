/*
Compilar: javac ServicoBackup.java
Executar: java ServicoBackup
*/
public class ServicoBackup implements Runnable {
    @Override
    public void run() {
        System.out.println("Iniciando rotina de backup...");
        try {
            for (int etapa = 1; etapa <= 5; etapa++) {
                System.out.println("Executando etapa " + etapa + " de 5...");
                Thread.sleep(1000);
            }
            System.out.println("-> SUCESSO: Backup concluído e salvo no servidor!");
        } catch (InterruptedException e) {
            System.out.println("-> CRÍTICO: O backup foi cancelado pelo utilizador! Limpando ficheiros temporários...");
            return;
        }
    }

    public static void main(String[] args) {
        ServicoBackup servico = new ServicoBackup();
        Thread threadBackup = new Thread(servico);
        threadBackup.start();

        try {
            long tempoToleranciaMs = 2000; // Altere para 7000 para testar o cenário de Sucesso
            Thread.sleep(tempoToleranciaMs);

            if (threadBackup.isAlive()) {
                System.out.println("\n[Main] Tempo limite atingido! Solicitando interrupção do backup...");
                threadBackup.interrupt();
            }
            threadBackup.join();
        } catch (InterruptedException e) {
            System.err.println("A thread principal foi interrompida inesperadamente.");
        }
        System.out.println("[Main] Processo finalizado.");
    }
}