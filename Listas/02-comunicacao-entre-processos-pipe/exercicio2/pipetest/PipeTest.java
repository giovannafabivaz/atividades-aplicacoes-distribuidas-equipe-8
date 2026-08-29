package pipetest;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeTest {
    public static void main(String[] args) {
        try {
            PipedOutputStream pout1 = new PipedOutputStream();
            PipedOutputStream pout2 = new PipedOutputStream();

            PipedInputStream pin1 = new PipedInputStream(pout1);
            PipedInputStream pin2 = new PipedInputStream(pout2);

            Producer prod = new Producer(pout1);
            Filter filt = new Filter(pin1, pout2);
            Consumer cons = new Consumer(pin2);

            prod.start();
            filt.start();
            cons.start();
        } catch (IOException e) {
            System.out.println("Erro ao criar os pipes: " + e.getMessage());
        }
    }
}
