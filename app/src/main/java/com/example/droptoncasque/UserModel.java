package com.example.droptoncasque;

public class UserModel {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private Boolean fonction;

    public UserModel(int id, String nom, String prenom, String email, Boolean fonction) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.fonction = fonction;
    }

    public UserModel() {
        this.id = -1;
        this.nom = null;
        this.prenom = null;
        this.email = null;
        this.fonction = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getFonction() {
        return fonction;
    }

    public void setFonction(Boolean fonction) {
        this.fonction = fonction;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", fonction='" + fonction + '\'' +
                '}';
    }
}