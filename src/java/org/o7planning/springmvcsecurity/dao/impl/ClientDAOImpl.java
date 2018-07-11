
package org.o7planning.springmvcsecurity.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.o7planning.springmvcsecurity.dao.ClientDAO;
import org.o7planning.springmvcsecurity.dao.MesRequetes;
import org.o7planning.springmvcsecurity.entity.Client;
import org.o7planning.springmvcsecurity.model.ClientInfo;
import org.springframework.beans.factory.annotation.Autowired;


public class ClientDAOImpl implements ClientDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Client findClient(String nickname) {
        
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Client.class);
        crit.add(Restrictions.eq("nickname", nickname));
        return (Client)crit.uniqueResult();
        
    }

     @Override
    public ClientInfo findClientInfo(String nickname) {
        Client client = this.findClient(nickname);
        if(client==null){
            return null;
        }
        return new ClientInfo(client.getNumclient(), client.getEmail(), client.getPrenom(), client.getNom(),
                client.getSex(), client.getDatenaissance(), client.getNickname(), 
                client.getTaille(), client.getPhoto(), client.getRemarque());
        
    }
    @Override
    public List<ClientInfo> listClientInfo() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(MesRequetes.getSELECTALLCLIENTS());
        return query.list();
    }
    @Override
    public void saveClient(ClientInfo clientInfo) {
        String nickname = clientInfo.getNickname();
        Client client = null;
        if(nickname!=null){
            client = this.findClient(nickname);
        }
        boolean isNew = false;
        if(client==null){
            isNew = true;
            client = new Client();
        }
        client.setPrenom(clientInfo.getPrenom());
        client.setNom(clientInfo.getNom());
        client.setEmail(clientInfo.getEmail());
        client.setSex(clientInfo.getSex());
        client.setDatenaissance(clientInfo.getDatenaissance());
        client.setRemarque(clientInfo.getRemarque());
        client.setTaille(clientInfo.getTaille());
        client.setPhoto(clientInfo.getPhoto());
       if(isNew){
           Session session = this.sessionFactory.getCurrentSession();
           session.persist(client);
       }        
        
        
    }
    
    @Override
    public void deleteClient(String nickname) {
        Client client = this.findClient(nickname);
        if(client!=null){
            this.sessionFactory.getCurrentSession().delete(client);
        }
    }
    
    
    
    @Override
    public Client findClient(String prenom, String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void deleteClient(String prenom, String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public ClientInfo findClientInfo(String prenom, String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
    
    
}
