public class Main {
    public static void main(String[] args) {
        
        // Instancia a primeira thread para "Faturamento"[cite: 5]
        ProcessadorRelatorio threadFaturamento = new ProcessadorRelatorio("Faturamento");
        
        // Instancia a segunda thread para "Vendas"[cite: 5]
        ProcessadorRelatorio threadVendas = new ProcessadorRelatorio("Vendas");

        // Inicia as duas threads em paralelo usando .start()[cite: 5]
        threadFaturamento.start();
        threadVendas.start();
    }
}