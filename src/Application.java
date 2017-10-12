import java.time.LocalDate;
import java.util.List;

public class Application {
  /**
   * Méthode de test du magasin
   *
   * @param args Les arguments passés en paramètre
   */
  public static void main(String... args) {

    /* Création d'un magasin */
    Magasin magasin = new Magasin();

    /* Création des articles */
    Article article1 = new FauteuilRoulant("1414", "ABCD", "chaise", 14.0, 45, 12.2, 12.3);
    Article article2 =
        new MatelasAir("1404", "ABCD", "chaise", 14.0, 222, 12.2, 12.5, 14.0, 15.0, 12.0);

    /* Ajout des articles dans le stock du magasin */
    magasin.ajouter_article_stock(article1);
    magasin.ajouter_article_stock(article2);

    System.out.println("# Stock initial des articles #");
    magasin.afficher_stock_initial();

    System.out.println("# Stock initial des articles après tri #");
    magasin.trierPar("stock");
    magasin.afficher_stock_initial();

    LocalDate ddb = LocalDate.of(2017, 6, 1);
    LocalDate ddf = LocalDate.of(2017, 6, 30);

    LocalDate ddb2 = LocalDate.of(2017, 6, 1);
    LocalDate ddf2 = LocalDate.of(2017, 6, 12);

    System.out.println("# Création d'une location #");
    Location location1 = new Location(ddb, ddf);
    Location location2 = new Location(ddb2, ddf2);

    System.out.println("# Liaison des articles avec la location #");
    location1.ajouter_article(article1, 2);
    location1.ajouter_article(article2, 4);

    location2.ajouter_article(article1, 12);
    location2.ajouter_article(article2, 14);

    Client cli = new Client("Jean", "Pierre");
    magasin.ajouterClient(cli);

    try {
      magasin.ajouterLocation(location1, cli);
    } catch (NotEnoughStock notEnoughStock) {
      notEnoughStock.printStackTrace();
    }

    try {
      magasin.ajouterLocation(location2, cli);
    } catch (NotEnoughStock notEnoughStock) {
      notEnoughStock.printStackTrace();
    }

    System.out.println("# Afichage du stock dispo  #");

    magasin
        .getStockDispo(ddb, ddf)
        .forEach((k, v) -> System.out.println("Article : " + k + " Quantité : " + v));

    System.out.println("# On affiche la recette du mois 06 #");
    System.out.println(magasin.calculRecettePeriode(ddf) + " €");

    System.out.println("====================================================================ez$$");
    List<Location> l1 = magasin.getLocationFin(ddf2);
    l1.forEach(System.out::println);
    System.out.println("//////////////////////////////////////////////////////////ez");

    System.out.println("# Création d'une archive #");
    magasin.archiveMois(6, 2017);
  }
}
