package pipetest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class Producer extends Thread {
    private final DataOutputStream out;
    private final Random rand = new Random();

    public Producer(OutputStream os) {
        out = new DataOutputStream(os);
    }

    @Override
    public void run() {
        try {
            while (true) {
                double num = rand.nextDouble();
                out.writeDouble(num);
                out.flush();
                Thread.sleep(Math.abs(rand.nextInt() % 100));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.out.println("Erro no Producer: " + e.getMessage());
        }
    }
}
