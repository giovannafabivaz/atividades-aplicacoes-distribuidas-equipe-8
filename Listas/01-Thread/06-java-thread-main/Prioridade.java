// enum criado conforme sugerido no Exercício 3.
public enum Prioridade {
    ALTA(1), MEDIA(2), BAIXA(3);
    
    private final int valor;
    
    Prioridade(int valor) { 
        this.valor = valor; 
    }
    
    public int getValor() { 
        return valor; 
    }
}