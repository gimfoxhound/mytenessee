
package org.o7planning.springmvcsecurity.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
 
import javax.servlet.http.HttpServletRequest;
import org.o7planning.springmvcsecurity.dao.FichierDAO;
import org.o7planning.springmvcsecurity.entity.Fichier;
 
import org.o7planning.springmvcsecurity.form.MyUploadForm;
import org.o7planning.springmvcsecurity.model.FichierInfo;
import org.o7planning.springmvcsecurity.utilitaires.MyUtilsDivers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;


@Controller
@Transactional
public class MyFileUploadController implements ServletContextAware{
    
 private ServletContext context;   
 
     @Autowired   
 private FichierDAO fichierDAO; 
     
 MyUploadForm myUploadForm = new MyUploadForm();    
 //HttpServletRequest request;
  
 @InitBinder
 public void initBinder(WebDataBinder dataBinder){
     Object target = dataBinder.getTarget();
     if(target==null){
         return;
     }
     System.out.println("Target ----> "+ target);
     if(target.getClass()==MyUploadForm.class){
         //register to handle the conversion between the multipart object and byte array
         dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
     }    
 }
 
  
 
 
 //get : showupload  form page
// @RequestMapping(value = "/uploadOneFile", method = RequestMethod.GET)
 private String uploadOneFileHandler(Model model){
   //MyUploadForm myUploadForm = new MyUploadForm();
   model.addAttribute("myUploadForm", myUploadForm);
  model.addAttribute("MyTitle", "Upload one File");  
  
  //this.userDAO.saveUser(userInfo);
   return "uploadOneFile" ;
 }

@RequestMapping(value="/editFichier")
    public String editFichier(Model model,HttpServletRequest request,
            @RequestParam("numfile") String numfile,
            @ModelAttribute ("myUploadForm") MyUploadForm myUploadForm ){
        
        //MyUploadForm myUploadForm = new MyUploadForm();
        System.out.println("Numfile egale --->" + numfile);
        FichierInfo fichierInfo=null;
        if(numfile!=null){
            fichierInfo = this.fichierDAO.findFichierInfo(Integer.parseInt(numfile));
            System.out.println("fichierinfo --->" +fichierInfo);
        }
        if(fichierInfo==null){
            return "redirect:/fichierList";
        }
        //return this.uploadOneFileHandlerPost(request, model, myUploadForm, fichierInfo); renvoi null pointerexception
        System.out.println("le fichier info est il null ? ---> " + fichierInfo.getNamefile());
        return this.uploadOneFileHandler(model, fichierInfo);
        //return this.doUpload(request, model, fichierInfo, myUploadForm,"rien");
        //return "";
    } 
 
@RequestMapping(value = "/uploadOneFile", method = RequestMethod.GET)
 public String uploadOneFileHandler(Model model, FichierInfo fichierInfo){
   //MyUploadForm myUploadForm = new MyUploadForm();
  model.addAttribute("myUploadForm", myUploadForm);
  model.addAttribute("MyTitle", "Upload one File");  
  
  //this.userDAO.saveUser(userInfo);
  return "uploadOneFile" ;
 } 

 
 
    
//post : do upload
@RequestMapping(value = "/uploadOneFile", method = RequestMethod.POST) 
public String uploadOneFileHandlerPost(HttpServletRequest request, Model model,
        @ModelAttribute("myUploadForm") MyUploadForm myUploadForm ){

   // System.out.println("on est dans la methode uploadOneFileHandlerPost on se dirige vers----> DoUpload");
    model.addAttribute("MyTitle", "Upload one File");
    return this.doUpload(request, model, myUploadForm);
    
}

@RequestMapping(value = "/uploadOneFileEdit", method = RequestMethod.POST) 
public String uploadOneFileHandlerPost(HttpServletRequest request, Model model,
        @ModelAttribute("myUploadForm") MyUploadForm myUploadForm, FichierInfo fichierInfo ){

    model.addAttribute("MyTitle", "Upload one File");
    //System.out.println("on est dans la methode uploadOneFileHandlerPost on se dirige vers----> DoUpload");
    return this.doUpload(request, model, myUploadForm);
    
}


 
//Get: Show upload form page
@RequestMapping(value = "/uploadMultiFile", method = RequestMethod.GET)
public String uploadMultiFileHandler(Model model){
 
   // MyUploadForm myUploadForm = new MyUploadForm();
    model.addAttribute("myUploadForm", myUploadForm);
    model.addAttribute("MyTitle", "Upload several Files");
    return "uploadMultiFile";
}
//post: do upload
@RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
public String uploadMultiFileHandlerPost(HttpServletRequest request, Model model,
        @ModelAttribute ("myUploadForm") MyUploadForm myUploadForm ){
    model.addAttribute("MyTitle", "Upload several Files");
    return this.doUpload(request, model, myUploadForm);
}
 
private String uploadMultiFileHandlerPost(HttpServletRequest request, Model model,
        @ModelAttribute ("myUploadForm") MyUploadForm myUploadForm, FichierInfo fichierInfo ){
    model.addAttribute("MyTitle", "Upload several Files");
    return this.doUpload(request, model, myUploadForm);
}

private String doUpload(HttpServletRequest request, Model model, //
          MyUploadForm myUploadForm) {
 
      System.out.println("Dans la methode doUpload");     
      String description = myUploadForm.getDescription();
      System.out.println("Description: " + description);
      FichierInfo unfich; //= new FichierInfo();
      int taille=0;
      String name="";
      String thename="";
      //unfich.setDescription(description);
 
     ServletContext servletContext = request.getSession().getServletContext();
     String myphotos ="/myupload-3";
      
      // Root Directory.
      String uploadRootPath = request.getServletContext().getRealPath("/WEB-INF/Img");
      
      
      System.out.println("uploadRootPath=" + uploadRootPath);
      //A efacer de suite 
      System.out.println("MyUploadForm description --->" + myUploadForm.getDescription());
 
      File uploadRootDir = new File(uploadRootPath);
      //
      // Create directory if it not exists.
      if (!uploadRootDir.exists()) {
          uploadRootDir.mkdirs();
      }
      CommonsMultipartFile[] fileDatas = myUploadForm.getFileDatas();
      //
      List<File> uploadedFiles = new ArrayList<File>();
      for (CommonsMultipartFile fileData : fileDatas) {
 
          // Client File Name
          name = fileData.getOriginalFilename();
          taille= (int)fileData.getSize();
          System.out.println("Client File Name = " + name);
 
          if (name != null && name.length() > 0) {
              try {
                  // Create the file on server
                  File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
 
      
                  // Stream to write data to file in server.
                  BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                  stream.write(fileData.getBytes());
                  stream.close();
                  //
                  uploadedFiles.add(serverFile);
                  System.out.println("Write file: " + serverFile);
                  
                  //stream.close(); element  ajoute mais ça ne marche 
              } catch (Exception e) {
                  System.out.println("Error Write file: " + name);
              }
          }
      }
      //Insertion des infos dans la DB
      unfich= new FichierInfo();
      for(File afile: uploadedFiles){
          
          if(description==null | description.equals("")){
              description = afile.getName();
          }
          unfich.setNamefile(afile.getName());
          unfich.setRemarque(afile.getName());
          unfich.setTaille((int)afile.length());
          unfich.setDescription(description);
          this.fichierDAO.saveFichier(unfich);
      }
      
      /*unfich.setNamefile(name);
      unfich.setRemarque(name);
      unfich.setTaille(taille);*/
      
      model.addAttribute("description", description);
      model.addAttribute("uploadedFiles", uploadedFiles);
      //this.fichierDAO.saveFichier(unfich);
      return "uploadResult";
  }

private String doUpload(HttpServletRequest request, Model model, //
           FichierInfo fichierInfo, MyUploadForm myUploadForm
        , String qqch) {
 
    //Toute la methode a effacer pour l'edition du fichier
      String description = fichierInfo.getDescription();
      if(fichierInfo.getDescription()==null | fichierInfo.getDescription().equals("")){
          description = fichierInfo.getNamefile()+ fichierInfo.getRemarque();
      }
      //System.out.println("Description: " + description);
      //FichierInfo unfich; //= new FichierInfo();
      int taille=fichierInfo.getTaille();
      String name=fichierInfo.getNamefile();
      String thename="";
      //unfich.setDescription(description);
 
    //ServletContext servletContext = request.getSession().getServletContext();
     //String myphotos ="/myupload-3";
      
      // Root Directory.
      String uploadRootPath = request.getServletContext().getRealPath("/WEB-INF/Img");
      
      
      System.out.println("uploadRootPath=" + uploadRootPath);
      if(myUploadForm!=null){
          System.out.println("l'objet fichierInfo est : "+fichierInfo);
          System.out.println("MyUploadForm nom --->" + myUploadForm.getName());
      }
 
      File uploadRootDir = new File(uploadRootPath);
      //
      // Create directory if it not exists.
      if (!uploadRootDir.exists()) {
          uploadRootDir.mkdirs();
      }
      CommonsMultipartFile[] fileDatas = myUploadForm.getFileDatas();
      System.out.println("taille du tableau fileDatas ---->"+ fileDatas);
      //
      List<File> uploadedFiles = new ArrayList<File>();
      for (CommonsMultipartFile fileData : fileDatas) {
 
          // Client File Name
          name = fileData.getOriginalFilename();
          taille= (int)fileData.getSize();
          System.out.println("Client File Name = " + name);
 
          if (name != null && name.length() > 0) {
              try {
                  // Create the file on server
                  File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
 
      
                  // Stream to write data to file in server.
                  BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                  stream.write(fileData.getBytes());
                  stream.close();
                  //
                  uploadedFiles.add(serverFile);
                  System.out.println("Write file: " + serverFile);
                  
                  //stream.close(); element  ajoute mais ça ne marche 
              } catch (Exception e) {
                  System.out.println("Error Write file: " + name);
              }
          }
      }
      //Insertion des infos dans la DB
      //unfich= new FichierInfo();
      for(File afile: uploadedFiles){
          
          if(description==null | description.equals("")){
              description = afile.getName();
          }
          fichierInfo.setNamefile(afile.getName());
          fichierInfo.setRemarque(afile.getName());
          fichierInfo.setTaille((int)afile.length());
          fichierInfo.setDescription(description);
          this.fichierDAO.saveFichier(fichierInfo);
      }
      
      /*unfich.setNamefile(name);
      unfich.setRemarque(name);
      unfich.setTaille(taille);*/
      
      model.addAttribute("description", description);
      model.addAttribute("uploadedFiles", uploadedFiles);
      //this.fichierDAO.saveFichier(unfich);
      return "uploadResult";
  }





    @Override
    public void setServletContext(ServletContext servletContext) {
        
       this.context = servletContext;
       context.setAttribute("monCheminUpload", MyUtilsDivers.getCHEMINUPLOAD() );
    }
    
   @RequestMapping("/fichiersList")
   public String fichiersList(Model model){
       List<FichierInfo> list = fichierDAO.listFichierInfos();
       model.addAttribute("fichierInfos", list);
       return "fichiersList";     
   }
@RequestMapping("/deleteFichier")
 public String deleteFichier(Model model, @RequestParam("numfile") String numfile){
     if(numfile!=null){
         this.fichierDAO.deleteFichier(Integer.parseInt(numfile));
     }
     return "redirect:/fichierList";
 }
 
    
    

    
    
    
    /*
    
 
private String doUpload(HttpServletRequest request, Model model, //
     MyUploadForm  myUploadForm ,FichierInfo fichierInfo, Fichier unfichier) {
 
    //Toute cette methode a effacer au besoin
      String description = myUploadForm.getDescription();
      System.out.println("Description: " + description);
      FichierInfo unfich; //= new FichierInfo();
      int taille=0;
      String name="";
      String thename="";
      //unfich.setDescription(description);
 
     ServletContext servletContext = request.getSession().getServletContext();
     String myphotos ="/myupload-3";
      
      // Root Directory.
      String uploadRootPath = request.getServletContext().getRealPath("/WEB-INF/Img");
      
      
      System.out.println("uploadRootPath=" + uploadRootPath);
 
      File uploadRootDir = new File(uploadRootPath);
      //
      // Create directory if it not exists.
      if (!uploadRootDir.exists()) {
          uploadRootDir.mkdirs();
      }
      CommonsMultipartFile[] fileDatas = myUploadForm.getFileDatas();
      //
      List<File> uploadedFiles = new ArrayList<File>();
      for (CommonsMultipartFile fileData : fileDatas) {
 
          // Client File Name
          name = fileData.getOriginalFilename();
          taille= (int)fileData.getSize();
          System.out.println("Client File Name = " + name);
 
          if (name != null && name.length() > 0) {
              try {
                  // Create the file on server
                  File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
 
      
                  // Stream to write data to file in server.
                  BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                  stream.write(fileData.getBytes());
                  stream.close();
                  //
                  uploadedFiles.add(serverFile);
                  System.out.println("Write file: " + serverFile);
                  
                  //stream.close(); element  ajoute mais ça ne marche 
              } catch (Exception e) {
                  System.out.println("Error Write file: " + name);
              }
          }
      }
      //Insertion des infos dans la DB
      unfich= new FichierInfo();
      for(File afile: uploadedFiles){
          
          if(description==null | description.equals("")){
              description = afile.getName();
          }
          unfich.setNamefile(afile.getName());
          unfich.setRemarque(afile.getName());
          unfich.setTaille((int)afile.length());
          unfich.setDescription(description);
          this.fichierDAO.saveFichier(unfich);
      }
      
      unfich.setNamefile(name);
      unfich.setRemarque(name);
      unfich.setTaille(taille);
      
      model.addAttribute("description", description);
      model.addAttribute("uploadedFiles", uploadedFiles);
      //this.fichierDAO.saveFichier(unfich);
      return "uploadResult";
  }   
    
    
    
    
    
    
    
    */

}
