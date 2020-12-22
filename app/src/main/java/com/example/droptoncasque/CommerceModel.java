package com.example.droptoncasque;

public class CommerceModel {
    private Integer id;
    private String nom;
    private String type;
    private String adresse;
    private MapsActivity.Pair<Double,Double> coord;
    private String contact_email;
    private String contact_telephone;
    private String url;

    public CommerceModel(Integer id, String nom, String type, String adresse, MapsActivity.Pair<Double, Double> coord, String contact_email, String contact_telephone, String url) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.coord = coord;
        this.contact_email= contact_email;
        this.contact_telephone = contact_telephone;
        this.url = url;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public MapsActivity.Pair<Double, Double> getCoord() {
        return coord;
    }

    public void setCoord(MapsActivity.Pair<Double, Double> coord) {
        this.coord = coord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_telephone() {
        return contact_telephone;
    }

    public void setContact_telephone(String contact_telephone) {
        this.contact_telephone = contact_telephone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Commerce{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", adresse='" + adresse + '\'' +
                ", coord=" + coord +
                ", contact_email='" + contact_email + '\'' +
                ", contact_telephone='" + contact_telephone + '\'' +
                '}';
    }
}
