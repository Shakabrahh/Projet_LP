import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
  private final List<Location> locations;
  private String nom;
  private String prenom;

  /**
   * Constructeur de Client
   *
   * @param nom Nom du client
   * @param prenom Prénom du client
   */
  public Client(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
    this.locations = new ArrayList<>();
  }

  /**
   * Getter de nom
   *
   * @return Nom du client
   */
  public String getNom() {
    return this.nom;
  }

  /**
   * Setter de l'atribut nom
   *
   * @param nom Nom du client
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Getter de l'attribut prénom
   *
   * @return Prénom du client
   */
  public String getPrenom() {
    return this.prenom;
  }

  /**
   * Setter du prénom
   *
   * @param prenom Prénom du client
   */
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  /**
   * Méthode d'affichage des attributs du client
   *
   * @return Attributs du client sous la forme d'une chaine de caractère
   */
  @Override
  public String toString() {
    return "Client{" + "nom='" + this.nom + '\'' + ", prenom='" + this.prenom + '\'' + '}';
  }

  /**
   * Test l'égalité de deux instances de Client
   *
   * @param o Objet à tester
   * @return Vrai si les deux Clients sont les mêmes, faux autrement
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Client)) return false;
    Client client = (Client) o;
    return Objects.equals(this.nom, client.nom) && Objects.equals(this.prenom, client.prenom);
  }

  @Override
  public int hashCode() {
    int result = this.nom != null ? this.nom.hashCode() : 0;
    result = 31 * result + (this.prenom != null ? this.prenom.hashCode() : 0);
    result = 31 * result + this.locations.hashCode();
    return result;
  }

  /**
   * Getter location
   *
   * @return La liste de locations du client
   */
  public List<Location> getLocations() {
    return this.locations;
  }

  /** Affiche les locations du client */
  public void afficherLocations() {
    this.locations.forEach(Location::afficherArticles);
  }
}
