package services;

<<<<<<< HEAD
import java.sql.SQLException;
import java.util.Set;

public interface IService <T>{
    public void ajouter(T t) throws SQLException;
=======
import java.util.Set;

public interface IService <T>{
    public void ajouter(T t);
>>>>>>> e344030054d549f5ae6c05ae55b38118fc572713

    public void modifier(T t);
    public void supprimer(int id);
    public T getOneById(int id);
    public Set<T> getAll();
<<<<<<< HEAD
}
=======
}
>>>>>>> e344030054d549f5ae6c05ae55b38118fc572713
