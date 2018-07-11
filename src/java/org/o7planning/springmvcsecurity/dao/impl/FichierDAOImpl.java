
package org.o7planning.springmvcsecurity.dao.impl;


import java.util.List;
import java.util.UUID;
 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.o7planning.springmvcsecurity.dao.FichierDAO;
import org.o7planning.springmvcsecurity.dao.MesRequetes;
import org.o7planning.springmvcsecurity.entity.Fichier;
import org.o7planning.springmvcsecurity.model.FichierInfo;
import org.springframework.beans.factory.annotation.Autowired;

public class FichierDAOImpl implements FichierDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Fichier findFichier(String nom) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Fichier.class);
        crit.add(Restrictions.eq("namefile", nom));
        return (Fichier)crit.uniqueResult();
    }

    @Override
    public Fichier findFichier(int numfile) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Fichier.class);
        crit.add(Restrictions.eq("numfile", numfile));
        return (Fichier)crit.uniqueResult();
    }
    

    @Override
    public FichierInfo findFichierInfo(String nom) {
        Fichier fichier = this.findFichier(nom);
        if(fichier==null){
            return null;
        }
        return new FichierInfo(fichier.getNumfile(), fichier.getNamefile(), fichier.getRemarque(), 
                fichier.getTaille(), fichier.getPhoto(), fichier.getDescription());
    }

    @Override
    public FichierInfo findFichierInfo(int numfile) {
        Fichier fichier = this.findFichier(numfile);
        if(fichier==null){
            return null;
        }
        return new FichierInfo(fichier.getNumfile(), fichier.getNamefile(), fichier.getRemarque(), 
                fichier.getTaille(), fichier.getPhoto(), fichier.getDescription());
    }
    
    
      @Override
    public List<FichierInfo> listFichierInfos() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MesRequetes.getSELECTALLFICHIERS());
        return query.list();
    }
    
    @Override
    public void saveFichier(FichierInfo fichierInfo) {
        String nom = fichierInfo.getNamefile();
        Fichier fichier =null;
        if(nom!=null){
            fichier = this.findFichier(nom);
        }
        boolean isNew = false;
        if(fichier==null){
            isNew= true;
            fichier = new Fichier();
        }
     fichier.setNumfile(fichierInfo.getNumfile());
     fichier.setNamefile(fichierInfo.getNamefile());
     fichier.setRemarque(fichierInfo.getRemarque());
     fichier.setTaille(fichierInfo.getTaille());
     fichier.setPhoto(fichierInfo.getPhoto());
     fichier.setDescription(fichierInfo.getDescription());
     
     if(isNew){
         Session session = this.sessionFactory.getCurrentSession();
         session.persist(fichier);
     }
    }


    
    
    @Override
    public void deleteFichier(String nom) {
        Fichier fichier = this.findFichier(nom);
        if(fichier!=null){
            this.sessionFactory.getCurrentSession().delete(fichier);
        }
    }

    @Override
    public void deleteFichier(int numfile) {
        Fichier fichier = this.findFichier(numfile);
        if(fichier!=null){
            this.sessionFactory.getCurrentSession().delete(fichier);
        }
    }
    
    
    
    
    
    
}
