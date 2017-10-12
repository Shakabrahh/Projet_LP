import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

public class Location {
  private final HashMap<Article, Integer> articles_loue;
  private LocalDate ddb;
  private LocalDate ddf;
  private Double montant;

  /**
   * Constructeur de la classe location
   *
   * @param ddb date du début de la location
   * @param ddf date de fin de la location
   */
  public Location(LocalDate ddb, LocalDate ddf) {
    this.ddb = ddb;
    this.ddf = ddf;
    this.montant = 0.0;
    this.articles_loue = new HashMap<>();
  }

  /**
   * Comparer deux locations
   *
   * @param o Location à tester
   * @return Retourne vrai si les deux objets sont les mêmes, faux autrement
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Location)) return false;
    Location location = (Location) o;
    return Objects.equals(this.ddb, location.ddb)
        && Objects.equals(this.ddf, location.ddf)
        && Objects.equals(this.montant, location.montant)
        && Objects.equals(this.articles_loue, location.articles_loue);
  }

  /**
   * Getter date début
   *
   * @return Retourne la date de début
   */
  public LocalDate getDdb() {
    return this.ddb;
  }

  /**
   * Setter date début
   *
   * @param ddb Date de début
   */
  public void setDdb(LocalDate ddb) {
    this.ddb = ddb;
  }

  /**
   * Getter date de fin
   *
   * @return Retourne la date de fin
   */
  public LocalDate getDdf() {
    return this.ddf;
  }

  /**
   * Setter date de fin
   *
   * @param ddf Date de fin
   */
  public void setDdf(LocalDate ddf) {
    this.ddf = ddf;
  }

  /**
   * Getter montant de la location
   *
   * @return Retourne le montant de la location
   */
  public Double getMontant() {
    return this.montant;
  }

  /**
   * Setter montant de la location
   *
   * @param montant Montant de la location
   */
  public void setMontant(Double montant) {
    this.montant = montant;
  }

  /**
   * Getter article loués
   *
   * @return Retourne la liste des articles loués
   */
  public HashMap<Article, Integer> getArticles_loue() {
    return this.articles_loue;
  }

  /**
   * Ajouter un nouvel article à une location
   *
   * @param a L'article à ajouter
   */
  public void ajouter_article(Article a) {
    this.montant += a.getPrixParJour();
    if (this.articles_loue.containsKey(a))
      this.articles_loue.replace(a, this.articles_loue.get(a) + 1);
    else this.articles_loue.put(a, 1);
  }

  /** Affichage textuel des articles dans une location */
  public void afficherArticles() {
    this.articles_loue.forEach((k, v) -> System.out.println(k + ", " + v));
  }

  /**
   * Retourne les articles de la location avec la quantité commandée sous la forme d'une chaîne de
   * caractère
   *
   * @return Retourne les articles avec la quantité commandée
   */
  public String construireStringArticle() {
    return this.articles_loue
        .entrySet()
        .stream()
        .map(entry -> "\n    ■ " + entry.getKey() + " quantité : " + entry.getValue())
        .collect(Collectors.joining(""));
  }

  /**
   * Ajouter des articles dans une location avec une quantité
   *
   * @param a L'article à ajouter
   * @param nb Nombre d'article à louer
   */
  public void ajouter_article(Article a, int nb) {
    this.montant += a.getPrixParJour() * nb * ChronoUnit.DAYS.between(this.ddb, this.ddf);
    if (this.articles_loue.containsKey(a))
      this.articles_loue.replace(a, this.articles_loue.get(a) + nb);
    else this.articles_loue.put(a, nb);
  }

  /**
   * Méthode permettant la récupération des attributs d'une location
   *
   * @return Retourne une chaine de caractère contenant les attributs de Location
   */
  @Override
  public String toString() {
    return "\n▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n"
        + "Location :"
        + "\nddb = "
        + this.ddb
        + "\nddf = "
        + this.ddf
        + "\nmontant = "
        + this.montant
        + " €\nArticles : "
        + this.construireStringArticle()
        + "\n▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n";
  }
}
