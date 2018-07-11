
package org.o7planning.springmvcsecurity.authentication;


import java.util.ArrayList;
import java.util.List;
 
import org.o7planning.springmvcsecurity.dao.UserInfoDAO;
import org.o7planning.springmvcsecurity.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserInfoDAO userInfoDAO;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserInfo userInfo = userInfoDAO.findUserInfo(username);
        System.out.println("UserInfo--->"+userInfo);
        
        if(userInfo==null){
            throw new UsernameNotFoundException("User "+ username+ " was not found in database");
        }
        
        List<String> roles = userInfoDAO.getUserRoles(username);
        List<GrantedAuthority> grandList = new ArrayList<GrantedAuthority>();
        if(roles!=null){
            for(String role:roles){
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role);
                grandList.add(authority);
            }
            
            
        }
         UserDetails userDetails = (UserDetails) new User(userInfo.getUsername(), //
                userInfo.getPassword(),grandList);
 
         return userDetails; 
    }
    
    
    
}
