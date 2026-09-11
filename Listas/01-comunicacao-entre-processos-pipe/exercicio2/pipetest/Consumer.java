package pipetest;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Consumer extends Thread {
    private final DataInputStream in;
    private int menoresQue02 = 0;
    private int maioresQue08 = 0;
    private int dentroDaFaixa = 0;
    private int totalProcessado = 0;

    public Consumer(InputStream is) {
        in = new DataInputStream(is);
    }

    @Override
    public void run() {
        try {
            while (true) {
                double valor = in.readDouble();
                totalProcessado++;

                if (valor < 0.2) {
                    menoresQue02++;
                } else if (valor > 0.8) {
                    maioresQue08++;
                } else {
                    dentroDaFaixa++;
                }

                if (totalProcessado % 10 == 0) {
                    int foraDaFaixa = menoresQue02 + maioresQue08;
                    double percentual = (foraDaFaixa * 100.0) / totalProcessado;

                    System.out.println();
                    System.out.println("--- Relatório após " + totalProcessado + " valores ---");
                    System.out.println("Menores que 0.2: " + menoresQue02);
                    System.out.println("Maiores que 0.8: " + maioresQue08);
                    System.out.println("Dentro da faixa [0.2, 0.8]: " + dentroDaFaixa);
                    System.out.printf("Fora da faixa: %d (%.2f%%)%n", foraDaFaixa, percentual);
                    System.out.println("Total processado: " + totalProcessado);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro no Consumer: " + e.getMessage());
        }
    }
}
