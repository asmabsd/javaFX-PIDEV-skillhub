package entities;

import java.util.Date;
import java.util.Objects;

public class event {

    private int id;
    private int price;
    private int nb_participant;
    private String title;
    private String description;
    private String statut;

    private String location;
    private String image;
    private Date start_date;
    private Date end_date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }


    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getNb_participant() {
        return nb_participant;
    }

    public void setNb_participant(int nb_participant) {
        this.nb_participant = nb_participant;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public event( int id,int nb_participant,int price, String title,String description,String statut,String location,String image, Date start_date,Date end_date) {
        this.id = id;
        this.nb_participant = nb_participant;

        this.price = price;
        this.description= description;
        this.statut= statut;
        this.location = location;
        this.image= image;
        this.start_date= start_date;
        this.end_date= end_date;
    }



    public event( int nb_participant,int price, String title,String description,String statut,String location,String image, Date start_date,Date end_date) {

        this.nb_participant = nb_participant;
        this.price = price;
        this.description= description;
        this.statut= statut;
        this.location = location;
        this.image= image;
        this.start_date= start_date;
        this.end_date= end_date;
    }
    public event() {
    }


    @Override
    public String toString() {
        return "event{" +
                "id=" + id +
                ", price=" + price +
                ", nb_participant=" + nb_participant +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", statut='" + statut + '\'' +
                ", location='" + location + '\'' +
                ", image='" + image + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        event event = (event) o;
        return id == event.id && price == event.price && nb_participant == event.nb_participant && Objects.equals(title, event.title) && Objects.equals(description, event.description) && Objects.equals(statut, event.statut) && Objects.equals(location, event.location) && Objects.equals(image, event.image) && Objects.equals(start_date, event.start_date) && Objects.equals(end_date, event.end_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, nb_participant, title, description, statut, location, image, start_date, end_date);
    }
}


