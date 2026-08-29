import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

enum Prioridade {
    ALTA(1), 
    MEDIA(2), 
    BAIXA(3);

    private final int valor;

    Prioridade(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}

class Tarefa {
    private final int id;
    private final Prioridade prioridade;

    public Tarefa(int id, Prioridade prioridade) {
        this.id = id;
        this.prioridade = prioridade;
    }

    public int getId() {
        return id;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void executar() {
        System.out.println("Tarefa " + id + " (" + prioridade + ") realizada com sucesso.");
    }

    @Override
    public String toString() {
        return "Tarefa " + id + " (" + prioridade + ")";
    }
}

class Produtor {
    private final Random random = new Random();

    public List<Tarefa> produzirTarefas(int quantidade) {
        List<Tarefa> lista = new ArrayList<>();
        Prioridade[] prioridades = Prioridade.values();

        System.out.println("Tarefas geradas:");
        for (int i = 0; i < quantidade; i++) {
            Prioridade p = prioridades[random.nextInt(prioridades.length)];
            Tarefa t = new Tarefa(i, p);
            lista.add(t);
            System.out.println(t);
        }
        System.out.println();
        return lista;
    }
}

class Consumidor {
    public void consumirTarefas(List<Tarefa> tarefas) {
        tarefas.sort(Comparator.comparingInt(t -> t.getPrioridade().getValor()));

        int countAlta = 0;
        int countMedia = 0;
        int countBaixa = 0;

        System.out.println("Executando por prioridade:");
        for (Tarefa t : tarefas) {
            t.executar();

            switch (t.getPrioridade()) {
                case ALTA -> countAlta++;
                case MEDIA -> countMedia++;
                case BAIXA -> countBaixa++;
            }
        }

        System.out.println();
        System.out.println("Resumo: " + countAlta + " ALTA, " + countMedia + " MÉDIA, " + countBaixa + " BAIXA");
    }
}

public class Main {
    public static void main(String[] args) {
        int totalTarefas = 8;

        Produtor produtor = new Produtor();
        Consumidor consumidor = new Consumidor();

        List<Tarefa> tarefas = produtor.produzirTarefas(totalTarefas);
        consumidor.consumirTarefas(tarefas);
    }
}