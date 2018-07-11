
package org.o7planning.springmvcsecurity.model;

import java.sql.Date;


public class ClientInfo {

private int numclient; //int(11)
private String email;
private String prenom;
private String nom;
private String sex;
private Date datenaissance;
private String nickname;
private int taille;
private String photo;
private String remarque;

    public ClientInfo() {
    }

    public ClientInfo(int numclient, String email, String prenom, String nom, String sex, Date datenaissance, String nickname, int taille, String photo, String remarque) {
        this.numclient = numclient;
        this.email = email;
        this.prenom = prenom;
        this.nom = nom;
        this.sex = sex;
        this.datenaissance = datenaissance;
        this.nickname = nickname;
        this.taille = taille;
        this.photo = photo;
        this.remarque = remarque;
    }


  

    public int getNumclient() {
        return numclient;
    }

    public void setNumclient(int numclient) {
        this.numclient = numclient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }




}
