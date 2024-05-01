package entities;


import java.util.Date;
import java.util.Objects;

public class Reclamation {
    private  int id;

    public Date getDate_reclamtion() {
        return date_reclamtion;
    }

    public void setDate_reclamtion(Date date_reclamtion) {
        this.date_reclamtion = date_reclamtion;
    }

   private String objet;
    private  String contenu;
    private String statut;
    private String reponse;
    private  Date date_reclamtion;
    private int id_freelencer;
    private  int id_user;


    public Reclamation(int id, String objet, String contenu, String statut, String reponse, Date date_reclamtion,int id_freelencer,int id_user) {
        this.id = id;
        this.objet = objet;
        this.contenu = contenu;
        this.statut = statut;
        this.reponse = reponse;
        this.date_reclamtion = date_reclamtion;
        this.id_freelencer = id_freelencer;
        this.id_user = id_user;
    }

    public Reclamation(int id, String objet, String contenu, String statut, String reponse) {
        this.id = id;
        this.objet = objet;
        this.contenu = contenu;
        this.statut = statut;
        this.reponse = reponse;
       // this.date_reclamtion = date_reclamtion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

   /* public LocalDate getDate_reclamtion() {
        return date_reclamtion;
    }

    public void setDate_reclamtion(LocalDate date_reclamtion) {
        this.date_reclamtion = date_reclamtion;
    }*/

    public Reclamation(String objet, String contenu, String statut, String reponse /*LocalDate date_reclamtion*/) {

        this.objet = objet;
        this.contenu = contenu;
        this.statut = statut;
        this.reponse = reponse;
        //this.date_reclamtion = date_reclamtion;
    }

    public Reclamation() {
    }

//    @Override
//    public String toString() {
//        return "Reclamation{" +
//                "id=" + id +
//                ", objet='" + objet + '\'' +
//                ", contenu='" + contenu + '\'' +
//                ", statut='" + statut + '\'' +
//                ", reponse='" + reponse + '\'' +
//                /*", date_reclamtion=" + date_reclamtion +*/
//                '}';
//    }


    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", objet='" + objet + '\'' +
                ", contenu='" + contenu + '\'' +
                ", statut='" + statut + '\'' +
                ", reponse='" + reponse + '\'' +
                ", date_reclamtion=" + date_reclamtion +
                ", id_freelencer=" + id_freelencer +
                ", id_user=" + id_user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reclamation that = (Reclamation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
