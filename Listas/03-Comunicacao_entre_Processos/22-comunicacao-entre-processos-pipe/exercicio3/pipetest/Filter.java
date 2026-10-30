package pipetest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Filter extends Thread {
    private final DataInputStream in;
    private final DataOutputStream out;

    public Filter(InputStream is, OutputStream os) {
        in = new DataInputStream(is);
        out = new DataOutputStream(os);
    }

    @Override
    public void run() {
        try {
            while (true) {
                double valor = in.readDouble();
                out.writeDouble(valor);
                out.flush();
            }
        } catch (IOException e) {
            // O fim do primeiro pipe ocorre quando o Producer termina.
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o pipe do Filter: " + e.getMessage());
            }
        }
    }
}
