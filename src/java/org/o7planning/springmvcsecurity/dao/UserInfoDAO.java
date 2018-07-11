
package org.o7planning.springmvcsecurity.dao;


import java.util.List;
 
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.springmvcsecurity.entity.User;
import org.o7planning.springmvcsecurity.entity.UserRole;
import org.o7planning.springmvcsecurity.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserInfoDAO {

 @Autowired
 private SessionFactory sessionFactory;

    public UserInfoDAO() {
    }
    
  /*public User findUser(String username){
   Session session = sessionFactory.getCurrentSession();
   Criteria crit = session.createCriteria(user)
      
      
  }  */
    

 public UserInfo findUserInfo(String username){
     String sql = MesRequetes.getSELECTUSERNAME();
     Session session  = sessionFactory.getCurrentSession();
     Query query = session.createQuery(sql);
     query.setParameter("username", username);
     
     return (UserInfo)query.uniqueResult();
 }
   
public List<String> getUserRoles(String username){
    String sql=MesRequetes.getSELECTROLEPERUSER();
    Session session = sessionFactory.getCurrentSession();
    Query aQuery = session.createQuery(sql);
    aQuery.setParameter("username", username);
    List<String> roles = aQuery.list();
    return roles;
}

public void saveUser(UserInfo userInfo){
    String username = userInfo.getUsername();
    User usr = null;
    /*if(username!=null){
        return "";
    }*/
    //return "";
    
}



 
}
