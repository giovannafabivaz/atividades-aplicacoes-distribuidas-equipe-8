// Criamos a classe estendendo Thread como pedido
public class ProcessadorRelatorio extends Thread {
    
    // O construtor recebe o nome do departamento[cite: 5]
    public ProcessadorRelatorio(String nomeDepartamento) {
        // Usa setName para batizar a thread internamente no Java[cite: 5]
        this.setName(nomeDepartamento);
    }

    // Sobrescrevemos o método run para definir a tarefa da thread[cite: 5]
    @Override
    public void run() {
        try {
            // Loop para simular o processamento de 3 páginas[cite: 5]
            for (int i = 1; i <= 3; i++) {
                // this.getName() resgata o nome que setamos no construtor
                System.out.println("[" + this.getName() + "] Gerando página " + i + "...");
                
                // Simula o tempo de escrita com pausa de 500ms[cite: 5]
                Thread.sleep(500); 
            }
            // Exibe a mensagem ao terminar o loop[cite: 5]
            System.out.println("[" + this.getName() + "] Relatório finalizado!");
            
        } catch (InterruptedException e) {
            // Captura obrigatória caso o sleep seja interrompido[cite: 5]
            System.out.println("A thread " + this.getName() + " foi interrompida.");
        }
    }
}