import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** The type Client. */
public class Client {
  private final List<Location> locations;
  private String nom;
  private String prenom;

  /**
   * Constructeur de Client.
   *
   * @param nom Nom du client
   * @param prenom Prénom du client
   */
  public Client(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
    locations = new ArrayList<>();
  }

  /**
   * Getter de nom.
   *
   * @return Nom du client
   */
  public String getNom() {
    return nom;
  }

  /**
   * Setter de l'atribut nom.
   *
   * @param nom Nom du client
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Getter de l'attribut prénom.
   *
   * @return Prénom du client
   */
  public String getPrenom() {
    return prenom;
  }

  /**
   * Setter du prénom.
   *
   * @param prenom Prénom du client
   */
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  /**
   * Méthode d'affichage des attributs du client.
   *
   * @return Attributs du client sous la forme d'une chaine de caractère
   */
  @Override
  public String toString() {
    return "Client{" + "nom='" + nom + '\'' + ", prenom='" + prenom + '\'' + '}';
  }

  /**
   * Test l'égalité de deux instances de Client.
   *
   * @param obj Objet à tester
   * @return Vrai si les deux Clients sont les mêmes, faux autrement
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Client)) return false;
    Client client = (Client) obj;
    return Objects.equals(nom, client.nom) && Objects.equals(prenom, client.prenom);
  }

  @Override
  public int hashCode() {
    int result = nom != null ? nom.hashCode() : 0;
    result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
    result = 31 * result + locations.hashCode();
    return result;
  }

  /**
   * Getter location.
   *
   * @return La liste de locations du client
   */
  public List<Location> getLocations() {
    return locations;
  }

  /** Affiche les locations du client. */
  public void afficherLocations() {
    locations.forEach(Location::afficherArticles);
  }
}
