import java.util.Objects;

/** The type Table lit. */
public class TableLit extends Article {
  private final Double poidsMax;
  private final Double longueur;
  private final Double largeur;
  private final Double hauteur;

  /**
   * Constructeur de la classe TableLit.
   *
   * @param reference Référence de l'article
   * @param marque Marque de l'article
   * @param modele Modèle de l'article
   * @param prixParJour Prix par jour pour la location
   * @param stock Nombre d'articles disponibles en magasin
   * @param poidsMax Poids maximal que l'article peut soutenir
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
      Double poidsMax,
      Double longueur,
      Double largeur,
      Double hauteur) {
    super(reference, marque, modele, prixParJour, stock);
    this.poidsMax = poidsMax;
    this.longueur = longueur;
    this.largeur = largeur;
    this.hauteur = hauteur;
  }

  /**
   * Methode permettant de vérifier l'égalité de deux instances.
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
    return Objects.equals(poidsMax, tableLit.poidsMax)
        && Objects.equals(longueur, tableLit.longueur)
        && Objects.equals(largeur, tableLit.largeur)
        && Objects.equals(hauteur, tableLit.hauteur);
  }

  @Override
  public int hashCode() {
    int result = poidsMax != null ? poidsMax.hashCode() : 0;
    result = 31 * result + (longueur != null ? longueur.hashCode() : 0);
    result = 31 * result + (largeur != null ? largeur.hashCode() : 0);
    result = 31 * result + (hauteur != null ? hauteur.hashCode() : 0);
    return result;
  }

  /**
   * Affichage de TableLit.
   *
   * @return Retourne une chaine de caractère contenant les attributs de TableLit
   */
  @Override
  public String toString() {
    return ",poidsMax="
        + poidsMax
        + ", longueur="
        + longueur
        + ", largeur="
        + largeur
        + ", hauteur="
        + hauteur
        + super.toString();
  }
}
