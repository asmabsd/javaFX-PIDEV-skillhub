package entities;

import java.util.Date;
import java.util.Objects;

public class actualite {
int id;
String titre;
String description;
String categorie;
Date date_publication;
String image;

    public actualite(int id, String titre, String description, String categorie, Date date_publication, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.date_publication = date_publication;
        this.image = image;
    }

    public actualite(String titre, String description, String categorie, Date date_publication, String image) {
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.date_publication = date_publication;
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

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "actualite{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", categorie='" + categorie + '\'' +
                ", date_publication=" + date_publication +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        actualite actualite = (actualite) o;
        return id == actualite.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
