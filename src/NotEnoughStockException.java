/** The type Not enough stock. */
public class NotEnoughStockException extends RuntimeException {
  /** Exception déclenchée si le nombre d'article est insuffisant. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new Not enough stock.
   *
   * @param message the message
   */
  public NotEnoughStockException(String message) {
    super(message);
  }
}
