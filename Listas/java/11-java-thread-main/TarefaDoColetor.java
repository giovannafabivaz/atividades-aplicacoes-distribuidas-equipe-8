import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/*
 * Thread responsável por coletar as páginas HTML de um conjunto de URLs.
 */
public class TarefaDoColetor extends Thread {

    private final String nome;
    private final List<String> urls;
    private final Map<String, Integer> frequencias;

    public TarefaDoColetor(String nome, List<String> urls) {
        this.nome = nome;
        this.urls = urls;
        this.frequencias = new LinkedHashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public Map<String, Integer> getFrequencias() {
        return frequencias;
    }

    @Override
    public void run() {
        for (String endereco : urls) {
            try {
                System.out.println(nome + " coletando: " + endereco);

                String html = baixarPagina(endereco);
                String texto = limparHtml(html);
                contarPalavras(texto);

                System.out.println(nome + " finalizou: " + endereco);

            } catch (Exception e) {
                System.out.println(
                    nome + " não conseguiu coletar " + endereco +
                    ": " + e.getMessage()
                );
            }
        }
    }

    /*
     * Faz o download do conteúdo HTML de uma URL.
     */
    private String baixarPagina(String endereco) throws Exception {
        URL url = new URL(endereco);
        URLConnection conexao = url.openConnection();

        conexao.setConnectTimeout(10000);
        conexao.setReadTimeout(10000);
        conexao.setRequestProperty("User-Agent", "Mozilla/5.0");

        StringBuilder html = new StringBuilder();

        try (
            InputStream entrada = conexao.getInputStream();
            BufferedReader leitor = new BufferedReader(
                new InputStreamReader(entrada, StandardCharsets.UTF_8)
            )
        ) {
            String linha;

            while ((linha = leitor.readLine()) != null) {
                html.append(linha).append('\n');
            }
        }

        return html.toString();
    }

    /*
     * Remove scripts, estilos, comentários e tags HTML.
     */
    private String limparHtml(String html) {
        String texto = html;

        texto = texto.replaceAll("(?is)<script.*?</script>", " ");
        texto = texto.replaceAll("(?is)<style.*?</style>", " ");
        texto = texto.replaceAll("(?is)<!--.*?-->", " ");
        texto = texto.replaceAll("(?is)<[^>]*>", " ");

        /*
         * Trata algumas entidades HTML comuns.
         */
        texto = texto.replace("&nbsp;", " ");
        texto = texto.replace("&amp;", " ");
        texto = texto.replace("&quot;", " ");
        texto = texto.replace("&lt;", " ");
        texto = texto.replace("&gt;", " ");
        texto = texto.replace("&#39;", " ");

        return texto;
    }

    /*
     * Separa o texto em palavras e conta suas ocorrências.
     */
    private void contarPalavras(String texto) {
        String textoNormalizado = texto.toLowerCase(Locale.ROOT);

        String[] palavras = textoNormalizado.split(
            "[^\\p{L}\\p{Nd}]+"
        );

        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                frequencias.put(
                    palavra,
                    frequencias.getOrDefault(palavra, 0) + 1
                );
            }
        }
    }
}

