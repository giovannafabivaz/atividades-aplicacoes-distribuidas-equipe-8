package servico;

import java.util.Random;

public class Servico implements Cumprimento {

  public Servico() {

  }

  public int gerarNumero() {
    Random random = new Random();
    return random.nextInt(101) + 50;
  }

}