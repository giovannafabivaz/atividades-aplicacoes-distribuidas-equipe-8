class TarefaContador implements Runnable {
    private String nome;
    private String texto;

    public TarefaContador(String nome, String texto) {
        this.nome = nome;
        this.texto = texto;
    }

    @Override
    public void run() {
        // texto.length() devolve exatamente a quantidade de letras/espaços
        int quantidade = texto.length();
        System.out.println(nome + ": A string '" + texto + "' tem " + quantidade + " caracteres.");
        
        try {
            Thread.sleep(1000);[cite: 4]
        } catch (InterruptedException e) {}
        
        System.out.println(nome + " FINALIZADA!");[cite: 4]
    }
}