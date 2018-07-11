
package org.o7planning.springmvcsecurity.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.o7planning.springmvcsecurity.dao.ApplicantDAO;
import org.o7planning.springmvcsecurity.dao.FichierDAO;
import org.o7planning.springmvcsecurity.dao.impl.UserInfoDAOImpl;
import org.o7planning.springmvcsecurity.model.ApplicantInfo;
import org.o7planning.springmvcsecurity.model.FichierInfo;
import org.o7planning.springmvcsecurity.model.UserInfo;
import org.o7planning.springmvcsecurity.validator.ApplicantValidator;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Transactional
public class MainController {
    
    @Autowired   
 private FichierDAO fichierDAO;   
    
    //applicantDAO
    @Autowired
    private ApplicantDAO applicantDAO;
    
    @Autowired
    private ApplicantValidator applicantValidator;   
    
    @Autowired
    private UserInfoDAOImpl userDAO;
    
    
@RequestMapping(value = {"/","welcome"},method = RequestMethod.GET)    
public String welcomePage(Model model){
 model.addAttribute("message", "This my Babar Web page");
 model.addAttribute("MyTitle", "Spring Logging using Hibernatus");
 //return "index";
 return "welcomePage";    
}
@RequestMapping(value = "/admin", method = RequestMethod.GET)    
public String adminPage(Model model){
    model.addAttribute("MyTitle", "Admin Page");
    model.addAttribute("message", "This is a protected page");
    return "adminPage";
}
@RequestMapping(value = "/login", method = RequestMethod.GET)
public String loginPage(Model model){
    model.addAttribute("MyTitle", "Login Page");
    return "loginPage";
}
@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
public String logoutSuccessful(Model model){
    model.addAttribute("MyTitle", "Logout");
    model.addAttribute("message", "Logout Successfull!");
    return "logoutSuccessfulPage";
}

@RequestMapping(value = "/userInfo",method = RequestMethod.GET)
public String userInfo(Model model, Principal principal){
    model.addAttribute("MyTitle", "User Info Page");
    model.addAttribute("message", "It s a protected page");
    String userName = principal.getName();
    System.out.println("User Name --->" +userName);
    return "userInfoPage";
}
@RequestMapping(value = "/403", method = RequestMethod.GET)
public String accessDenied(Model model, Principal principal){
    model.addAttribute("MyTitle", "Access Denied!");
    if(principal!=null){
        model.addAttribute("message", "Hi "+ principal.getName()+ " '<br>' You do not have the permission to access this page!");        
    }else{
        model.addAttribute("message", "Verboten Seite! "+ principal.getName()+ " '<br>' You do not have the permission to access this page!");
    }
    return "403Page";   
}


@RequestMapping("/fichierList")
 public String fichierList(Model model){
     List<FichierInfo> listfichier = fichierDAO.listFichierInfos();
     model.addAttribute("fichierInfos", listfichier);
     model.addAttribute("MyTitle", "Liste des fichiers");
     return "fichiersList" ;
 }


@RequestMapping("/applicantList")
public String applicantList(Model model){
    
    List<ApplicantInfo> list= applicantDAO.listApplicantInfos();
    model.addAttribute("MyTitle", "Applicant List");
    model.addAttribute("applicantInfos", list);
    
    return "applicantList";
}

private Map<String, String> dataForPositions(){
    Map<String, String> positionMap = new LinkedHashMap<String, String>();
    positionMap.put("Developer", "Developer");
    positionMap.put("Leader", "Leader");
    positionMap.put("Tester", "Tester");
    return positionMap;
}

private List<String> dataForSkills(){
    
    List<String> list = new ArrayList<String>();
    list.add("Java");
    list.add("C/C++");
    list.add("C#");
    return list;
}

@RequestMapping("/mescandidats")
public String myListCandidats(Model model){
  return applicantList(model);
}




private String formApplicant(Model model, ApplicantInfo applicantInfo){
    model.addAttribute("applicantForm", applicantInfo);
    Map<String, String> positionMap = this.dataForPositions();
    model.addAttribute("positionMap", positionMap);
    model.addAttribute("MyTitle", "Design new Applicant");
    
    List<String> list = dataForSkills();
    model.addAttribute("skills", list);
    if(applicantInfo.getId()==null){
        model.addAttribute("formTitle", "Create Applicant");
    }else{
        model.addAttribute("formTitle", "Edit Applicant");
    }
    return "formApplicant";
}

private String formUser(Model model, UserInfo userInfo){
    model.addAttribute("usrForm", userInfo);
    model.addAttribute("MyTitle", "Complete User");
    if(userInfo.getUsername()==null){
        model.addAttribute("formTitle", "Create User");
    }else{
        model.addAttribute("formTitle", "Edit User");
    }
    
    return "formUsr";
}


@RequestMapping("/registerUser")
public String registerUsr (Model model){
    UserInfo userInfo= new UserInfo();
    return this.formUser(model, userInfo);
}

@RequestMapping("/createApplicant")
public String createApplicant(Model model){
    ApplicantInfo applicantInfo = new ApplicantInfo();
    return this.formApplicant(model, applicantInfo);
}

//------------------------------------------------------------------------------//
//Reprendre à partir de là 
//------------------------------------------------------------------------------//


@RequestMapping("editApplicant")
public String editApplicant(Model model, @RequestParam("id") String id){
    
    ApplicantInfo applicantInfo= null;
    if(id!=null ){
        applicantInfo = this.applicantDAO.findApplicantInfo(id);
    }
    if(applicantInfo==null){
        return "redirect:/applicantList";
    }
    return this.formApplicant(model, applicantInfo);
}
@RequestMapping("/editUser")
public String editUsr(Model model, @RequestParam("username") String usrname){
    UserInfo userInfo= null;
    if(usrname!=null){
        userInfo = this.userDAO.findUserInfo(usrname);
    }
    if(userInfo==null){
        return "redirect:/userList";
    }
    return this.formUser(model, userInfo);    
}


@RequestMapping("/deleteApplicant")
public String deleteApplicant(Model model, @RequestParam("id") String id){
    if(id!=null){
        this.applicantDAO.deleteApplicant(id);
    }
    return "redirect:/applicantList";
}

@RequestMapping("/deleteUser")
public String deleteUsr(Model model, @RequestParam("username") String usrname){
    if(usrname!=null){
        this.userDAO.deleteUser(usrname);
    }
    return "redirect:/userList";
}



//Set a form validator
@InitBinder
protected void initBinder(WebDataBinder dataBinder){
    //Form Target
    Object target = dataBinder.getTarget();
    if(target ==null){
        return;
    }
    System.out.println("Target --->"+ target);
    if(target.getClass()==ApplicantInfo.class){
        dataBinder.setValidator(applicantValidator);
    }
}
//Save or update Applicant
//1 @ModelAttribute bind form value
//2 @Validated form validator
//3 RedirectAttribute for flash value




@RequestMapping(value = "/saveApplicant", method = RequestMethod.POST)
public String saveApplicant(Model model, @ModelAttribute("applicantForm")@Validated ApplicantInfo applicantInfo,
        BindingResult result, final RedirectAttributes redirectAttribute){
    
    if(result.hasErrors()){
        return this.formApplicant(model, applicantInfo);
    }
    this.applicantDAO.saveApplicant(applicantInfo);
    //Important!!! Need @EnableWebMVC
    //Add message to flash scope
    redirectAttribute.addFlashAttribute("message", "Save Applicant Successfull!");
    return "redirect:/applicantList";
    
}

@RequestMapping(value = "/saveUsr", method = RequestMethod.POST)
public String saveUser(Model model, @ModelAttribute("usrForm")@Validated UserInfo userInfo, 
        BindingResult result, final RedirectAttributes redirectAttribute ){
    
    if(result.hasErrors()){
        return this.formUser(model, userInfo);
    }
    this.userDAO.saveUser(userInfo);
    
    redirectAttribute.addFlashAttribute("message", "Save User Successfully!");
    return "redirect:/userList";
    
}

@RequestMapping("/userList")
public String userLists(Model model){
  List<UserInfo> list = this.userDAO.listUserInfo();
  model.addAttribute("MyTitle", "User Lists");
  model.addAttribute("userInfos", list);
  return "userList";
    
    
}


//@RequestMapping(value = "/saveUser", method = RequestMethod.POST)



/*
@RequestMapping("/editApplicant")
   public String editApplicant(Model model, @RequestParam("id") String id) {
       ApplicantInfo applicantInfo = null;
       if (id != null) {
           applicantInfo = this.applicantDAO.findApplicantInfo(id);
       }
       if (applicantInfo == null) {
           return "redirect:/applicantList";
       }
 
       return this.formApplicant(model, applicantInfo);
   }
 
   @RequestMapping("/deleteApplicant")
   public String deleteApplicant(Model model, @RequestParam("id") String id) {
       if (id != null) {
           this.applicantDAO.deleteApplicant(id);
       }
       return "redirect:/applicantList";
   }
 
   // Set a form validator
   @InitBinder
   protected void initBinder(WebDataBinder dataBinder) {
       // Form target
       Object target = dataBinder.getTarget();
       if (target == null) {
           return;
       }
       System.out.println("Target=" + target);
 
       if (target.getClass() == ApplicantInfo.class) {
           dataBinder.setValidator(applicantValidator);
       }
   }
 
   // Save or update Applicant
   // 1. @ModelAttribute bind form value
   // 2. @Validated form validator
   // 3. RedirectAttributes for flash value
   @RequestMapping(value = "/saveApplicant", method = RequestMethod.POST)
   public String saveApplicant(Model model, 
           @ModelAttribute("applicantForm") @Validated ApplicantInfo applicantInfo, //
           BindingResult result, //
           final RedirectAttributes redirectAttributes) {
 
    
       if (result.hasErrors()) {
           return this.formApplicant(model, applicantInfo);
       }
 
       this.applicantDAO.saveApplicant(applicantInfo);
 
       // Important!!: Need @EnableWebMvc
       // Add message to flash scope
       redirectAttributes.addFlashAttribute("message", "Save Applicant Successful");
 
       return "redirect:/applicantList";
 
   }



*/

}
