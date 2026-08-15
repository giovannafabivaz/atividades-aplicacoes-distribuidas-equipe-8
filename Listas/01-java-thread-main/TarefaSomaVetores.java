class TarefaSomaVetores implements Runnable {
    private String nome;
    private int[] vetorA;
    private int[] vetorB;

    public TarefaSomaVetores(String nome, int[] vetorA, int[] vetorB) {
        this.nome = nome;
        this.vetorA = vetorA;
        this.vetorB = vetorB;
    }

    @Override
    public void run() {
        // Percorremos os elementos dos vetores usando um laço
        for (int i = 0; i < vetorA.length; i++) {
            int soma = vetorA[i] + vetorB[i];
            System.out.println(nome + " - Índice [" + i + "]: " + vetorA[i] + " + " + vetorB[i] + " = " + soma);
            
            try {
                Thread.sleep(1000);[cite: 4]
            } catch (InterruptedException e) {}
        }
        System.out.println(nome + " FINALIZADA!");[cite: 4]
    }
}