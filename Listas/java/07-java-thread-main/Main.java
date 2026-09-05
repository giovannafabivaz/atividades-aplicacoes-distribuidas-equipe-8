public class Main {
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("=== EXERCÍCIO 1: CONTA BANCÁRIA ===");
        ContaBancaria conta = new ContaBancaria();
        Thread t1 = new Thread(() -> conta.sacar(70, "Thread-1"));
        Thread t2 = new Thread(() -> conta.sacar(70, "Thread-2"));
        // A primeira deve sacar, a segunda deve esbarrar no tryLock
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("\n=== EXERCÍCIO 2: URNA ELETRÔNICA ===");
        // Instancia 3 threads rodando a mesma urna ao mesmo tempo
        Thread u1 = new Thread(new UrnaEletronica());
        Thread u2 = new Thread(new UrnaEletronica());
        Thread u3 = new Thread(new UrnaEletronica());
        u1.start(); u2.start(); u3.start();
        u1.join(); u2.join(); u3.join();
        // O resultado deve ser exatamente 300
        System.out.println("Total de votos apurados: " + UrnaEletronica.getTotalVotos());

        System.out.println("\n=== EXERCÍCIO 3: RESERVA DE CINEMA ===");
        Cinema cinema = new Cinema();
        // Ambas tentam comprar o assento 5 simultaneamente
        Thread c1 = new Thread(() -> cinema.reservarAssento(5, "Cliente A"));
        Thread c2 = new Thread(() -> cinema.reservarAssento(5, "Cliente B"));
        c1.start(); c2.start();
        c1.join(); c2.join();

        System.out.println("\n=== EXERCÍCIO 4: GERADOR DE ID ===");
        GeradorID gerador = new GeradorID();
        // Dispara 5 threads simultâneas pedindo um ID
        for (int i = 1; i <= 5; i++) {
            final String nome = "Thread-" + i;
            new Thread(() -> {
                System.out.println(nome + " obteve o ID: " + gerador.obterProximoId());
            }).start();
        }
        Thread.sleep(500); // Pequena pausa para aguardar os geradores terminarem as impressões no console

        System.out.println("\n=== EXERCÍCIO 5: GRAVADOR DE LOG ===");
        GravadorLog log = new GravadorLog();
        Thread l1 = new Thread(() -> log.escreverMensagem("Processo de Faturamento finalizado.", "Thread-X"));
        Thread l2 = new Thread(() -> log.escreverMensagem("Processo de Vendas iniciou.", "Thread-Y"));
        l1.start(); l2.start();
    }
}