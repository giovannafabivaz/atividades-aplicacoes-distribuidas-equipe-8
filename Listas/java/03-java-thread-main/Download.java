// Arquivo: Download.java
class Download extends Thread {
    private String nome;
    private int totalEtapas;

    public Download(String nome, int totalEtapas) {
        this.nome = nome;
        this.totalEtapas = totalEtapas;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= totalEtapas; i++) {
                System.out.println("Download " + nome + " baixando parte " + i + " de " + totalEtapas + "...");
                // Simula o tempo de 1 segundo para cada etapa
                Thread.sleep(1000); 
            }
            System.out.println("SUCESSO: Download " + nome + " finalizado!");
        } catch (InterruptedException e) {
            System.out.println("CRÍTICO: O download " + nome + " foi interrompido (tempo limite estourado).");
        }
    }
}