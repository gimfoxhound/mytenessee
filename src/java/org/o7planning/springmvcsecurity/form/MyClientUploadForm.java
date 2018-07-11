
package org.o7planning.springmvcsecurity.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class MyClientUploadForm {

private String email;
private String prenom;
private String nom;
private String sex;
private String datenaissance;
private String remarque;
private String nickname;
private String taille;
private CommonsMultipartFile photo;

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

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public CommonsMultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(CommonsMultipartFile photo) {
        this.photo = photo;
    }


    
}
