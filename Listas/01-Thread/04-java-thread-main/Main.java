// Arquivo: Main.java
public class Main {
    public static void main(String[] args) {
        
        // Cria a nova Thread utilizando uma Expressão Lambda, como pedido no exercício
        Thread sensor = new Thread(() -> {
            try {
                // Loop de 5 iterações para coletar os dados de temperatura
                for (int i = 1; i <= 5; i++) {
                    System.out.println("[Sensor] Coletando dados de temperatura... Passada " + i + " de 5 etapas");
                    
                    // Pausa a thread do sensor por 800 milissegundos a cada iteração[cite: 3]
                    Thread.sleep(800);
                }
                // Exibe a mensagem de encerramento após o loop[cite: 3]
                System.out.println("[Sensor] Monitoramento encerrado com sucesso!");
                
            } catch (InterruptedException e) {
                System.out.println("Erro na thread do sensor: " + e.getMessage());
            }
        });

        // Inicia a thread do sensor para rodar em paralelo[cite: 3]
        sensor.start();

        // A thread principal (main) continua a execução imediatamente e imprime a inicialização da interface[cite: 3]
        System.out.println("[Painel] Interface gráfica iniciada e pronta para o usuário.");

        try {
            // A thread principal pausa por 1500 milissegundos (1,5 segundos)[cite: 3]
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("Erro na thread principal: " + e.getMessage());
        }

        // Após a pausa, a main imprime a ação do usuário[cite: 3]
        System.out.println("[Painel] Usuário navegando pelos menus...");
    }
}