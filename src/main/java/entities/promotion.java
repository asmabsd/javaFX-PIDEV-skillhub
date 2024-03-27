package entities;

import java.util.Objects;

public class promotion {


    private int id;
    private int event_id;
    private String qr_code;
    private int discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }


    public promotion(int event_id,int discount,String qr_code) {

        this.event_id = event_id;
        this.qr_code = qr_code;
        this.discount = discount;
    }
    public promotion(int id,int event_id,int discount,String qr_code) {
        this.id = id;
        this.event_id = event_id;
        this.qr_code = qr_code;
        this.discount = discount;
    }


    public promotion() {
    }

    @Override
    public String toString() {
        return "promotion{" +
                "id=" + id +
                ", event_id=" + event_id +
                ", qr_code='" + qr_code + '\'' +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        promotion promotion = (promotion) o;
        return id == promotion.id && event_id == promotion.event_id && discount == promotion.discount && Objects.equals(qr_code, promotion.qr_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event_id, qr_code, discount);
    }
}
