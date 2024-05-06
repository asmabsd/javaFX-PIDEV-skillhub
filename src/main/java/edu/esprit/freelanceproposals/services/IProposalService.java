/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.esprit.freelanceproposals.services;

import edu.esprit.freelanceproposals.entities.Proposal;
import java.util.List;

/**
 *
 * @author Sirine
 */
public interface IProposalService {
    public void ajout(Proposal p);
    public void modifier(Proposal p);
    public void supprimer(int id);
    public Proposal recherche(int id);
    public List<Proposal> listeProposals();
}
