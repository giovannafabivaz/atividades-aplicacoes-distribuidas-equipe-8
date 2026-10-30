class ProcessadorRelatorio extends Thread {

    public ProcessadorRelatorio(String nomeDepartamento) {
        this.setName(nomeDepartamento);
    }

    @Override
    public void run() {
        try {
            for (int pagina = 1; pagina <= 3; pagina++) {
                System.out.println("[" + this.getName() + "] Gerando página " + pagina + "...");
                Thread.sleep(500);
            }
            System.out.println("[" + this.getName() + "] Relatório finalizado!");
        } catch (InterruptedException e) {
            System.out.println("[" + this.getName() + "] Geração de relatório interrompida!");
        }
    }
}

public class Main {

    public static void main(String[] args) {
        ProcessadorRelatorio threadFaturamento = new ProcessadorRelatorio("Faturamento");
        ProcessadorRelatorio threadVendas = new ProcessadorRelatorio("Vendas");

        threadFaturamento.start();
        threadVendas.start();

        try {
            threadFaturamento.join();
            threadVendas.join();
        } catch (InterruptedException e) {
            System.err.println("A thread principal foi interrompida.");
        }

        System.out.println("\n[Sistema] Todos os relatórios foram processados com sucesso.");
    }
}