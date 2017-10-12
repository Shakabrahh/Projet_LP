import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class Magasin {
  // attributs
  private final List<Client> clients = new ArrayList<>();
  private final List<Article> articles_stock = new ArrayList<>();

  /**
   * Ajoute un article au stock du magasin
   *
   * @param article L'article a ajouter
   */
  public void ajouter_article_stock(Article article) {
    if (this.articles_stock.contains(article))
      this.articles_stock
          .stream()
          .filter(n -> Objects.equals(n, article))
          .forEach(n -> n.setStock(n.getStock() + article.getStock()));
    else this.articles_stock.add(article);
  }

  /** Affiche l'ensemble des locations */
  public void afficher_registre() {
    this.clients.forEach(Client::afficherLocations);
  }

  /**
   * retourne le stock disponible pour chaque article à une periode donnee dans un HashMap
   *
   * @param deb Date de debut de la periode concernee par la location
   * @param fin Date de fin de la periode concernee par la location
   * @return HashMap(Article, stockDisponible) Retourne un hashmap contenant les stocks disponibles
   *     sur une période donnée
   */
  public HashMap<Article, Integer> getStockDispo(LocalDate deb, LocalDate fin) {
    HashMap<Article, Integer> result = new HashMap<>();
    this.articles_stock.forEach(x -> result.put(x, x.getStock()));
    // On retire le nombre d'articles loués au stock d'articles
    this.getRegistre(deb, fin)
        .forEach(
            l1 -> {
              // On retire le nombre d'articles loués au stock d'articles
              l1.getArticles_loue()
                  .keySet()
                  .forEach(
                      a1 -> result.replace(a1, result.get(a1) - l1.getArticles_loue().get(a1)));
            });
    return result;
  }

  /**
   * Retourne la liste des locations concernees par la periode renseignee
   *
   * @param deb Date de debut de la periode
   * @param fin Date de fin de la periode
   * @return Retourne une liste de locations
   */
  public List<Location> getRegistre(LocalDate deb, LocalDate fin) {
    List<Location> locations = new ArrayList<>();
    this.clients.forEach(
        x ->
            x.getLocations()
                .stream()
                .filter(
                    loc ->
                        (loc.getDdb().isAfter(deb) || loc.getDdb().isEqual(deb))
                            && (loc.getDdf().isBefore(fin) || loc.getDdf().isEqual(fin)))
                .forEach(locations::add));
    return locations;
  }

  /** Affiche le stock initial d'articles que possède le magasin */
  public void afficher_stock_initial() {
    this.articles_stock.forEach(System.out::println);
  }

  /**
   * Affiche les locations d'un client
   *
   * @param client Le client du magasin
   */
  public void location_du_client(Client client) {
    if (this.clientExist(client)) client.afficherLocations();
  }

  /**
   * Méthode qui retourne les locations qui se terminent au mois de la date passé en paramètre
   *
   * @param fin Date de fin des locations
   * @return retourne les locations qui se terminent au mois et à l'année passé en paramètre
   */
  public List<Location> getLocationFin(LocalDate fin) {
    List<Location> locations = new ArrayList<>();
    this.clients.forEach(
        x ->
            x.getLocations()
                .stream()
                .filter(loc -> this.samePeriod(loc.getDdf(), fin))
                .forEach(locations::add));
    return locations;
  }

  /**
   * Cette méthode permet de trier la liste d'articles du magasin en fonction d'une option passée en
   * paramètre. Ces options sont les suivantes : reference, marque, modele et stock. Tous les tris
   * sont croissants, à l'exception de stock qui est décroissant.
   *
   * @param option Le stock d'articles est trié en fonction du paramètre
   */
  public void trierPar(String option) {
    switch (option) {
      case "reference":
        this.articles_stock.sort(Comparator.comparing(Article::getReference));
        break;
      case "marque":
        this.articles_stock.sort(Comparator.comparing(Article::getMarque));
        break;
      case "modele":
        this.articles_stock.sort(Comparator.comparing(Article::getModele));
        break;
      case "stock":
        this.articles_stock.sort((p1, p2) -> Integer.compare(p2.getStock(), p1.getStock()));
        /* On compare p2 à p1 au lieu de p1 à p2 avoir d'avoir un tri décroissant ce qui est plus
         * logique lorsque l'on tri un stock */
        break;
      case "prix":
        this.articles_stock.sort(Comparator.comparing(Article::getPrixParJour));
        break;
      default:
        System.out.println("L'option de tri passée en paramètre est invalide");
        break;
    }
  }

  /**
   * Méthode permettant de comparer deux dates en prenant le mois et l'année
   *
   * @param date1 Première date à comparer
   * @param date2 Deuxième date à comparer
   * @return Retourne vrai si les deux dates ont le même mois et la même année, sinon faux
   */
  public boolean samePeriod(LocalDate date1, LocalDate date2) {
    return date1.getMonth() == date2.getMonth() && date1.getYear() == date2.getYear();
  }

  /**
   * @param client Le client à vérifier
   * @return True si le client est enregistré dans le magasin, false sinon
   */
  public boolean clientExist(Client client) {
    return this.clients.contains(client);
  }

  /**
   * @param l1 La location a potentiellement ajouter au magasin
   * @return True si la location est possible, false sinon
   */
  public boolean locationPossible(Location l1) {
    HashMap<Article, Integer> stockDispo = this.getStockDispo(l1.getDdb(), l1.getDdf());
    if (stockDispo.isEmpty()) {
      System.out.println("Article non disponible");
      return false;
    }
    for (Article a1 : l1.getArticles_loue().keySet()) {
      HashMap<Article, Integer> articlesLoues = l1.getArticles_loue();
      if (articlesLoues != null && articlesLoues.get(a1) != null && stockDispo.get(a1) != null) {
        // si plus d'articles loué que disponibles
        if (stockDispo.get(a1) < articlesLoues.get(a1)) return false;
      } else return false;
    }
    return true;
  }

  /**
   * Ajoute une location à un client du magasin si possible
   *
   * @param l1 La location a ajoutée
   * @param c1 Le client bénéficiaire de la location
   */
  public void ajouterLocation(Location l1, Client c1) throws NotEnoughStock {
    if (this.locationPossible(l1)) {
      if (this.clientExist(c1)) {
        this.clients.get(this.clients.indexOf(c1)).getLocations().add(l1);
      } else System.out.println("Le client n'existe pas.");
    } else throw new NotEnoughStock("Pas assez d'articles en stock, annulation de la location");
  }

  /**
   * @param fin Date de fin
   * @return La recette du magasin pour la période renseignée
   */
  public int calculRecettePeriode(LocalDate fin) {
    int montantTotal = 0;
    for (Location l1 : this.getLocationFin(fin)) montantTotal += l1.getMontant();
    return montantTotal;
  }

  /**
   * @param deb Date de début
   * @param fin Date de fin
   * @return La recette du magasin pour la période renseignée
   */
  public int calculRecettePeriode(LocalDate deb, LocalDate fin) {
    int montantTotal = 0;
    for (Location l1 : this.getRegistre(deb, fin)) montantTotal += l1.getMontant();
    return montantTotal;
  }

  /**
   * Crée une archive sous la forme d'un fichier "{annee}{mois}.log" qui liste les locations en
   * cours sur le mois et l'année indiqués
   *
   * @param mois Mois à archiver
   * @param annee Année à archiver
   */
  public void archiveMois(int mois, int annee) {
    LocalDate archive_fin = LocalDate.of(annee, mois, 1);
    try {
      Files.write(
          Paths.get(String.valueOf(annee) + String.valueOf(mois) + ".loc."),
          this.getLocationFin(archive_fin).toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Ajoute un client au magasin si il n'existe pas
   *
   * @param cli Le client à ajouter
   */
  public void ajouterClient(Client cli) {
    if (this.clientExist(cli)) {
      System.out.println("Le client existe déjà : " + cli);
    } else {
      System.out.println("Ajout du client effectué : " + cli);
      this.clients.add(cli);
    }
  }
}
