
package org.o7planning.springmvcsecurity.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.o7planning.springmvcsecurity.dao.MesRequetes;
import org.o7planning.springmvcsecurity.entity.User;
import org.o7planning.springmvcsecurity.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;


public class UserInfoDAOImpl {

@Autowired
 private SessionFactory sessionFactory;

 public User findUser(String username){
     String sql = MesRequetes.getSELECTUSERNAME();
     Session session  = sessionFactory.getCurrentSession();
     Criteria crit = session.createCriteria(User.class);
     crit.add(Restrictions.eq("username", username));
     
     
     
     /*Query query = session.createQuery(sql);
     query.setParameter("username", userName);*/
     
     return (User)crit.uniqueResult();
 }
   
 public UserInfo findUserInfo(String username){
     User theUser = this.findUser(username);
     if(theUser==null){
         return null;
     }
     return new UserInfo(theUser.getUsername(), theUser.getPassword());
 }
 
 public List<UserInfo> listUserInfo(){
     String sql = MesRequetes.getSELECTALLUSERS();
     
     Session aSession = sessionFactory.getCurrentSession();
     Query aQuery = aSession.createQuery(sql);
     listedetouslesusers(aQuery.list());
     return aQuery.list();
 }
 
 private static void listedetouslesusers(List uneListe){
     
     System.out.println("Impression de tous les enregistrements de la listes");
     for(int j=0;j<uneListe.size();j++){
         System.out.println(uneListe.get(j));
     }
 }
 
 
 
/*public List<String> getUserRoles(String userName){
    String sql=MesRequetes.getSELECTROLEPERUSER();
    Session session = sessionFactory.getCurrentSession();
    Query aQuery = session.createQuery(sql);
    aQuery.setParameter("username", userName);
    List<String> roles = aQuery.list();
    return roles;
}    */

public void saveUser(UserInfo userInfo){
   String username = userInfo.getUsername();
   User usr = null;
   
   if(username!=null){
       usr= this.findUser(username);
   }
   
   boolean isNew = false;
   if(usr==null){
       isNew=true;
       usr = new User();
   }
   usr.setEnabled(true);
   usr.setUsername(userInfo.getUsername());
   usr.setPassword(userInfo.getPassword());
   
   
   if(isNew){
       Session aSession = this.sessionFactory.getCurrentSession();
       aSession.persist(usr);
   }
   
   
}

public void deleteUser(String username){
    User usr = this.findUser(username);
    if(usr!=null){
        this.sessionFactory.getCurrentSession().delete(usr);
    }
}


}
