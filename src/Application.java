import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/** The type Application. */
public final class Application {
  /** Constructeur d'application pour bloquer l'instanciation. */
  private Application() {}

  /**
   * Méthode de test du magasin. Z
   *
   * @param args Les arguments passés en paramètre
   */
  public static void main(String... args) throws IOException {

    Magasin magasin = new Magasin();
    magasin = remplir(magasin);

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String action = "none";
    while (!action.equals("6")) {
      System.out.println("Bienvenue dans votre magasin favori, que souhaitez-vous faire ?");
      System.out.println("    - 1) Afficher les articles");
      System.out.println("    - 2) Créer une location");
      System.out.println("    - 3) Archiver les locations du mois");
      System.out.println("    - 4) Calculer recette du mois");
      System.out.println("    - 5) Lancer jeu de test");
      System.out.println("    - 6) Quitter");

      System.out.print("Choix : ");
      action = reader.readLine();

      switch (action) {
        case "1":
          magasin.afficherStockInitial();
          break;
        case "2":
          BufferedReader readerLoc = new BufferedReader(new InputStreamReader(System.in));
          String actionLoc = "none";
          System.out.println("Pour quel client souhaitez-vous ajouter");
          List<Client> listeCli = magasin.getClients();
          int index = 1;
          for (Client client : listeCli) {
            System.out.println("    - " + index + " " + client.getNom() + " " + client.getPrenom());
            index++;
          }
          System.out.print("Choix : ");
          actionLoc = readerLoc.readLine();
          Client clientChoisi = listeCli.get(Integer.valueOf(actionLoc) - 1);

          String regex_date =
              "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)";

          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

          while (!actionLoc.matches(regex_date)) {
            System.out.println("- Date de début pour la location JJ/MM/AAAA :");
            actionLoc = readerLoc.readLine();
          }
          LocalDate localDeb = LocalDate.parse(actionLoc, formatter);
          actionLoc = "none";

          while (!actionLoc.matches(regex_date)) {
            System.out.println("- Date de fin pour la location JJ/MM/AAAA :");
            actionLoc = readerLoc.readLine();
          }

          LocalDate localFin = LocalDate.parse(actionLoc, formatter);
          Location location = new Location(localDeb, localFin);
          System.out.println("Quels articles souhaitez-vous ajouter à votre location ?");
          List<Article> listeArticle = magasin.getArticlesStock();
          index = 1;
          for (Article article : listeArticle) {
            System.out.println("    - " + index + " " + article.toString());
            index++;
          }
          System.out.print(
              "Choix des articles (chaque index d'articles séparé par une virgule ex : 1,2,2,3): ");
          actionLoc = readerLoc.readLine();
          List<String> articlesChoisi = Arrays.asList(actionLoc.split(","));

          for (String s : articlesChoisi) {
            Article articleChoisi = listeArticle.get(Integer.valueOf(s) - 1);
            location.ajouterArticle(articleChoisi, 1);
          }
          magasin.ajouterLocation(location, clientChoisi);
          System.out.println("Enregistrement de la location terminée, merci de votre visite");
          break;
        case "3":
          BufferedReader afficherLocReader = new BufferedReader(new InputStreamReader(System.in));
          String actionLocReader = "-1";
          int mois = -1;
          int annee = -1;

          while ((mois < 0) || (mois > 12)) {
            System.out.print(
                "Entrez le mois lequel vous souhaitez archiver les locations (par exemple 6 pour juin) : ");
            actionLocReader = afficherLocReader.readLine();
            mois = Integer.valueOf(actionLocReader);
          }

          actionLocReader = "-1";

          // Vérification int à 4 chiffres
          while ((int) (Math.log10(annee) + 1) != 4) {
            System.out.print(
                "Entrez l'année lequel vous souhaitez archiver les locations (par exemple 1996) : ");
            actionLocReader = afficherLocReader.readLine();
            annee = Integer.valueOf(actionLocReader);
          }

          magasin.archiveMois(mois, annee);
          break;
        case "4":
          BufferedReader recetteLocReader = new BufferedReader(new InputStreamReader(System.in));
          String recetteLoc = "-1";
          int moisRecette = -1;
          int anneeRecette = -1;

          while ((moisRecette < 0) || (moisRecette > 12)) {
            System.out.print(
                "Entrez le mois lequel vous souhaitez afficher la recette (par exemple 6 pour juin) : ");
            recetteLoc = recetteLocReader.readLine();
            moisRecette = Integer.valueOf(recetteLoc);
          }

          recetteLoc = "-1";

          // Vérification int à 4 chiffres
          while ((int) (Math.log10(anneeRecette) + 1) != 4) {
            System.out.print(
                "Entrez l'année lequel vous souhaitez afficher la recette (par exemple 1996) : ");
            recetteLoc = recetteLocReader.readLine();
            anneeRecette = Integer.valueOf(recetteLoc);
          }

          LocalDate dateRecette = LocalDate.of(anneeRecette, moisRecette, 30);

          System.out.println(
              "La recette du magasin pour le mois "
                  + moisRecette
                  + " et l'année "
                  + anneeRecette
                  + " est de "
                  + magasin.calculRecettePeriode(dateRecette)
                  + "€");
          break;
        case "5":
          jeuDeTest();
          break;
      }
    }
  }

  /**
   * Méthode permettant de remplir le stock et les clients du magasin.
   *
   * @param mag Le magasin à remplir.
   * @return Un magasin avec un stock d'article et des clients.
   */
  public static Magasin remplir(Magasin mag) {
    Article article1 = new FauteuilRoulant("AB7", "Medico", "NT7", 14.0, 45, 12.2, 12.3);
    Article article2 = new FauteuilRoulant("RNG", "Alpico", "RG6", 16.0, 14, 12.7, 11.3);

    Article article3 =
        new MatelasAir("1404", "KTM", "ME236", 14.0, 222, 12.2, 12.5, 14.0, 15.0, 12.0);
    Article article4 =
        new MatelasAir("DNTAD", "Rebo", "ME236", 14.0, 222, 12.2, 12.5, 14.0, 15.0, 12.0);

    Article article5 = new SouleveMalade("1725", "Rac", "ANS5", 14.0, 100, 12.2, 12.5);
    Article article6 = new SouleveMalade("AJ45", "Lu", "Prince", 14.0, 100, 12.2, 12.5);
    mag.ajouterArticleStock(article1, article2, article3, article4, article5, article6);

    Client cli1 = new Client("Jean", "Jean");
    Client cli2 = new Client("Tom", "Tom");
    Client cli3 = new Client("Epaul", "Hier");
    mag.ajouterClient(cli1);
    mag.ajouterClient(cli2);
    mag.ajouterClient(cli3);
    return mag;
  }

  /** Méthode permettant de lancer le jeu de test du magasin. */
  public static void jeuDeTest() {
    Magasin magasin = new Magasin();

    Article article1 = new FauteuilRoulant("AB7", "Medico", "NT7", 14.0, 45, 12.2, 12.3);
    Article article2 =
        new MatelasAir("1404", "ABCD", "chaise", 14.0, 222, 12.2, 12.5, 14.0, 15.0, 12.0);
    Article article3 = new SouleveMalade("1404", "ABCD", "chaise", 14.0, 222, 12.2, 12.5);
    /*
     Copie de l'article3 qui permet de vérifier que deux mêmes articles ajoutés dans le stock sont
     fusionnés et leurs deux stocks sont additionnés Ici, les deux articles SouleveMalade
     (article3 et article4) sont égaux, ils seront donc fusionné en un seul article avec une
     quantité de 444 (la somme de leurs deux quantités).
    */
    Article article4 = new SouleveMalade("1404", "ABCD", "chaise", 14.0, 222, 12.2, 12.5);

    /* Ajout des articles dans le stock du magasin */
    magasin.ajouterArticleStock(article1, article2, article3, article4);

    System.out.println("----------------------------------------------");
    System.out.println("# Stock initial des articles #");
    magasin.afficherStockInitial();
    System.out.println("----------------------------------------------");

    System.out.println("----------------------------------------------");
    System.out.println("# Stock initial des articles après tri par stock #");
    magasin.trierPar("stock");
    magasin.afficherStockInitial();
    System.out.println("----------------------------------------------");

    /* Création des dates à assossier aux locations. */
    LocalDate ddb = LocalDate.of(2017, 6, 1);
    LocalDate ddf = LocalDate.of(2017, 6, 30);

    LocalDate ddb2 = LocalDate.of(2017, 6, 1);
    LocalDate ddf2 = LocalDate.of(2017, 6, 12);

    /* Création d'une location. */
    Location location1 = new Location(ddb, ddf);
    Location location2 = new Location(ddb2, ddf2);

    /* Ajout des articles aux différentes locations. */
    location1.ajouterArticle(article1, 2);
    location1.ajouterArticle(article2, 4);
    location1.ajouterArticle(article3, 4);

    location2.ajouterArticle(article1, 12);
    location2.ajouterArticle(article2, 14);
    location2.ajouterArticle(article3, 4);

    /* Création du client et ajout de celui-ci dans la "bd" du magasin. */
    Client cli = new Client("Jean", "Pierre");
    magasin.ajouterClient(cli);

    /* Affectation des locations aux clients. */
    try {
      magasin.ajouterLocation(location1, cli);
    } catch (NotEnoughStockException e) {
      e.printStackTrace();
    }

    try {
      magasin.ajouterLocation(location2, cli);
    } catch (NotEnoughStockException e) {
      e.printStackTrace();
    }

    System.out.println("----------------------------------------------");
    System.out.println("# Afichage du stock dispo #");
    magasin
        .getStockDispo(ddb, ddf)
        .forEach(
            (k, v) ->
                System.out.println(
                    "Article : " + k.getClass().getName() + " Quantité restante : " + v));
    System.out.println("----------------------------------------------");

    System.out.println("----------------------------------------------");
    System.out.println("# On affiche la recette du mois 06 #");
    System.out.println(magasin.calculRecettePeriode(ddf) + " €");
    System.out.println("----------------------------------------------");

    /* Création de l'archive du magasin */
    magasin.archiveMois(6, 2017);
  }
}
