import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

/** The type Magasin. */
public class Magasin {
  // attributs
  private final List<Client> clients = new ArrayList<>();
  private final List<Article> articlesStock = new ArrayList<>();

  /**
   * Ajoute un article au stock du magasin.
   *
   * @param article L'article a ajouter
   */
  public void ajouterArticleStock(Article article) {
    if (articlesStock.contains(article))
      articlesStock
          .stream()
          .filter(n -> Objects.equals(n, article))
          .forEach(n -> n.setStock(n.getStock() + article.getStock()));
    else articlesStock.add(article);
  }

  /** Affiche l'ensemble des locations. */
  public void afficherRegistre() {
    clients.forEach(Client::afficherLocations);
  }

  /**
   * Retourne le stock disponible pour chaque article à une periode donnee dans un HashMap.
   *
   * @param deb Date de debut de la periode concernee par la location
   * @param fin Date de fin de la periode concernee par la location
   * @return HashMap(Article, stockDisponible) Retourne un hashmap contenant les stocks disponibles
   *     sur une période donnée
   */
  public HashMap<Article, Integer> getStockDispo(LocalDate deb, LocalDate fin) {
    HashMap<Article, Integer> result = new HashMap<>();
    articlesStock.forEach(x -> result.put(x, x.getStock()));
    // On retire le nombre d'articles loués au stock d'articles
    getRegistre(deb, fin)
        .forEach(
            l1 -> {
              // On retire le nombre d'articles loués au stock d'articles
              l1.getarticlesLoue()
                  .keySet()
                  .forEach(a1 -> result.replace(a1, result.get(a1) - l1.getarticlesLoue().get(a1)));
            });
    return result;
  }

  /**
   * Retourne la liste des locations concernees par la periode renseignee.
   *
   * @param deb Date de debut de la periode
   * @param fin Date de fin de la periode
   * @return Retourne une liste de locations
   */
  public Iterable<Location> getRegistre(ChronoLocalDate deb, ChronoLocalDate fin) {
    List<Location> locations = new ArrayList<>();
    clients.forEach(
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

  /** Affiche le stock initial d'articles que possède le magasin. */
  public void afficherStockInitial() {
    articlesStock.forEach(System.out::println);
  }

  /**
   * Affiche les locations d'un client.
   *
   * @param client Le client du magasin
   */
  public void locationsClient(Client client) {
    if (clientExist(client)) client.afficherLocations();
  }

  /**
   * Méthode qui retourne les locations qui se terminent au mois de la date passé en paramètre.
   *
   * @param fin Date de fin des locations
   * @return retourne les locations qui se terminent au mois et à l'année passé en paramètre
   */
  public List<Location> getLocationFin(LocalDate fin) {
    List<Location> locations = new ArrayList<>();
    clients.forEach(
        x ->
            x.getLocations()
                .stream()
                .filter(loc -> samePeriod(loc.getDdf(), fin))
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
        articlesStock.sort(Comparator.comparing(Article::getReference));
        break;
      case "marque":
        articlesStock.sort(Comparator.comparing(Article::getMarque));
        break;
      case "modele":
        articlesStock.sort(Comparator.comparing(Article::getModele));
        break;
      case "stock":
        articlesStock.sort((p1, p2) -> Integer.compare(p2.getStock(), p1.getStock()));
        /* On compare p2 à p1 au lieu de p1 à p2 avoir d'avoir un tri décroissant ce qui est plus
         * logique lorsque l'on tri un stock */
        break;
      case "prix":
        articlesStock.sort(Comparator.comparing(Article::getPrixParJour));
        break;
      default:
        System.out.println("L'option de tri passée en paramètre est invalide");
        break;
    }
  }

  /**
   * Méthode permettant de comparer deux dates en prenant le mois et l'année.
   *
   * @param date1 Première date à comparer
   * @param date2 Deuxième date à comparer
   * @return Retourne vrai si les deux dates ont le même mois et la même année, sinon faux
   */
  public boolean samePeriod(LocalDate date1, LocalDate date2) {
    return date1.getMonth() == date2.getMonth() && date1.getYear() == date2.getYear();
  }

  /**
   * Cette méthode permet se savoir si un client est stocké dans la liste du magasin.
   *
   * @param client Le client à vérifier
   * @return True si le client est enregistré dans le magasin, false sinon
   */
  public boolean clientExist(Client client) {
    return clients.contains(client);
  }

  /**
   * Cette méthode permet de savoir si la location est possible en fonction du stock d'articles
   * disponibles.
   *
   * @param l1 La location a potentiellement ajouter au magasin
   * @return True si la location est possible, false sinon
   */
  public boolean locationPossible(Location l1) {
    HashMap<Article, Integer> stockDispo = getStockDispo(l1.getDdb(), l1.getDdf());
    if (stockDispo.isEmpty()) {
      System.out.println("Article non disponible");
      return false;
    }
    for (Article a1 : l1.getarticlesLoue().keySet()) {
      HashMap<Article, Integer> articlesLoues = l1.getarticlesLoue();
      if (articlesLoues != null && articlesLoues.get(a1) != null && stockDispo.get(a1) != null) {
        // si plus d'articles loué que disponibles
        if (stockDispo.get(a1) < articlesLoues.get(a1)) return false;
      } else return false;
    }
    return true;
  }

  /**
   * Ajoute une location à un client du magasin si possible.
   *
   * @param l1 La location a ajoutée
   * @param c1 Le client bénéficiaire de la location
   * @throws NotEnoughStockException the not enough stock
   */
  public void ajouterLocation(Location l1, Client c1) throws NotEnoughStockException {
    if (locationPossible(l1)) {
      if (clientExist(c1)) {
        clients.get(clients.indexOf(c1)).getLocations().add(l1);
      } else System.out.println("Le client n'existe pas.");
    } else
      throw new NotEnoughStockException("Pas assez d'articles en stock, annulation de la location");
  }

  /**
   * Cette méthode permet de calculer la recette des locations pour un mois donnée.
   *
   * @param fin Date de fin.
   * @return La recette du magasin pour la période renseignée
   */
  public int calculRecettePeriode(LocalDate fin) {
    int montantTotal = 0;
    for (Location l1 : getLocationFin(fin)) montantTotal += l1.getMontant();
    return montantTotal;
  }

  /**
   * Cette méthode permet de calculer la recette du magasin sur une période donnée.
   *
   * @param deb Date de début
   * @param fin Date de fin
   * @return La recette du magasin pour la période renseignée
   */
  public int calculRecettePeriode(LocalDate deb, LocalDate fin) {
    int montantTotal = 0;
    for (Location l1 : getRegistre(deb, fin)) montantTotal += l1.getMontant();
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
    LocalDate archiveFin = LocalDate.of(annee, mois, 1);
    try {
      Files.write(
          Paths.get(String.valueOf(annee) + String.valueOf(mois) + ".loc."),
          getLocationFin(archiveFin).toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Ajoute un client au magasin si il n'existe pas.
   *
   * @param cli Le client à ajouter
   */
  public void ajouterClient(Client cli) {
    if (clientExist(cli)) {
      System.out.println("Le client existe déjà : " + cli);
    } else {
      System.out.println("Ajout du client effectué : " + cli);
      clients.add(cli);
    }
  }
}
