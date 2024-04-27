package entities;

import java.sql.Date;


public class Contrat {
    private int id;
    private Date date_debut;
    private Date date_fin;
    private int montant;
    private String statut;
    private String projet;
    private String freelancer;
    private Organisation organisation;
    private User user;
    private Date date_creation;
    private String description;

    public User getUser_id() {
        return this.user;
    }

    public void setUser_id(User user) {
        this.user = user;
    }

    public Contrat() {
    }

    public Contrat(int id, Date date_debut, Date dateFin, int montant, String statut, String projet, String freelancer, Organisation organisation, User user, Date dateCreation, String description) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = dateFin;
        this.montant = montant;
        this.statut = statut;
        this.projet = projet;
        this.freelancer = freelancer;
        this.organisation = organisation;
        this.user = user;
        this.date_creation = dateCreation;
        this.description = description;
    }

    public Contrat(Date date_debut, Date dateFin, int montant, String statut, String projet, String freelancer, Organisation organisation, User user, Date dateCreation, String description) {
        this.date_debut = date_debut;
        this.date_fin = dateFin;
        this.montant = montant;
        this.statut = statut;
        this.projet = projet;
        this.freelancer = freelancer;
        this.organisation = organisation;
        this.user = user;
        this.date_creation = dateCreation;
        this.description = description;
    }

    public Organisation getOrganisation_id() {
        return this.organisation;
    }

    public void setOrganisation_id(Organisation organisation) {
        this.organisation = organisation;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return this.date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return this.date_fin;
    }

    public void setDate_fin(Date dateFin) {
        this.date_fin = dateFin;
    }

    public int getMontant() {
        return this.montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getStatut() {
        return this.statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getProjet() {
        return this.projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public String getFreelancer() {
        return this.freelancer;
    }

    public void setFreelancer(String freelancer) {
        this.freelancer = freelancer;
    }

    public Date getDate_creation() {
        return this.date_creation;
    }

    public void setDate_creation(Date dateCreation) {
        this.date_creation = dateCreation;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "Contrat{id=" + this.id + ", dateDebut=" + this.date_debut + ", dateFin=" + this.date_fin + ", montant=" + this.montant + ", statut='" + this.statut + "', projet='" + this.projet + "', freelancer='" + this.freelancer + "', organisationId=" + this.organisation + ", userId=" + this.user + ", dateCreation=" + this.date_creation + ", description='" + this.description + "'}";
    }
}

