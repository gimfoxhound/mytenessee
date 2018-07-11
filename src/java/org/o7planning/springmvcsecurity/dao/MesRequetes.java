
package org.o7planning.springmvcsecurity.dao;

import org.o7planning.springmvcsecurity.entity.Applicant;
import org.o7planning.springmvcsecurity.entity.Client;
import org.o7planning.springmvcsecurity.entity.Fichier;
import org.o7planning.springmvcsecurity.entity.User;
import org.o7planning.springmvcsecurity.entity.UserRole;
import org.o7planning.springmvcsecurity.model.ApplicantInfo;
import org.o7planning.springmvcsecurity.model.ClientInfo;
import org.o7planning.springmvcsecurity.model.FichierInfo;
import org.o7planning.springmvcsecurity.model.UserInfo;


public class MesRequetes {
    
    private static final String SELECTUSERNAME = "Select new " + UserInfo.class.getName() + "(u.username,u.password) from " + User.class.getName() + " u where u.username = :username ";
    private static final String SELECTROLEPERUSER= "Select r.userRole from "+ UserRole.class.getName() + " r where r.user.username = :username ";
    private static final String SELECTLISTOFAPPLICANTS= "Select new " + ApplicantInfo.class.getName()+ "(a.id, a.name, a.email, a.gender, a.position, a.skills) from " + Applicant.class.getName() + " a ";
    private static final String SELECTALLUSERS= "Select new " + UserInfo.class.getName() + "(u.username,u.password) from " + User.class.getName()+" u";
               
    private static final String SELECTALLCLIENTS= "Select new " + ClientInfo.class.getName()//
                + "(cli.NUMCLIENT, cl.EMAIL, cl.PRENOM, cli.NOM, cl.SEX, cl.DATENAISSANCE, cli.REMARQUE, cli.NICKNAME, cli.TAILLE, cli.PHOTO) "//
                + " from " + Client.class.getName() + " cli ";
            
    
    private static final String SELECTALLFICHIERS= "select new "+ FichierInfo.class.getName() +"(fichier.numfile, fichier.namefile, fichier.remarque, fichier.taille, fichier.photo, fichier.description)"  +" from "+ Fichier.class.getName()+" fichier";
    
    public static String getSELECTUSERNAME() {
        return SELECTUSERNAME;
    }

    public static String getSELECTROLEPERUSER() {
        return SELECTROLEPERUSER;
    }

    public static String getSELECTLISTOFAPPLICANTS() {
        return SELECTLISTOFAPPLICANTS;
    }

    public static String getSELECTALLUSERS() {
        return SELECTALLUSERS;
    }

    public static String getSELECTALLCLIENTS() {
        return SELECTALLCLIENTS;
    }

    public static String getSELECTALLFICHIERS() {
        return SELECTALLFICHIERS;
    }
    
    
    
    
    

    
    
    
}
