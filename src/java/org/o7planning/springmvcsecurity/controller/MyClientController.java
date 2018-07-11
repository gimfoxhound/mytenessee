
package org.o7planning.springmvcsecurity.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
 
//import org.o7planning.springmvcsecurity.form.MyUploadForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;
import org.o7planning.springmvcsecurity.dao.ClientDAO;
import org.o7planning.springmvcsecurity.dao.impl.ClientDAOImpl;
import org.o7planning.springmvcsecurity.model.ClientInfo;

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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@Transactional
@EnableWebMvc
public class MyClientController {
    
@Autowired
private ClientDAO clientDAO;


@RequestMapping("/clientList")
public String clientList(Model model){
    List<ClientInfo> list = clientDAO.listClientInfo();
    model.addAttribute("clientInfos", list);
    return "clientList";
}
private String formClient(Model model, ClientInfo clientInfo){
    model.addAttribute("clientForm", clientInfo);
    if(clientInfo==null){
        model.addAttribute("formTitle", "Create Client");
    }else{
        model.addAttribute("formTitle", "Edit Client");
    }
    return "formClient";
}    

@RequestMapping("/createClient")
public String createClient(Model model){
    ClientInfo clientInfo = new ClientInfo();
    return this.formClient(model, clientInfo);
}

@RequestMapping("/editClient")
public String editClient(Model model, @RequestParam("nickname") String nickname){
    ClientInfo clientInfo = null;
    if(nickname!=null){
        clientInfo = this.clientDAO.findClientInfo(nickname);
    }
    if(clientInfo==null){
        return "redirect:/clientList";
    }
    return this.formClient(model, clientInfo);
}

@RequestMapping("/deleteClient")
public String deleteClient(Model model, @RequestParam("nickname") String nickname){
    if(nickname!=null){
        this.clientDAO.deleteClient(nickname);
    }
    return "redirect:/clientList";
}
@InitBinder
protected void initBinder(WebDataBinder dataBinder){
    Object target = dataBinder.getTarget();
    if(target==null){
        return;
    }
    System.out.println("Target---> "+target);
    if(target.getClass()==ClientInfo.class){
        System.out.println("Validator à implementer");
    }
}

@RequestMapping(value = "/saveClient", method = RequestMethod.POST)
public String saveClient(Model model,// 
 @ModelAttribute("clientForm") @Validated ClientInfo clientInfo,//
 BindingResult result,//
 final RedirectAttributes redirectAttributes){
    if(result.hasErrors()){
        return this.formClient(model, clientInfo);
    }
    this.clientDAO.saveClient(clientInfo);
    redirectAttributes.addFlashAttribute("message", "Save Client successfull!");
    return "redirect:/clientList";
}

private String doUpload(HttpServletRequest request, Model model,
        ClientInfo clientInfo){
    
    //A revoir ici
    return ""; 
    
}


}
