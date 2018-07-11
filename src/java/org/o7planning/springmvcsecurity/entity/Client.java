
package org.o7planning.springmvcsecurity.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//CREATE TABLE `` (
@Entity
@Table(name = "client")
public class Client implements Serializable {
private static final long serialVersionUID = -7893237204476214150L;
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

    //`` int(11) NOT NULL AUTO_INCREMENT, 
    @Id
    @Column(name = "NUMCLIENT", length = Integer.SIZE,nullable = false)
    public int getNumclient() {
        return numclient;
    }

    public void setNumclient(int numclient) {
        this.numclient = numclient;
    }

    
    @Column(name = "EMAIL", length = 255, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name = "PRENOM", length = 255,nullable = true)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(name = "NOM", length = 255, nullable = true)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    @Column(name = "SEX", length = 32, nullable = false)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    
    @Column(name = "DATENAISSANCE", nullable = true)
    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    
    @Column(name = "NICKNAME", length =255, nullable = true )
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    
    @Column(name = "TAILLE", length = Integer.SIZE, nullable = true )
    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    //`` varchar() DEFAULT NULL,
    @Column(name = "PHOTO", length = 1255, nullable = true)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    
    @Column(name = "REMARQUE", length = 255, nullable = true)
    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }






/**
 
 
  
  
  
  
  
  
  
  
  
  
  
)
 **/

    
    
}
