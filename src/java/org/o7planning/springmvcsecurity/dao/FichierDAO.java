
package org.o7planning.springmvcsecurity.dao;

import java.util.List;
import org.o7planning.springmvcsecurity.entity.Fichier;
import org.o7planning.springmvcsecurity.model.FichierInfo;


public interface FichierDAO {

    public Fichier findFichier(String nom);
    public Fichier findFichier(int numfile);
    public FichierInfo findFichierInfo(String nom);
    public FichierInfo findFichierInfo(int numfile);
    public void saveFichier(FichierInfo fichierInfo);
    public List<FichierInfo> listFichierInfos();
    public void deleteFichier(String nom);
    public void deleteFichier(int numfile);
    
}
