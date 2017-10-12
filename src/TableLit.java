import java.util.Objects;

public class TableLit extends Article {
  private final Double pMax;
  private final Double longueur;
  private final Double largeur;
  private final Double hauteur;

  /**
   * Constructeur de la classe TableLit
   *
   * @param reference Référence de l'article
   * @param marque Marque de l'article
   * @param modele Modèle de l'article
   * @param prixParJour Prix par jour pour la location
   * @param stock Nombre d'articles disponibles en magasin
   * @param pMax Poids maximal que l'article peut soutenir
   * @param longueur Longueur de l'article
   * @param largeur Largeur de l'article
   * @param hauteur Hauteur de l'article
   */
  protected TableLit(
      String reference,
      String marque,
      String modele,
      Double prixParJour,
      int stock,
      Double pMax,
      Double longueur,
      Double largeur,
      Double hauteur) {
    super(reference, marque, modele, prixParJour, stock);
    this.pMax = pMax;
    this.longueur = longueur;
    this.largeur = largeur;
    this.hauteur = hauteur;
  }

  /**
   * Methode permettant de vérifier l'égalité de deux instances
   *
   * @param o L'article à tester
   * @return Retourne vrai si les deux objets sont les mêmes, faux autrement
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TableLit)) return false;
    if (!super.equals(o)) return false;
    TableLit tableLit = (TableLit) o;
    return Objects.equals(this.pMax, tableLit.pMax)
        && Objects.equals(this.longueur, tableLit.longueur)
        && Objects.equals(this.largeur, tableLit.largeur)
        && Objects.equals(this.hauteur, tableLit.hauteur);
  }

  @Override
  public int hashCode() {
    int result = this.pMax != null ? this.pMax.hashCode() : 0;
    result = 31 * result + (this.longueur != null ? this.longueur.hashCode() : 0);
    result = 31 * result + (this.largeur != null ? this.largeur.hashCode() : 0);
    result = 31 * result + (this.hauteur != null ? this.hauteur.hashCode() : 0);
    return result;
  }

  /**
   * Affichage de TableLit
   *
   * @return Retourne une chaine de caractère contenant les attributs de TableLit
   */
  @Override
  public String toString() {
    return ",pMax="
        + this.pMax
        + ", longueur="
        + this.longueur
        + ", largeur="
        + this.largeur
        + ", hauteur="
        + this.hauteur
        + super.toString();
  }
}
