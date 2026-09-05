// Arquivo: Main.java
public class Main {
    public static void main(String[] args) {
        // Instancia as threads com os tamanhos definidos no requisito
        Download d1 = new Download("Arquivo_Pequeno", 2);
        Download d2 = new Download("Arquivo_Medio", 4);
        Download d3 = new Download("Arquivo_Grande", 8);

        System.out.println("Iniciando downloads paralelos...\n");

        // Dispara as threads simultaneamente
        d1.start();
        d2.start();
        d3.start();

        try {
            // A thread principal aguarda 5000 milissegundos
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Tempo limite atingido. Cancelando downloads pendentes... ---\n");
        
        // Verifica individualmente quem ainda está vivo
        if (d1.isAlive()) {
            d1.interrupt();
        }
        if (d2.isAlive()) {
            d2.interrupt();
        }
        if (d3.isAlive()) {
            d3.interrupt();
        }
    }
}