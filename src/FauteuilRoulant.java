import java.util.Objects;

public class FauteuilRoulant extends Article {
  private final Double largAssise;
  private final Double poids;

  /**
   * Constructeur de la classe FauteuilRoulant
   *
   * @param reference Référence de l'article
   * @param marque Marque de l'article
   * @param modele Modèle de l'article
   * @param prixParJour Prix par jour pour la location
   * @param stock Nombre d'articles disponibles en magasin
   * @param largAssise Largeur de l'assise
   * @param poids Poids du fauteuil
   */
  public FauteuilRoulant(
      String reference,
      String marque,
      String modele,
      Double prixParJour,
      int stock,
      Double largAssise,
      Double poids) {
    super(reference, marque, modele, prixParJour, stock);
    this.largAssise = largAssise;
    this.poids = poids;
  }

  /**
   * Méthode d'affichage des attributs du client
   *
   * @return Attributs du FauteuilRoulant sous la forme d'une chaine de caractère
   */
  @Override
  public String toString() {
    return "FauteuilRoulant{"
        + "largAssise="
        + this.largAssise
        + ", poids="
        + this.poids
        + super.toString();
  }

  /**
   * Test d'égalité entre deux instances de FauteuilRoulant
   *
   * @param o l'article à tester
   * @return Vrai si les deux Clients sont les mêmes, faux autrement
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof FauteuilRoulant)) return false;
    if (!super.equals(o)) return false;
    FauteuilRoulant that = (FauteuilRoulant) o;
    return Objects.equals(this.largAssise, that.largAssise)
        && Objects.equals(this.poids, that.poids);
  }

  @Override
  public int hashCode() {
    int result = this.largAssise != null ? this.largAssise.hashCode() : 0;
    result = 31 * result + (this.poids != null ? this.poids.hashCode() : 0);
    return result;
  }
}
