/*
Compilar: javac PainelTelemetria.java
Executar: java PainelTelemetria
*/
public class PainelTelemetria {
    public static void main(String[] args) {
        Thread sensorThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("[Sensor] A recolher dados de temperatura (" + i + "/5)...");
                    Thread.sleep(800);
                }
                System.out.println("[Sensor] Recolha finalizada.");
            } catch (InterruptedException e) {
                System.out.println("[Sensor] Interrompido.");
            }
        });

        sensorThread.start();

        try {
            System.out.println("[Interface] A iniciar painel de controlo...");
            Thread.sleep(1500);
            System.out.println("[Interface] O utilizador está a navegar no painel...");
            sensorThread.join();
        } catch (InterruptedException e) {
            System.err.println("Erro na thread principal.");
        }
        
        System.out.println("[Sistema] Processo de telemetria concluído.");
    }
}