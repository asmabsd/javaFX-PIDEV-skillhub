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
public class Commentaire {
    private int id;
    private String auteur;
    private String commentaire;
    private Date datePublication;
    private int note;

    public Commentaire() {
    }

    public Commentaire(int id, String auteur, String commentaire, Date datePublication, int note) {
        this.id = id;
        this.auteur = auteur;
        this.commentaire = commentaire;
        this.datePublication = datePublication;
        this.note = note;
    }

    public Commentaire(String auteur, String commentaire, Date datePublication, int note) {
        this.auteur = auteur;
        this.commentaire = commentaire;
        this.datePublication = datePublication;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", auteur=" + auteur + ", commentaire=" + commentaire + ", datePublication=" + datePublication + ", note=" + note + '}';
    }
    
}
