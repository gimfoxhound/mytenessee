
package org.o7planning.springmvcsecurity.dao;

import java.util.List;
import org.o7planning.springmvcsecurity.entity.Client;
import org.o7planning.springmvcsecurity.model.ClientInfo;


public interface ClientDAO {

public Client findClient(String prenom, String nom);
public Client findClient(String nickname);
public List<ClientInfo> listClientInfo();
public ClientInfo findClientInfo(String nickname);
public ClientInfo findClientInfo(String prenom, String nom);
public void saveClient(ClientInfo clientInfo);
public void deleteClient(String nickname);
public void deleteClient(String prenom, String nom);

    
}
