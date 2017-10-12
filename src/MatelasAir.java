import java.util.Objects;

public class MatelasAir extends TableLit {
  private final Double tGonflage;

  /**
   * Constructeur de la classe MatelasAir
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
   * @param tGonflage Temps nécessaire pour gonfler le matelas
   */
  public MatelasAir(
      String reference,
      String marque,
      String modele,
      Double prixParJour,
      int stock,
      Double pMax,
      Double longueur,
      Double largeur,
      Double hauteur,
      Double tGonflage) {
    super(reference, marque, modele, prixParJour, stock, pMax, longueur, largeur, hauteur);
    this.tGonflage = tGonflage;
  }

  /**
   * Affichage des attributs de matelasAir
   *
   * @return Attributs de MatelasAir sous la forme d'une chaine de caractère
   */
  @Override
  public String toString() {
    return "MatelasAir{" + "tGonflage=" + this.tGonflage + super.toString();
  }

  /**
   * Test d'égalité entre deux instances de MatelasAir
   *
   * @param o L'article à tester
   * @return Vrai si les deux objets sont les mêmes, faux autrement
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MatelasAir)) return false;
    if (!super.equals(o)) return false;
    MatelasAir that = (MatelasAir) o;
    return Objects.equals(this.tGonflage, that.tGonflage);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (this.tGonflage != null ? this.tGonflage.hashCode() : 0);
    return result;
  }
}
