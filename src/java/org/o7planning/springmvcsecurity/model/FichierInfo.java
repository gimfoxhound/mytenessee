
package org.o7planning.springmvcsecurity.model;


public class FichierInfo {

private int numfile; // 
private String namefile; //
private String remarque; //
private int taille; // 
private String photo; //
private String description;

    public FichierInfo() {
    }

    public FichierInfo(int numfile, String namefile, String remarque, int taille, String photo, String description) {
        this.numfile = numfile;
        this.namefile = namefile;
        this.remarque = remarque;
        this.taille = taille;
        this.photo = photo;
        this.description = description;
    }

    public int getNumfile() {
        return numfile;
    }

    public void setNumfile(int numfile) {
        this.numfile = numfile;
    }

    public String getNamefile() {
        return namefile;
    }

    public void setNamefile(String namefile) {
        this.namefile = namefile;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "("+this.getNamefile()+", "+this.getDescription()+", "+ this.getRemarque()+", "+this.getTaille()+")";
    }




    
}
