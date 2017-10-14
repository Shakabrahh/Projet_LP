/** The type Lit medicalise. */
public class LitMedicalise extends TableLit {
  /**
   * Constructeur de la classe LitMedicalise.
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
  public LitMedicalise(
      String reference,
      String marque,
      String modele,
      Double prixParJour,
      int stock,
      Double poidsMax,
      Double longueur,
      Double largeur,
      Double hauteur) {
    super(reference, marque, modele, prixParJour, stock, poidsMax, longueur, largeur, hauteur);
  }

  /**
   * Affichage des attributs de LitMedicalise.
   *
   * @return Retourne une chaine de caractère contenant les attributs de LitMedicalise
   */
  @Override
  public String toString() {
    return "LitMedicalise{ " + super.toString();
  }
}
