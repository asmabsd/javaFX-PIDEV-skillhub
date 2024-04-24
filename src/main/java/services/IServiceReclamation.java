package services;


import entities.Reclamation;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IServiceReclamation<T>{




    public void ajouter(T t) throws SQLException;


    void supprimer(Reclamation reclamation) throws SQLException;

    public void modifier(T t) throws SQLException;

    public void supprimer(int id)throws SQLException;
   // public List<T> afficher(T t) throws SQLException;

    public T getOneById(int id);

    public Set<T> getAll();

    List<Reclamation> afficher() throws SQLException;
}