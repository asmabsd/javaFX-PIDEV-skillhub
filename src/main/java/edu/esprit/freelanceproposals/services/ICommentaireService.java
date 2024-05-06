/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.esprit.freelanceproposals.services;

import edu.esprit.freelanceproposals.entities.Commentaire;
import java.util.List;

/**
 *
 * @author Sirine
 */
public interface ICommentaireService {
    public void ajout(Commentaire c);
    public void modifier(Commentaire c);
    public void supprimer(int id);
    public Commentaire recherche(int id);
    public List<Commentaire> listeCommentaires();
    public List<Boolean> getReactionsByComment(int id);
}
