import java.util.Objects;

/** The type Article. */
public class Article {
  private final String reference;
  private final String marque;
  private final String modele;
  private final Double prixParJour;
  private int stock;

  /**
   * Constructeur de la classe Article.
   *
   * @param reference Référence de l'article
   * @param marque Marque de l'article
   * @param modele Modèle de l'article
   * @param prixParJour Prix par jour pour la location
   * @param stock Nombre d'articles disponibles en magasin
   */
  public Article(String reference, String marque, String modele, Double prixParJour, int stock) {
    this.reference = reference;
    this.marque = marque;
    this.modele = modele;
    this.prixParJour = prixParJour;
    this.stock = stock;
  }

  /**
   * Méthode permettant d'afficher le prix par jour.
   *
   * @return Retourne le prix par jour au format Double
   */
  public Double getPrixParJour() {
    return prixParJour;
  }

  /**
   * Méthode retournant la référence de l'article.
   *
   * @return Retourne la référence au format String
   */
  public String getReference() {
    return reference;
  }

  /**
   * Gets marque.
   *
   * @return the marque
   */
  public String getMarque() {
    return marque;
  }

  /**
   * Gets modele.
   *
   * @return the modele
   */
  public String getModele() {
    return modele;
  }

  /**
   * Méthode permettant l'affichage d'un article.
   *
   * @return Retourne une chaine de caractère contenant les attributs d'Article
   */
  @Override
  public String toString() {
    return '\''
        + ", reference='"
        + reference
        + '\''
        + ", marque='"
        + marque
        + '\''
        + ", modele='"
        + modele
        + '\''
        + ", prixParJour="
        + prixParJour
        + ", stock="
        + stock
        + '}';
  }

  /**
   * Méthode permettant de récupérer le stock d'un article.
   *
   * @return Retourne le stock d'un article
   */
  public int getStock() {
    return stock;
  }

  /**
   * Setter du stock d'un article.
   *
   * @param stock Le nouveau stock
   */
  public void setStock(int stock) {
    this.stock = stock;
  }

  /**
   * Test d'égalité de deux articles.
   *
   * @param obj L'article à tester
   * @return Retourne vrai si les deux objets sont les mêmes, faux autrement
   */
  @Override
  public boolean equals(Object obj) { // cc
    if (this == obj) return true;
    if (!(obj instanceof Article)) return false;
    Article article = (Article) obj;
    return Objects.equals(reference, article.reference)
        && Objects.equals(marque, article.marque)
        && Objects.equals(modele, article.modele)
        && Objects.equals(prixParJour, article.prixParJour);
  }

  /**
   * hashCode de la classe article.
   *
   * @return Returns the hash code value for this collection.
   */
  @Override
  public int hashCode() {
    int result = reference != null ? reference.hashCode() : 0;
    result = 31 * result + (marque != null ? marque.hashCode() : 0);
    result = 31 * result + (modele != null ? modele.hashCode() : 0);
    result = 31 * result + (prixParJour != null ? prixParJour.hashCode() : 0);
    result = 31 * result + stock;
    return result;
  }
}
