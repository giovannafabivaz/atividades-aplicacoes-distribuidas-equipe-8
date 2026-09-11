class GerenciadorDownloads {

    private int downloadsConcluidos = 0;
    private int downloadsFalhados = 0;

    private final Object travaSucesso = new Object();
    private final Object travaFalha = new Object();

    public void incrementarSucesso(String nomeArquivo) {

        synchronized (travaSucesso) {

            downloadsConcluidos++;

            System.out.println(
                "[SUCESSO] " + nomeArquivo +
                " | Downloads concluídos: " +
                downloadsConcluidos
            );

            System.out.flush();
        }
    }

    public void incrementarFalha(String nomeArquivo) {

        synchronized (travaFalha) {

            downloadsFalhados++;

            System.out.println(
                "[FALHA] " + nomeArquivo +
                " | Downloads falhados: " +
                downloadsFalhados
            );

            System.out.flush();
        }
    }
}

public class Ex4_GerenciadorDownloads {

    public static void main(String[] args) {

        GerenciadorDownloads gerenciador =
            new GerenciadorDownloads();

        Thread threadA = new Thread(() -> {

            for (int i = 1; i <= 5; i++) {
                gerenciador.incrementarSucesso(
                    "arquivo_sucesso_" + i + ".zip"
                );
            }
        });

        Thread threadB = new Thread(() -> {

            for (int i = 1; i <= 5; i++) {
                gerenciador.incrementarFalha(
                    "arquivo_falha_" + i + ".zip"
                );
            }
        });

        threadA.start();
        threadB.start();
    }
}