import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * Programa principal da atividade.
 */
public class Coletor {

    private static final int NUMERO_DE_THREADS = 5;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        List<String> urls = lerUrls(teclado);

        if (urls.isEmpty()) {
            System.out.println("Nenhuma URL foi informada.");
            teclado.close();
            return;
        }

        List<TarefaDoColetor> tarefas =
            distribuirUrlsEntreThreads(urls);

        iniciarThreads(tarefas);
        esperarThreads(tarefas);

        Map<String, Integer> frequenciasTotais =
            consolidarFrequencias(tarefas);

        exibirMenuOrdenacao(teclado, frequenciasTotais);

        teclado.close();
    }

    /*
     * Le as URLs até que o usuário pressione Enter em uma linha vazia.
     */
    private static List<String> lerUrls(Scanner teclado) {
        List<String> urls = new ArrayList<>();

        System.out.println("Digite as URLs que deseja coletar.");
        System.out.println(
            "Pressione Enter em uma linha vazia para finalizar."
        );
        System.out.println();

        while (true) {
            System.out.print("URL: ");
            String url = teclado.nextLine().trim();

            if (url.isEmpty()) {
                break;
            }

            urls.add(url);
        }

        return urls;
    }

    /*
     * Distribui as URLs entre exatamente cinco threads.
     *
     * A distribuiçao é feita em rodizio:
     * URL 0 vai para a thread 0;
     * URL 1 vai para a thread 1;
     * assim ate o fim.
     */
    private static List<TarefaDoColetor> distribuirUrlsEntreThreads(
        List<String> urls
    ) {
        List<List<String>> grupos = new ArrayList<>();

        for (int i = 0; i < NUMERO_DE_THREADS; i++) {
            grupos.add(new ArrayList<>());
        }

        for (int i = 0; i < urls.size(); i++) {
            int indiceDaThread = i % NUMERO_DE_THREADS;
            grupos.get(indiceDaThread).add(urls.get(i));
        }

        List<TarefaDoColetor> tarefas = new ArrayList<>();

        for (int i = 0; i < NUMERO_DE_THREADS; i++) {
            tarefas.add(
                new TarefaDoColetor(
                    "Thread-" + (i + 1),
                    grupos.get(i)
                )
            );
        }

        return tarefas;
    }

    /*
     * Inicia as cinco threads.
     */
    private static void iniciarThreads(
        List<TarefaDoColetor> tarefas
    ) {
        System.out.println();
        System.out.println(
            "Iniciando " + NUMERO_DE_THREADS + " threads..."
        );
        System.out.println();

        for (TarefaDoColetor tarefa : tarefas) {
            tarefa.start();
        }
    }

    /*
     * Aguarda o encerramento das cinco threads.
     */
    private static void esperarThreads(
        List<TarefaDoColetor> tarefas
    ) {
        for (TarefaDoColetor tarefa : tarefas) {
            try {
                tarefa.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                System.out.println(
                    "A espera pela thread foi interrompida."
                );
            }
        }

        System.out.println();
        System.out.println("Todas as threads foram finalizadas.");
    }

    /*
     * Soma os mapas de frequência produzidos pelas threads.
     */
    private static Map<String, Integer> consolidarFrequencias(
        List<TarefaDoColetor> tarefas
    ) {
        Map<String, Integer> frequenciasTotais =
            new LinkedHashMap<>();

        for (TarefaDoColetor tarefa : tarefas) {
            for (
                Map.Entry<String, Integer> entrada :
                tarefa.getFrequencias().entrySet()
            ) {
                String palavra = entrada.getKey();
                Integer frequencia = entrada.getValue();

                frequenciasTotais.put(
                    palavra,
                    frequenciasTotais.getOrDefault(palavra, 0)
                        + frequencia
                );
            }
        }

        return frequenciasTotais;
    }

    /*
     * Exibe o menu de ordenação e imprime o resultado.
     */
    private static void exibirMenuOrdenacao(
        Scanner teclado,
        Map<String, Integer> frequencias
    ) {
        System.out.println();
        System.out.println("Escolha a ordenação das palavras:");
        System.out.println("1 - Ordem alfabética A-Z");
        System.out.println("2 - Ordem alfabética inversa Z-A");
        System.out.println("3 - Frequência crescente");
        System.out.println("4 - Frequência decrescente");
        System.out.print("Opção: ");

        String opcao = teclado.nextLine().trim();

        List<Map.Entry<String, Integer>> palavras =
            new ArrayList<>(frequencias.entrySet());

        switch (opcao) {
            case "1":
                palavras.sort(
                    Comparator.comparing(
                        Map.Entry<String, Integer>::getKey
                    )
                );
                break;

            case "2":
                palavras.sort(
                    Comparator.comparing(
                        Map.Entry<String, Integer>::getKey
                    ).reversed()
                );
                break;

            case "3":
                palavras.sort(
                    Comparator
                        .comparing(
                            Map.Entry<String, Integer>::getValue
                        )
                        .thenComparing(
                            Map.Entry<String, Integer>::getKey
                        )
                );
                break;

            case "4":
                palavras.sort(
                    Comparator
                        .comparing(
                            Map.Entry<String, Integer>::getValue
                        )
                        .reversed()
                        .thenComparing(
                            Map.Entry<String, Integer>::getKey
                        )
                );
                break;

            default:
                System.out.println("Opção inválida.");
                return;
        }

        imprimirFrequencias(palavras);
    }

    /*
     * Imprime o vetor de palavras distintas e suas frequências.
     */
    private static void imprimirFrequencias(
        List<Map.Entry<String, Integer>> palavras
    ) {
        System.out.println();
        System.out.println("======================================");
        System.out.println("PALAVRAS E FREQUÊNCIAS");
        System.out.println("======================================");

        if (palavras.isEmpty()) {
            System.out.println("Nenhuma palavra foi encontrada.");
            return;
        }

        for (Map.Entry<String, Integer> entrada : palavras) {
            System.out.printf(
                "%-30s %d%n",
                entrada.getKey(),
                entrada.getValue()
            );
        }

        System.out.println("======================================");
        System.out.println(
            "Total de palavras distintas: " + palavras.size()
        );
    }
}

