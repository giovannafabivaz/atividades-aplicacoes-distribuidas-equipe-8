public class Pi implements Parametro {
  public Pi() {
  }
  
  public Object executar() {
    return Double.valueOf(Math.PI); // Retorna o valor de PI.
  }
}