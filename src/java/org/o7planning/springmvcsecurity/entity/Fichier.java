
package org.o7planning.springmvcsecurity.entity;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//CREATE TABLE `` (

@Entity
@Table(name = "fichier")
public class Fichier  implements Serializable{

private static final long serialVersionUID = -7893237204476214150L;

private int numfile; // 
private String namefile; //
private String remarque; //
private int taille; // 
private String photo; //
private String description;

    @Id
    @Column(name = "NUMFILE", length = Integer.SIZE, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getNumfile() {
        return numfile;
    }

    public void setNumfile(int numfile) {
        this.numfile = numfile;
    }

    @Column(name = "NAMEFILE", length = 550, nullable = true )
    public String getNamefile() {
        return namefile;
    }

    public void setNamefile(String namefile) {
        this.namefile = namefile;
    }

    @Column(name = "REMARQUE", length =255, nullable = true )
    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    @Column(name = "TAILLE", length = Integer.SIZE, nullable = true)
    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    @Column(name = "PHOTO", length = Integer.SIZE, nullable = true)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    @Column(name = "DESCRIPTION", length = 550, nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }






}
