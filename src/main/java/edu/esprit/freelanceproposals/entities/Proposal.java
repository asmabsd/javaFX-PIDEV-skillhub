/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.freelanceproposals.entities;

import java.sql.Date;

/**
 *
 * @author Sirine
 */
public class Proposal {
    private int id;
    private String titre;
    private String description;
    private String client;
    private String freelancer;
    private double budget;
    private Date delai;
    private String statut;
    private Date dateSoumission;
    private Date dateDebut;
    private Date dateFin;
    private String fichiers;

    public Proposal() {
    }

    public Proposal(int id, String titre, String description, String client, String freelancer, double budget, Date delai, String statut, Date dateSoumission, Date dateDebut, Date dateFin, String fichiers) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.client = client;
        this.freelancer = freelancer;
        this.budget = budget;
        this.delai = delai;
        this.statut = statut;
        this.dateSoumission = dateSoumission;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.fichiers = fichiers;
    }

    public Proposal(String titre, String description, String client, String freelancer, double budget, Date delai, String statut, Date dateSoumission, Date dateDebut, Date dateFin, String fichiers) {
        this.titre = titre;
        this.description = description;
        this.client = client;
        this.freelancer = freelancer;
        this.budget = budget;
        this.delai = delai;
        this.statut = statut;
        this.dateSoumission = dateSoumission;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.fichiers = fichiers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(String freelancer) {
        this.freelancer = freelancer;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Date getDelai() {
        return delai;
    }

    public void setDelai(Date delai) {
        this.delai = delai;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(Date dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getFichiers() {
        return fichiers;
    }

    public void setFichiers(String fichiers) {
        this.fichiers = fichiers;
    }

    @Override
    public String toString() {
        return "Proposal{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", client=" + client + ", freelancer=" + freelancer + ", budget=" + budget + ", delai=" + delai + ", statut=" + statut + ", dateSoumission=" + dateSoumission + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", fichiers=" + fichiers + '}';
    }
}
