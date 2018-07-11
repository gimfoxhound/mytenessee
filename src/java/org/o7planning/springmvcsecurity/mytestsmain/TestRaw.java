
package org.o7planning.springmvcsecurity.mytestsmain;

import org.o7planning.springmvcsecurity.config.ApplicationContextConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestRaw {

    
    public static void main(String[] args){
    ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
    String myupload = (String)context.getBean("monChemin");
        System.out.println("Mon chemin upload est : "+ myupload );
    
    
    }
    

    
}
