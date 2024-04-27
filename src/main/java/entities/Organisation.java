package entities;

public class Organisation {
    private int id;
    private String nom;
    private String adresse;
    private int telephone;
    private String email;
    private String contact;
    private String domaine_activite;

    public Organisation(int id, String nom, String adresse, int telephone, String email, String contact, String domaine_activite) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.contact = contact;
        this.domaine_activite = domaine_activite;
    }

    public Organisation(String nom, String adresse, int telephone, String email, String contact, String domaine_activite) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.contact = contact;
        this.domaine_activite = domaine_activite;
    }

    public Organisation(int organisationId) {
    }

    public Organisation() {
    }

    public Organisation(String organisationIdValue) {
        this.id = Integer.parseInt(organisationIdValue);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return this.contact;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getTelephone() {
        return this.telephone;
    }

    public String getDomaine_activite() {
        return this.domaine_activite;
    }

    public void setDomaine_activite(String domaine_activite) {
        this.domaine_activite = domaine_activite;
    }

    public String toString() {
        return "Organisation{id=" + this.id + ", nom=" + this.nom + ", adresse =" + this.adresse + ", telephone=" + this.telephone + ", email='" + this.email + "', contact='" + this.contact + "', domaine_activite='" + this.domaine_activite + "'}";
    }
}
