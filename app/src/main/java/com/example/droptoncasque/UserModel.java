package com.example.droptoncasque;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class UserModel {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private Boolean fonction; //true == particulier, false == commerçant
    private ArrayList<Integer> favoris;

    public UserModel(int id, String nom, String prenom, String email, Integer fonction, String favs) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;

        if(fonction == 1){
            this.fonction = true;
        }else{
            this.fonction = false;
        }

        ArrayList<Integer> favoris = new ArrayList<>();
        if (favs.equals("")){
            favoris = null;
        }else {
            ArrayList<String> arr = new ArrayList<String>(Arrays.asList(favs.split(",")));
            for (String currentId : arr) {
                favoris.add(Integer.parseInt(currentId));
            }
        }
        this.favoris = favoris;
    }

    public UserModel() {
        this.id = -1;
        this.nom = null;
        this.prenom = null;
        this.email = null;
        this.fonction = null;
        this.favoris = null;
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

    public ArrayList<Integer> getFavoris() {
        return favoris;
    }

    public void setFavoris(ArrayList<Integer> favoris) {
        this.favoris = favoris;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", fonction=" + fonction +
                ", favoris=" + favoris +
                '}';
    }
}
