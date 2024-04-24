package entities;
import java.util.Date;
import java.util.Objects;

import java.time.LocalDateTime;

public class actualite {
   private int id;
   private String titre;
    private LocalDateTime date_publicaton;
    private String description;
    private String categorie;
    private String image;
    //private int likes=0;
   // private int dislikes=0;

    public actualite(int id, String titre, LocalDateTime date_publicaton, String description, String categorie, String image) {
        this.id = id;
        this.titre = titre;
        this.date_publicaton = date_publicaton;
        this.description = description;
        this.categorie = categorie;
        this.image = image;
    }

    public actualite() {
    }

    public actualite(String titre, LocalDateTime date_publicaton, String description, String categorie, String image) {
        this.titre = titre;
        this.date_publicaton = date_publicaton;
        this.description = description;
        this.categorie = categorie;
        this.image = image;
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

    public LocalDateTime getDate_publicaton() {
        return date_publicaton;
    }

    public void setDate_publicaton(LocalDateTime date_publicaton) {
        this.date_publicaton = date_publicaton;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public actualite(LocalDateTime date_publicaton, String description, String categorie, String image) {
        this.date_publicaton = date_publicaton;
        this.description = description;
        this.categorie = categorie;
        this.image = image;
    }

    @Override
    public String toString() {
        return "actualite{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", date_publicaton=" + date_publicaton +
                ", description='" + description + '\'' +
                ", categorie='" + categorie + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        actualite actualite = (actualite) o;
        return id == actualite.id && Objects.equals(titre, actualite.titre) && Objects.equals(date_publicaton, actualite.date_publicaton) && Objects.equals(description, actualite.description) && Objects.equals(categorie, actualite.categorie) && Objects.equals(image, actualite.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titre, date_publicaton, description, categorie, image);
    }
}
