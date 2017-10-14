import java.util.Objects;

/** The type Souleve malade. */
public class SouleveMalade extends Article {
  private final Double capLevage;
  private final Double degPivo;

  /**
   * Constructeur de la classe SouleveMalade.
   *
   * @param reference Référence de l'article
   * @param marque Marque de l'article
   * @param modele Modèle de l'article
   * @param prixParJour Prix par jour pour la location
   * @param stock Nombre d'articles disponibles en magasin
   * @param capLevage Capacité de levage de l'article
   * @param degPivo Capacité de rotation de l'article
   */
  public SouleveMalade(
      String reference,
      String marque,
      String modele,
      Double prixParJour,
      int stock,
      Double capLevage,
      Double degPivo) {
    super(reference, marque, modele, prixParJour, stock);
    this.capLevage = capLevage;
    this.degPivo = degPivo;
  }

  /**
   * Test d'égalité entre deux instances de SouleveMalade.
   *
   * @param o l'article à tester
   * @return Vrai si les deux Clients sont les mêmes, faux autrement
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SouleveMalade)) return false;
    if (!super.equals(o)) return false;
    SouleveMalade that = (SouleveMalade) o;
    return Objects.equals(capLevage, that.capLevage) && Objects.equals(degPivo, that.degPivo);
  }

  @Override
  public int hashCode() {
    int result = capLevage != null ? capLevage.hashCode() : 0;
    result = 31 * result + (degPivo != null ? degPivo.hashCode() : 0);
    return result;
  }

  /**
   * Affichage des attributs.
   *
   * @return Attributs du SouleveMalade sous la forme d'une chaine de caractère
   */
  @Override
  public String toString() {
    return "SouleveMalade{" + "capLevage=" + capLevage + ", degPivo=" + degPivo + super.toString();
  }
}
