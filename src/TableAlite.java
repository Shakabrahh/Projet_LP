public class TableAlite extends TableLit {
  /**
   * Constructeur de la classe TableAlite
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
  public TableAlite(
      String reference,
      String marque,
      String modele,
      Double prixParJour,
      int stock,
      Double pMax,
      Double longueur,
      Double largeur,
      Double hauteur) {
    super(reference, marque, modele, prixParJour, stock, pMax, longueur, largeur, hauteur);
  }

  /**
   * Méthode permettant d'afficher les attributs de TableAlite
   *
   * @return Retourne une chaine de caractère contenant les attributs de TableAlite
   */
  @Override
  public String toString() {
    return "TableAlite{" + super.toString();
  }
}
