import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

/** The type Location. */
public class Location {
  private final HashMap<Article, Integer> articlesLoue;
  private LocalDate ddb;
  private LocalDate ddf;
  private Double montant;

  /**
   * Constructeur de la classe location.
   *
   * @param ddb date du début de la location.
   * @param ddf date de fin de la location.
   */
  public Location(LocalDate ddb, LocalDate ddf) {
    this.ddb = ddb;
    this.ddf = ddf;
    montant = 0.0;
    articlesLoue = new HashMap<>();
  }

  /**
   * Comparer deux locations.
   *
   * @param obj Location à tester.
   * @return Retourne vrai si les deux objets sont les mêmes, faux autrement.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Location location = (Location) obj;
    return Objects.equals(articlesLoue, location.articlesLoue)
        && Objects.equals(ddb, location.ddb)
        && Objects.equals(ddf, location.ddf)
        && Objects.equals(montant, location.montant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(articlesLoue, ddb, ddf, montant);
  }

  /**
   * Getter date début.
   *
   * @return Retourne la date de début.
   */
  public LocalDate getDdb() {
    return ddb;
  }

  /**
   * Setter date début.
   *
   * @param ddb Date de début.
   */
  public void setDdb(LocalDate ddb) {
    this.ddb = ddb;
  }

  /**
   * Getter date de fin.
   *
   * @return Retourne la date de fin.
   */
  public LocalDate getDdf() {
    return ddf;
  }

  /**
   * Setter date de fin.
   *
   * @param ddf Date de fin.
   */
  public void setDdf(LocalDate ddf) {
    this.ddf = ddf;
  }

  /**
   * Getter montant de la location.
   *
   * @return Retourne le montant de la location.
   */
  public Double getMontant() {
    return montant;
  }

  /**
   * Setter montant de la location.
   *
   * @param montant Montant de la location.
   */
  public void setMontant(Double montant) {
    this.montant = montant;
  }

  /**
   * Getter article loués.
   *
   * @return Retourne la liste des articles loués.
   */
  public HashMap<Article, Integer> getarticlesLoue() {
    return articlesLoue;
  }

  /**
   * Ajouter un nouvel article à une location.
   *
   * @param a L'article à ajouter.
   */
  public void ajouterArticle(Article a) {
    montant += a.getPrixParJour();
    if (articlesLoue.containsKey(a)) articlesLoue.replace(a, articlesLoue.get(a) + 1);
    else articlesLoue.put(a, 1);
  }

  /**
   * Ajouter des articles dans une location avec une quantité.
   *
   * @param a L'article à ajouter.
   * @param nb Nombre d'article à louer.
   */
  public void ajouterArticle(Article a, int nb) {
    montant += a.getPrixParJour() * nb * ChronoUnit.DAYS.between(ddb, ddf);
    if (articlesLoue.containsKey(a)) articlesLoue.replace(a, articlesLoue.get(a) + nb);
    else articlesLoue.put(a, nb);
  }

  /** Affichage textuel des articles dans une location. */
  public void afficherArticles() {
    articlesLoue.forEach((k, v) -> System.out.println(k + ", " + v));
  }

  /**
   * Retourne les articles de la location avec la quantité commandée sous la forme d'une chaîne de
   * caractère.
   *
   * @return Retourne les articles avec la quantité commandée.
   */
  public String construireStringArticle() {
    return articlesLoue
        .entrySet()
        .stream()
        .map(entry -> "\n    ■ " + entry.getKey() + " quantité : " + entry.getValue())
        .collect(Collectors.joining(""));
  }

  /**
   * Méthode permettant la récupération des attributs d'une location.
   *
   * @return Retourne une chaine de caractère contenant les attributs de Location.
   */
  @Override
  public String toString() {
    return "\n▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n"
        + "Location :"
        + "\nddb = "
        + ddb
        + "\nddf = "
        + ddf
        + "\nmontant = "
        + montant
        + " €\nArticles : "
        + construireStringArticle()
        + "\n▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n";
  }
}
