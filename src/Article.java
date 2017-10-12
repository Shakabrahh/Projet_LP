import java.util.Objects;

public class Article {
  private final String reference;
  private final String marque;
  private final String modele;
  private final Double prixParJour;
  private int stock;

  /**
   * Constructeur de la classe Article
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
   * Méthode permettant d'afficher le prix par jour
   *
   * @return Retourne le prix par jour au format Double
   */
  public Double getPrixParJour() {
    return this.prixParJour;
  }

  /**
   * Méthode retournant la référence de l'article
   *
   * @return Retourne la référence au format String
   */
  public String getReference() {
    return this.reference;
  }

  public String getMarque() {
    return this.marque;
  }

  public String getModele() {
    return this.modele;
  }

  /**
   * Méthode permettant l'affichage d'un article
   *
   * @return Retourne une chaine de caractère contenant les attributs d'Article
   */
  @Override
  public String toString() {
    return +'\''
        + ", reference='"
        + this.reference
        + '\''
        + ", marque='"
        + this.marque
        + '\''
        + ", modele='"
        + this.modele
        + '\''
        + ", prixParJour="
        + this.prixParJour
        + ", stock="
        + this.stock
        + '}';
  }

  /**
   * Méthode permettant de récupérer le stock d'un article
   *
   * @return Retourne le stock d'un article
   */
  public int getStock() {
    return this.stock;
  }

  /**
   * Setter du stock d'un article
   *
   * @param stock Le nouveau stock
   */
  public void setStock(int stock) {
    this.stock = stock;
  }

  /**
   * Test d'égalité de deux articles
   *
   * @param o L'article à tester
   * @return Retourne vrai si les deux objets sont les mêmes, faux autrement
   */
  @Override
  public boolean equals(Object o) { // cc
    if (this == o) return true;
    if (!(o instanceof Article)) return false;
    Article article = (Article) o;
    return Objects.equals(this.reference, article.reference)
        && Objects.equals(this.marque, article.marque)
        && Objects.equals(this.modele, article.modele)
        && Objects.equals(this.prixParJour, article.prixParJour);
  }

  /**
   * hashCode de la classe article
   *
   * @return Returns the hash code value for this collection.
   */
  @Override
  public int hashCode() {
    int result = this.reference != null ? this.reference.hashCode() : 0;
    result = 31 * result + (this.marque != null ? this.marque.hashCode() : 0);
    result = 31 * result + (this.modele != null ? this.modele.hashCode() : 0);
    result = 31 * result + (this.prixParJour != null ? this.prixParJour.hashCode() : 0);
    result = 31 * result + this.stock;
    return result;
  }
}
