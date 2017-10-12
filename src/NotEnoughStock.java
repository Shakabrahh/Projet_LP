public class NotEnoughStock extends Exception {
  /** Exception déclenchée si le nombre d'article est insuffisant */
  private static final long serialVersionUID = 1L;

  public NotEnoughStock(String message) {
    super(message);
  }
}
