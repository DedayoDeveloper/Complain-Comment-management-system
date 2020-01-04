/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

//import com.model.FileUpload;
import com.model.Product;
import com.model.User;
import com.model.UserCreate;
import com.model.UserType;
import com.model.incidence;
import com.service.FileService;
import com.service.IncidenceReportImpl;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import com.validator.FileValidator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Supersoft
 */
@Controller
@Scope("session")

public class MainController {
    
    
    final String SAVE_DIRECTORY = "uploads";
    String fullSavePath;
    
    @Autowired
    IncidenceReportImpl incidence;
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    FileService pService;
    
//    @Autowired
//    FileValidator filevalidator;
//    String folderpath = "\IncidenceManagementReportSystem\build\web\";
    
    private static final String UPLOAD_DIRECTORY ="/";  
    
    @RequestMapping(value="/" , method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView indexPage(){
    ModelAndView model = new ModelAndView();
    List<User> getInstitutions = incidence.getAllInstitutions();
    model.addObject("getInstitutions", getInstitutions);
    model.setViewName("index");
    return model;
    }
    
    
//    
//    @RequestMapping(value="/incident",method={RequestMethod.GET,RequestMethod.POST})
//    public ModelAndView IncidentReportPage(HttpServletRequest request,HttpSession session,@RequestParam(defaultValue = "1") int page_num){
//    ModelAndView model = new ModelAndView();
//    
//    
//       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//         String username = auth.getName();
////    Object user = request.getSession().getAttribute("registerusername");
////    String username = user.toString();
//        System.out.println("USERNAME FROM REGISTER IS = " + username);
//     String getInstitutionName = incidence.getIntitutionNameTwo(username);
//            System.out.println("getInstitutionName =============================== " + getInstitutionName);
//     List<incidence> getIncident = incidence.getLoggedIncidenceForFamiliarInstitute(getInstitutionName);
//           
//            int total = getIncident.size();
//            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
//            System.out.println("page:" + page_num);
//            com.util.Pagination pg = new com.util.Pagination(page_num, total);
//            String limit = pg.getLimit();
//            List<incidence> getIncidence = incidence.getLoggedIncidenceForFamiliarInstituteLimit(getInstitutionName,limit);
//            pg.setLink(link);
//            List<User> getInstitutions = incidence.getAllInstitutions();
//            System.out.println("size!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + getInstitutions.size());
//            model.addObject("getInstitutions", getInstitutions);
//            String pages = pg.getControls();
//            System.out.println("pages:" + pages);
//            model.addObject("pagination", pages);
//            System.out.println("incidence:" + getIncident.size());
//            model.addObject("getIncidence", getIncidence);
//    model.setViewName("incidentReport");
//    return model;
//    }
//    
    @ResponseBody
    @RequestMapping(value="/registernewuser",method={RequestMethod.GET,RequestMethod.POST})
    public String AdminPage(HttpServletRequest request,HttpSession session){
    ModelAndView model = new ModelAndView();
        
//    Object User = request.getSession().getAttribute("username");
    
    
     String alertMessage = "User Not Successfully Created";
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");
    String phonenumber = request.getParameter("phonenumber");
    String institutionname = request.getParameter("institutionname");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String usertypename = request.getParameter("usertype");

  
    System.out.println("usertypename " + usertypename);
    
    int enabled = 1;
    
    int firsttimelogin = 1;
    
    String role = "ROLE_USER";
    
    
    
   boolean registeruser = incidence.registerUser(firstname, lastname, phonenumber, institutionname, username, password,enabled,firsttimelogin);
       
        if(registeruser == true){
            
        int mapUser = incidence.userMappingForAdminCreation(username, usertypename);
        System.out.println("USERMAPPING########################################################## " + mapUser);
        System.out.println("WE GOT HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        int userrole = incidence.userRoles(username, role);
        
        System.out.println("GOT USERROLE++++++++++++++++++++ " + userrole);
        alertMessage = "User Successfully Created";
        }
    return alertMessage;
    }
    
    
    
    
    
    @ResponseBody
    @RequestMapping(value="/registeruser",method={RequestMethod.POST,RequestMethod.GET})
    public String RegisterModal(HttpServletRequest request, HttpSession session){
    ModelAndView model = new ModelAndView();
    String alertMessage = "User Not Successfully Added";
    

    
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");
    String phonenumber = request.getParameter("phonenumber");
    String institutionname = request.getParameter("institutionname");
    String username = request.getParameter("username");
   
    String password = request.getParameter("password");
    String toEmailAddress = username;
    String subject = "Verify Your Account";
    
    int enabled = 1;
    
    int firsttimelogin = 0;
    
    String role = "ROLE_USER";
    
    String usertypename = "institutionuser";
    
    String emailbody = String.valueOf(Math.random()).substring(2, 15);
    
    
        String usertyp = incidence.userType(usertypename);
        int usertype = Integer.parseInt(usertyp);
        System.out.println("usertype ---------------------------- " + usertype);
    
        boolean registeruser = incidence.registerUser(firstname, lastname, phonenumber, institutionname, username, password,enabled,firsttimelogin);
        int usermap = incidence.userMapping(username, usertype);
        System.out.println("GOT USERMAP ++++++++++++++++++++++ " + usermap);
    
   
            
    if(registeruser == true){
        request.getSession().setAttribute("username", username);
         System.out.println("USER REGISTERED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
         int userrole = incidence.userRoles(username, role);
         System.out.println("GOT USERROLE++++++++++++++++++++ " + userrole);
         
         boolean emailinsert = incidence.insertEmail(username, subject, emailbody, toEmailAddress);
//        alertMessage = "User Successfully Added";
        
//        model.addObject("verify", "TO COMPLETE YOUR REGISTRATION PLEASE VERIFY TOKEN SENT TO EMAIL ADDRESS");
        
        
        
//         model.setViewName("tokenPage");
         alertMessage = "User successfully created";
        }
    
    return alertMessage;
    }
    
  @RequestMapping(value = "/incidentreport/incidentreport", method = {RequestMethod.POST, RequestMethod.GET})
     public ModelAndView incidentReport(HttpServletRequest request, HttpSession session, @RequestParam(defaultValue = "1") int page_num){
     ModelAndView model = new ModelAndView();
        
//     Object user =  request.getSession().getAttribute("username");
      
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

         String username = auth.getName();
        
         model.addObject("username", username);
//       String username = user.toString();
       request.getSession().setAttribute("username", username);
       
        System.out.println("username" + username);
        
        System.out.println("USERNAME FROM INCIDENTREPORT " + username);
        
        String checklogin = incidence.checkFirstLogin(username);
        System.out.println("checklogin !!!!!!!!!!!!!!!!!++++++++++++++++ " + checklogin);
         String confirm = "0";
         System.out.println("confirm!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + confirm);
         
         
        if(checklogin.equals(confirm)){
            System.out.println("i am here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        model.setViewName("tokenPage");
     
        }else{
            
        String CheckUserId = incidence.userAssign(username);
        System.out.println("CheckUserId:" + CheckUserId);
   
//        String getFinancialInstitutionName = incidence.getFinancialInstitutionName(username);
//        model.addObject("financialinstitutionname", getFinancialInstitutionName);
//        System.out.println("getFinancialInstitutionName:" + getFinancialInstitutionName);
       

        List<incidence> getIncidenceType = incidence.getIncidenceType();
        model.addObject("incidenceTypes", getIncidenceType);
        System.out.println("getIncidenceType size: " + getIncidenceType.size());
        
        String getInstitutionName = incidence.getIntitutionNameTwo(username);
            System.out.println("getInstitutionName =============================== " + getInstitutionName);
               model.addObject("institutionname", getInstitutionName);
        if (CheckUserId.equalsIgnoreCase("1")) {
            List<incidence> getIncident = incidence.getAllIncidenceInDatabase();
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.getAllIncidenceInDatabaseLimit(limit);
            pg.setLink(link);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
//            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
            model.addObject("institutionname", getInstitutionName);
            model.setViewName("incidentreport3");
            
            
                   
        }else if (CheckUserId.equalsIgnoreCase("2")){
             List<incidence> getIncident = incidence.getAllIncidenceInDatabase();
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.getAllIncidenceInDatabaseLimit(limit);
            pg.setLink(link);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
//            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
            model.setViewName("supersoftuser");
            
            
        } else {
            List<incidence> getIncident = incidence.getLoggedIncidenceForFamiliarInstitute(getInstitutionName);
           
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.getLoggedIncidenceForFamiliarInstituteLimit(getInstitutionName,limit);
            pg.setLink(link);
            List<User> getInstitutions = incidence.getAllInstitutions();
            System.out.println("size!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + getInstitutions.size());
            model.addObject("getInstitutions", getInstitutions);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
            model.setViewName("incidentReport");
        }
      
        }
        
        return model;
    }   
    
//    
    @RequestMapping(value="/viewIncidence/viewIncidence/{id}" , method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView ViewPage(HttpServletRequest request,HttpSession session,@PathVariable int id){

    
//         Object user = request.getSession().getAttribute("username");
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return model;
//        }
        
//        boolean isUserAllowed = false;
//
//        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
//
//        for (UserPages userPgs: userPages) {
//            if (userPgs.getUrlmapping() != null) {
//                if (userPgs.getUrlmapping().equals("viewIncidence")) {
//                    isUserAllowed = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
//
//        if (isUserAllowed == false) {
//            model.setViewName("redirect:/welcome");
//            return model;
//        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println("id" + id);
        model.addObject("userId", username);

        String getFinancialInstitution = incidence.getInstCode(username);

        model.addObject("financialinstitutioncode", getFinancialInstitution);
        List<incidence> getIncidencebyId = incidence.getIncidencebyID(id);
        System.out.println("getIncidencebyId:" + getIncidencebyId.size());
        model.addObject("getIncidencebyId", getIncidencebyId);
        List<incidence> responses = incidence.getResponsebyID(id);
        System.out.println("responses:" + responses.size());
        model.addObject("responses", responses);
        model.setViewName("viewIncidence");
    return model;
    }
    
    
    @ResponseBody
    @RequestMapping(value={"/incidentreport/closeincidence"},method={RequestMethod.GET,RequestMethod.POST})
     public String closeIncidenceTwo(HttpSession session, HttpServletRequest request) {
        String message = "Failed";
        
        Object user = request.getSession().getAttribute("username");
           Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
         System.out.println("USERNAME! " + username);
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return message;
//        }

//        boolean isUserAllowed = false;
//
//        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
//
//        for (UserPages userPgs: userPages) {
//            if (userPgs.getUrlmapping() != null) {
//                if (userPgs.getUrlmapping().equals("closeincidence")) {
//                    isUserAllowed = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
//
//        if (isUserAllowed == false)
//            return message;

//        String username = (String) request.getSession().getAttribute("username");
        String id = request.getParameter("id");
        
        int result = incidence.closeIncidence(id, username);
        System.out.println("incidenceID: " + id);
        System.out.println("username: " + username);
        if (result == 1) {
            message = "Successful";
             model.setViewName("incidentReport");
            return message;
           
        }
        return message;
    }
    
    
    
    
    
    
    
    @ResponseBody
    @RequestMapping(value={"/incidentreport/logincidence"},method={RequestMethod.GET,RequestMethod.POST})
    public String logIncidenceTwo(incidence Incidence, BindingResult result, HttpServletRequest request, HttpSession session) {
        String messages = request.getParameter("messageBody");
    
        
        
        
        request.getSession().setAttribute("messages", messages);
        String alert = (String) request.getSession().getAttribute("messages");
        System.out.println("alert: " + alert);

        long status = 0;
        String message = "Failed";
        
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return message;
//        }

//        boolean isUserAllowed = false;
//
//        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
//
//        for (UserPages userPgs: userPages) {
//            if (userPgs.getUrlmapping() != null) {
//                if (userPgs.getUrlmapping().equals("logincidence")) {
//                    isUserAllowed = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
//
//        if (isUserAllowed == false)
//            return message;

        if (result.hasErrors()) {
            System.out.println("res:" + result.toString());
            return "error in supplied info";
        }
        try {
           String trackingnumber = String.valueOf(Math.random()).substring(2, 14);
             request.getSession().setAttribute("trackingnumber", trackingnumber);
           String getInstitutionName = incidence.getIntitutionNameTwo(username);
            System.out.println("getInstitutionName =============================== " + getInstitutionName);
             System.out.println("TRACKING NUMBER ==== " + trackingnumber);
            status = incidence.logIncidence(Incidence,trackingnumber);
            System.out.println("status!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + status);
            
             int updateusername = incidence.InsertInstitutionName(getInstitutionName, username);
            
        } catch (Exception ee) {
            System.out.println(ee.getMessage());
        }
        if (status >= 1) {
            message = "Successful";
            //insert foremail
             model.setViewName("incidentReport");
        } else {
            message = "Failed";
        }

        return message;
    }

       
    @RequestMapping(value="/incidenreportpagetwo" , method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView SearchPage(HttpServletRequest request,HttpSession session,@PathVariable int id){
    ModelAndView model = new ModelAndView();
    model.setViewName("incidentReport2");
    return model;
  }
    
    
   
    
    
//    
//    @RequestMapping(value = "/loginForm", method = {RequestMethod.POST, RequestMethod.GET})
//    public String incidentReport(HttpServletRequest request, HttpSession session) {
//      
//    ModelAndView model = new ModelAndView();
//    String username = request.getParameter("username");
//    String password = request.getParameter("password");
//    
//    request.getSession().setAttribute("username", username);
//    
//    int user = incidence.UserSign(username, password);
//    if (user == 1){
//        System.out.println("user=================== " + user);
//   
//        System.out.println("SIGN IN SUCCESSFUL");
//        model.setViewName("incidentReport");
//        return "redirect:/incidentreport";
//    }
//    return "error";
//    }
//        
     @RequestMapping(value = "/incidentreport", method = {RequestMethod.POST, RequestMethod.GET})
     public ModelAndView incidentReportSecondMethod(HttpServletRequest request, HttpSession session, @RequestParam(defaultValue = "1") int page_num){
     ModelAndView model = new ModelAndView();
        
      Object user =  request.getSession().getAttribute("username");
    
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

         String username = auth.getName();
         
         model.addObject("username", username);
         
       request.getSession().setAttribute("username", user);
//       String username = user.toString();
        System.out.println("username" + username);
         System.out.println("USERNAME FROM INCIDENTREPORT " + username);
        
//        String getUsernameForPage = incidence.User(username);
//        model.addObject("username", username);
        
        
        String checklogin = incidence.checkFirstLogin(username);
        System.out.println("checklogin !!!!!!!!!!!!!!!!!++++++++++++++++ " + checklogin);
         String confirm = "0";
         System.out.println("confirm!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + confirm);
         
         model.addObject("username", username);
         
         
        if(checklogin.equals(confirm)){
            System.out.println("i am here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        model.setViewName("tokenPage");
     
        }else{
            
        String CheckUserId = incidence.userAssign(username);
        System.out.println("CheckUserId:" + CheckUserId);
   
//        String getFinancialInstitutionName = incidence.getFinancialInstitutionName(username);
//        model.addObject("financialinstitutionname", getFinancialInstitutionName);
//        System.out.println("getFinancialInstitutionName:" + getFinancialInstitutionName);
       

        List<incidence> getIncidenceType = incidence.getIncidenceType();
        model.addObject("incidenceTypes", getIncidenceType);
        System.out.println("getIncidenceType size: " + getIncidenceType.size());
        
        String getInstitutionName = incidence.getIntitutionNameTwo(username);
            System.out.println("getInstitutionName =============================== " + getInstitutionName);

        if (CheckUserId.equalsIgnoreCase("1")) {
            List<incidence> getIncident = incidence.getAllIncidenceInDatabase();
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.getAllIncidenceInDatabaseLimit(limit);
            pg.setLink(link);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
//            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
//            model.addObject("formupload", new FileUpload());
            model.addObject("institutionname", getInstitutionName);
            model.setViewName("incidentreport3");
            
        }else if (CheckUserId.equalsIgnoreCase("2")){
             List<incidence> getIncident = incidence.getAllIncidenceInDatabase();
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.getAllIncidenceInDatabaseLimit(limit);
            pg.setLink(link);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
//            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
//              model.addObject("formupload", new FileUpload());
            model.setViewName("supersoftuser");
            
        } else {
            List<incidence> getIncident = incidence.getLoggedIncidenceForFamiliarInstitute(getInstitutionName);
           
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.getLoggedIncidenceForFamiliarInstituteLimit(getInstitutionName,limit);
            pg.setLink(link);
            List<User> getInstitutions = incidence.getAllInstitutions();
            System.out.println("size!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + getInstitutions.size());
            model.addObject("getInstitutions", getInstitutions);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
            model.addObject("institutionname", getInstitutionName);
//              model.addObject("formupload", new FileUpload());
            model.setViewName("incidentReport");
        }
      
        }
        
        return model;
    }   
    
    
    @ResponseBody
    @RequestMapping(value="/logincidence",method=RequestMethod.POST)
    public String logIncidence(incidence Incidence, BindingResult result, HttpServletRequest request,HttpSession session) {
        String messages = request.getParameter("messageBody");
    
        
        
        
        request.getSession().setAttribute("messages", messages);
        String alert = (String) request.getSession().getAttribute("messages");
        System.out.println("alert: " + alert);

        long status = 0;
        String message = "Failed";
        
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return message;
//        }

//        boolean isUserAllowed = false;
//
//        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
//
//        for (UserPages userPgs: userPages) {
//            if (userPgs.getUrlmapping() != null) {
//                if (userPgs.getUrlmapping().equals("logincidence")) {
//                    isUserAllowed = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
//
//        if (isUserAllowed == false)
//            return message;

//          String username = user.toString();
        if (result.hasErrors()) {
            System.out.println("res:" + result.toString());
            return "error in supplied info";
        }
        try {
           String trackingnumber = String.valueOf(Math.random()).substring(2, 14);
           request.getSession().setAttribute("trackingnumber", trackingnumber);
            System.out.println("TRACKING NUMBER ==== " + trackingnumber);
           String getInstitutionName = incidence.getIntitutionNameTwo(username);
            System.out.println("getInstitutionName =============================== " + getInstitutionName);
            
            status = incidence.logIncidence(Incidence,trackingnumber);
            
            System.out.println("status!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + status);
            
             int updateusername = incidence.InsertInstitutionName(getInstitutionName, username);
            
        } catch (Exception ee) {
            System.out.println(ee.getMessage());
        }
        if (status >= 1) {
            message = "Successful";
            //insert foremail
             model.setViewName("incidentReport");
        } else {
            message = "Failed";
        }

        return message;
    }

    
    
    
    @ResponseBody
    @RequestMapping(value = {"/closeincidence"}, method = {RequestMethod.POST,RequestMethod.GET})
    public String closeIncidence(HttpSession session, HttpServletRequest request) {
        String message = "Failed";
        System.out.println("debugging");
        Object user = request.getSession().getAttribute("username");
           Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println("USERNAME! " + username);
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return message;
//        }

//        boolean isUserAllowed = false;
//
//        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
//
//        for (UserPages userPgs: userPages) {
//            if (userPgs.getUrlmapping() != null) {
//                if (userPgs.getUrlmapping().equals("closeincidence")) {
//                    isUserAllowed = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
//
//        if (isUserAllowed == false)
//            return message;

//        String username = (String) request.getSession().getAttribute("username");
//        String id = request.getParameter("id");
       String id = request.getParameter("id");
       System.out.println("incidenceID: " + id);
      
        int result = incidence.closeIncidence(id, username);
        
        System.out.println("username: " + username);
       
        if (result == 1) {
              System.out.println("debugging");
                System.out.println("result====================================================== " + result);
            message = "Successful";
            model.setViewName("incidentReport");
            return message;
           
        }
        return message;
    }
    
    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendincidenceresponse",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String sendIncidenceResponse(HttpServletRequest request) {
        String alertMessage = "Failed";
        
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println("USERNAME FOR RESPONSE " + username);
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("login");
//            return alertMessage;
//        }

//        boolean isUserAllowed = false;
//
//        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
//
//        for (UserPages userPgs: userPages) {
//            if (userPgs.getUrlmapping() != null) {
//                if (userPgs.getUrlmapping().equals("sendincidenceresponse")) {
//                    isUserAllowed = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
//
//        if (isUserAllowed == false)
//            return alertMessage;

        try {
//            String username = (String) request.getSession().getAttribute("username");
                           String id = request.getParameter("id");
                           System.out.println("ID FROM SEND RESPONSE = " + id);
                           String message  =  request.getParameter("message");
                           System.out.println("MESSAGE FROM SEND RESPONSE = " + message);

            boolean isChanged = incidence.incidenceReply(id,message, username, request.getParameter("financialinstitutioncode"));

            if (isChanged == true) {
                alertMessage = "Successful";
                //insert into email table
            }

        } catch (Exception ee) {
            ee.printStackTrace();
        }

        return alertMessage;
    }
    
   
    @RequestMapping(value = {"/viewIncidence/{id}"}, method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView viewIncidence(HttpSession session, HttpServletRequest request, @PathVariable int id) {
//        ModelAndView model = new ModelAndView();
//        Object user = request.getSession().getAttribute("username");
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return model;
//        }
        
//        boolean isUserAllowed = false;
//
//        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
//
//        for (UserPages userPgs: userPages) {
//            if (userPgs.getUrlmapping() != null) {
//                if (userPgs.getUrlmapping().equals("viewIncidence")) {
//                    isUserAllowed = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
//
//        if (isUserAllowed == false) {
//            model.setViewName("redirect:/welcome");
//            return model;
//        }

//        String username = (String) request.getSession().getAttribute("username");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println("id" + id);
        model.addObject("userId", username);

        String getFinancialInstitution = incidence.getInstCode(username);

        model.addObject("financialinstitutioncode", getFinancialInstitution);
        List<incidence> getIncidencebyId = incidence.getIncidencebyID(id);
        System.out.println("getIncidencebyId:" + getIncidencebyId.size());
        model.addObject("getIncidencebyId", getIncidencebyId);
        List<incidence> responses = incidence.getResponsebyID(id);
        System.out.println("responses:" + responses.size());
        model.addObject("responses", responses);
        model.setViewName("viewIncidence");
        return model;
    }
    
    
   
    
    
    
    
    @RequestMapping(value = "/searchUser", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView searchUsersreport(HttpServletRequest request, HttpSession session, @RequestParam(defaultValue = "1") int page_num, 
        @RequestParam String table_name, @RequestParam String search_by, @RequestParam String search_string) {        
        Object user = request.getSession().getAttribute("username");
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        System.out.println("OBJECT USER " + username);
        ModelAndView model = new ModelAndView();
         List<UserCreate> getUserAdmin = incidence.searchUserDetails(table_name, search_by, search_string);
            int total = getUserAdmin.size();
             String link = "/IncidenceManagementReportSystem/userpagesearch/" + "?page_num=";
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<UserCreate> getUserAdmins = incidence.searchUserDetailsLimit(table_name, search_by, search_string, limit);
            String type = request.getParameter("search_string");
            model.addObject("type", type);
            System.out.println("controller type............:" + type);
            pg.setLink(link);
            String pages = pg.getControls();
            model.addObject("pagination", pages);
            System.out.println("incidence:" + getUserAdmins.size());
            model.addObject("getUserAdmins",getUserAdmins);
        model.setViewName("UserViewPage");
     return model;   
    }
    
    
    
    
    
    
    
    @RequestMapping(value="/userpagesearch",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView searchPage(HttpServletRequest request, HttpSession session, @RequestParam(defaultValue = "1") int page_num, 
        @RequestParam String table_name, @RequestParam String search_by, @RequestParam String search_string) {        
        Object user = request.getSession().getAttribute("username");
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        System.out.println("OBJECT USER " + username);
        ModelAndView model = new ModelAndView();
         List<UserCreate> getUserAdmin = incidence.searchUserDetails(table_name, search_by, search_string);
            int total = getUserAdmin.size();
             String link = "/IncidenceManagementReportSystem/userpagesearch/" + "?page_num=";
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<UserCreate> getUserAdmins = incidence.searchUserDetailsLimit(table_name, search_by, search_string, limit);
            String type = request.getParameter("search_string");
            model.addObject("type", type);
            System.out.println("controller type............:" + type);
            pg.setLink(link);
            String pages = pg.getControls();
            model.addObject("pagination", pages);
            System.out.println("incidence:" + getUserAdmins.size());
            model.addObject("getUserAdmins",getUserAdmins);
    model.setViewName("UserViewPage");
    return model;
   }
    
    
    
    
    
    
    
    
    
    
    
    
    

    @RequestMapping(value = "/searchincidencereport", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView searchincidencereport(HttpServletRequest request, HttpSession session, @RequestParam(defaultValue = "1") int page_num, 
            @RequestParam String table_name, @RequestParam String table_name2, @RequestParam String search_by, @RequestParam String search_string) {        
        Object user = request.getSession().getAttribute("username");
        System.out.println("OBJECT USER " + user);
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return model;
//        }
        
//        boolean isUserAllowed = false;
//
//        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
//
//        for (UserPages userPgs: userPages) {
//            if (userPgs.getUrlmapping() != null) {
//                if (userPgs.getUrlmapping().equals("searchincidencereport")) {
//                    isUserAllowed = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
//
//        if (isUserAllowed == false) {
//            model.setViewName("redirect:/welcome");
//            return model;
//        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        model.addObject("username", username);
//        String username = (String) user;
        System.out.println("username" + username);
//        String getFinancialInstitution = (String) request.getSession().getAttribute("walletInst");
//        model.addObject("financialinstitutioncode", getFinancialInstitution);
           String getUserRole = incidence.userAssign(username);
//        List<incidence> getIncidenceType = incidence.getIncidenceType();
//        model.addObject("incidenceTypes", getIncidenceType);
//        System.out.println("getIncidenceType size: " + getIncidenceType.size());

        if (getUserRole.equalsIgnoreCase("1")) {
            List<incidence> getIncident = incidence.searchIncidence(table_name, search_by, search_string);
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentReport/" + "?page_num=";
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.searchIncidenceLimit(table_name, search_by, search_string, limit);
            String type = request.getParameter("search_string");
            model.addObject("type", type);
            System.out.println("controller type............:" + type);
            pg.setLink(link);
            String pages = pg.getControls();
            model.addObject("pagination", pages);
            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
        } else if (getUserRole.equalsIgnoreCase("3")){
            String getInstitution = incidence.getIntitutionNameTwo(username);
            List<incidence> getIncident = incidence.searchIncidenceByUser(table_name, search_by, search_string, getInstitution);
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentReport/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.searchIncidenceLimitByUser(table_name, search_by, search_string, getInstitution, limit);
            pg.setLink(link);
            String type = request.getParameter("search_string");
            model.addObject("type", type);
            System.out.println("controller type............:" + type);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
        } else if (getUserRole.equalsIgnoreCase("2")){
            List<incidence> getIncident = incidence.searchIncidence(table_name, search_by, search_string);
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentReport/" + "?page_num=";
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.searchIncidenceLimit(table_name, search_by, search_string, limit);
            String type = request.getParameter("search_string");
            model.addObject("type", type);
            System.out.println("controller type............:" + type);
            pg.setLink(link);
            String pages = pg.getControls();
            model.addObject("pagination", pages);
            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
        }
        model.setViewName("incidentReport2");
        return model;
    }
    
    
      @RequestMapping(value = "/errorPage", method = RequestMethod.GET)
    public ModelAndView errorHandler() {
        ModelAndView model = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("errorPage");

        return model;
    }
    
    
    
//      @ResponseBody
    @RequestMapping(value = "/incidentreport/searchincidencereport", method = {RequestMethod.POST, RequestMethod.GET})
 public ModelAndView searchincidencereportTwo(HttpServletRequest request, HttpSession session, @RequestParam(defaultValue = "1") int page_num, 
            @RequestParam String table_name, @RequestParam String table_name2, @RequestParam String search_by, @RequestParam String search_string) {        
      Object user = request.getSession().getAttribute("username");
      
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
      
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return model;
//        }
        
//        boolean isUserAllowed = false;
//
//        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
//
//        for (UserPages userPgs: userPages) {
//            if (userPgs.getUrlmapping() != null) {
//                if (userPgs.getUrlmapping().equals("searchincidencereport")) {
//                    isUserAllowed = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
//
//        if (isUserAllowed == false) {
//            model.setViewName("redirect:/welcome");
//            return model;
//        }

//        String username = user.toString();
        System.out.println("username" + username);
//        String getFinancialInstitution = (String) request.getSession().getAttribute("walletInst");
//        model.addObject("financialinstitutioncode", getFinancialInstitution);
           String getUserRole = incidence.userAssign(username);
//        List<incidence> getIncidenceType = incidence.getIncidenceType();
//        model.addObject("incidenceTypes", getIncidenceType);
//        System.out.println("getIncidenceType size: " + getIncidenceType.size());

        if (getUserRole.equalsIgnoreCase("1")) {
            List<incidence> getIncident = incidence.searchIncidence(table_name, search_by, search_string);
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentReport/" + "?page_num=";
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.searchIncidenceLimit(table_name, search_by, search_string, limit);
            String type = request.getParameter("search_string");
            model.addObject("type", type);
            System.out.println("controller type............:" + type);
            pg.setLink(link);
            String pages = pg.getControls();
            model.addObject("pagination", pages);
            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
        } else if (getUserRole.equalsIgnoreCase("3")){
            String getInstitution = incidence.getIntitutionNameTwo(username);
            List<incidence> getIncident = incidence.searchIncidenceByUser(table_name, search_by, search_string, getInstitution);
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentReport/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.searchIncidenceLimitByUser(table_name, search_by, search_string, getInstitution, limit);
            pg.setLink(link);
            String type = request.getParameter("search_string");
            model.addObject("type", type);
            System.out.println("controller type............:" + type);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
        } else if (getUserRole.equalsIgnoreCase("2")){
            List<incidence> getIncident = incidence.searchIncidence(table_name, search_by, search_string);
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentReport/" + "?page_num=";
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.searchIncidenceLimit(table_name, search_by, search_string, limit);
            String type = request.getParameter("search_string");
            model.addObject("type", type);
            System.out.println("controller type............:" + type);
            pg.setLink(link);
            String pages = pg.getControls();
            model.addObject("pagination", pages);
            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
        }
        model.setViewName("incidentReport2");
        return model;
    }
//    
//    
    
    
    @RequestMapping(value="/tokenpage",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView tokenPage(){
    ModelAndView model = new ModelAndView();
    model.setViewName("tokenPage");
    return model;
    }
    
    
    
    
    @RequestMapping(value="/posttoken",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView tokenPage(HttpServletRequest request,HttpSession session,@RequestParam(defaultValue = "1") int page_num){
    ModelAndView model = new ModelAndView();
    
    String alertMessage = "NOT SUCCESSFUL";
    
         String username = (String) request.getSession().getAttribute("username");
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
         request.getSession().setAttribute("username", username);
        System.out.println("USERNAME FROM POSTTOKEN IS = " + username);
        System.out.println("username________________________________ " + username);
     String token = request.getParameter("token");
            System.out.println("token from page ================================= " + token);
        
            String checktoken = incidence.checkToken(username);
             System.out.println("token from database ================================= " + checktoken);
               String getUserMapping = incidence.userAssign(username);
             System.out.println("getUserMapping!!!!!!!!!!!!!!!!!!!!+++++++++++++++++++++++++++++ " + getUserMapping);
        if (token.equals(checktoken)){

            System.out.println("EXECUTED SUCCESSFULLY");
            int loginUpdate = incidence.updateFirstLogin(username);
            System.out.println("USER LOGIN UPDATED!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            String getInstitutionName = incidence.getIntitutionNameTwo(username);
//            System.out.println("getInstitutionName =============================== " + getInstitutionName);
//            List<incidence> getIncident = incidence.getLoggedIncidenceForFamiliarInstitute(getInstitutionName);
//            int total = getIncident.size();
//            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
//            System.out.println("page:" + page_num);
//            com.util.Pagination pg = new com.util.Pagination(page_num, total);
//            String limit = pg.getLimit();
//            List<incidence> getIncidence = incidence.getLoggedIncidenceForFamiliarInstituteLimit(getInstitutionName,limit);
//            pg.setLink(link);
//            List<User> getInstitutions = incidence.getAllInstitutions();
//            System.out.println("size!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + getInstitutions.size());
//            model.addObject("getInstitutions", getInstitutions);
//            String pages = pg.getControls();
//            System.out.println("pages:" + pages);
//            model.addObject("pagination", pages);
//            System.out.println("incidence:" + getIncident.size());
//            model.addObject("getIncidence", getIncidence);
            model.addObject("user", "User verified! Please sign in with username and password");
            model.setViewName("index");
//                 alertMessage = "Successful";
//
//            String getInstitutionName = incidence.getIntitutionNameTwo(username);
//            System.out.println("getInstitutionName =============================== " + getInstitutionName);
//            List<incidence> getIncident = incidence.getLoggedIncidenceForFamiliarInstitute(getInstitutionName);
//            int total = getIncident.size();
//            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
//            System.out.println("page:" + page_num);
//            com.util.Pagination pg = new com.util.Pagination(page_num, total);
//            String limit = pg.getLimit();
//            List<incidence> getIncidence = incidence.getLoggedIncidenceForFamiliarInstituteLimit(getInstitutionName,limit);
//            pg.setLink(link);
//            List<User> getInstitutions = incidence.getAllInstitutions();
//            System.out.println("size!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + getInstitutions.size());
//            model.addObject("getInstitutions", getInstitutions);
//            String pages = pg.getControls();
//            System.out.println("pages:" + pages);
//            model.addObject("pagination", pages);
//            System.out.println("incidence:" + getIncident.size());
//            model.addObject("getIncidence", getIncidence);
//            model.setViewName("incidentReport");
            
         
      
//    } else if (getUserMapping.equals("2")){
//         
//      System.out.println("EXECUTED SUCCESSFULLY");
//             int loginUpdate = incidence.updateFirstLogin(username);
//             System.out.println("GOT HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                 alertMessage = "Successful";
////                 model.addObject("user","USER VERIFIED! Please Sign In With Username And Password");
//            String getInstitutionName = incidence.getIntitutionNameTwo(username);
//             List<incidence> getIncident = incidence.getAllIncidenceInDatabase();
//            int total = getIncident.size();
//            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
//            System.out.println("page:" + page_num);
//            com.util.Pagination pg = new com.util.Pagination(page_num, total);
//            String limit = pg.getLimit();
//            List<incidence> getIncidence = incidence.getAllIncidenceInDatabaseLimit(limit);
//            pg.setLink(link);
//            String pages = pg.getControls();
//            System.out.println("pages:" + pages);
//            model.addObject("pagination", pages);
////            System.out.println("incidence:" + getIncident.size());
//            model.addObject("getIncidence", getIncidence);
//            model.addObject("getInstitutionName", getInstitutionName);
//            return "redirect:/superuser";
//    
//    } else if(getUserMapping.equals("1")){
//    
//         int loginUpdate = incidence.updateFirstLogin(username);
//             System.out.println("GOT HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                 alertMessage = "Successful";
//         List<incidence> getIncident = incidence.getAllIncidenceInDatabase();
//            int total = getIncident.size();
//            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
//            System.out.println("page:" + page_num);
//            com.util.Pagination pg = new com.util.Pagination(page_num, total);
//            String limit = pg.getLimit();
//            List<incidence> getIncidence = incidence.getAllIncidenceInDatabaseLimit(limit);
//            pg.setLink(link);
//            String pages = pg.getControls();
//            System.out.println("pages:" + pages);
//            model.addObject("pagination", pages);
////            System.out.println("incidence:" + getIncident.size());
//            model.addObject("getIncidence", getIncidence);
//            return "redirect:/incidentReport";
//    
//    }
        }
        
    return model;
}
    
    
    
    
  @RequestMapping(value="/incidentReport",method={RequestMethod.GET,RequestMethod.POST}) 
  public ModelAndView incidentReportThirdPage(HttpServletRequest request, HttpSession session, @RequestParam(defaultValue = "1") int page_num){
  ModelAndView model = new ModelAndView();
   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   String username = auth.getName();
  List<incidence> getIncident = incidence.getAllIncidenceInDatabase();
  int total = getIncident.size();
  String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
  System.out.println("page:" + page_num);
  com.util.Pagination pg = new com.util.Pagination(page_num, total);
  String limit = pg.getLimit();
  List<incidence> getIncidence = incidence.getAllIncidenceInDatabaseLimit(limit);
  pg.setLink(link);
  String pages = pg.getControls();
  System.out.println("pages:" + pages);
  model.addObject("pagination", pages);
//            System.out.println("incidence:" + getIncident.size());
 model.addObject("username", username);
  model.addObject("getIncidence", getIncidence);
  model.setViewName("incidentreport3");
  return model;
  }
    
  @RequestMapping(value="/managecompany",method={RequestMethod.GET,RequestMethod.POST}) 
  public ModelAndView manageCompany(HttpServletRequest request, HttpSession session){
  ModelAndView model = new ModelAndView();
   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
   auth.getName();
  List<User> getInstitutions = incidence.getAllInstitutions();
  List<Product> getProduct = incidence.getAllProducts();
  model.addObject("getInstitutions", getInstitutions);
  model.addObject("getProduct", getProduct);
  model.setViewName("manageinstitution");
  return model;
  }
    
  @ResponseBody
  @RequestMapping(value="/companyreg",method={RequestMethod.GET,RequestMethod.POST}) 
  public String manageCompanyPage(HttpServletRequest request, HttpSession session){
      String alertMessage = "Institution Successfully Added";
  ModelAndView model = new ModelAndView();
  String institutionname= request.getParameter("institutionname");
  String products = request.getParameter("products");
  
  boolean company = incidence.CreateInstitution(institutionname, products);
  if (company == true){
      alertMessage = "Institution Successfully Added";
      System.out.println("INSTITUTION REGISTERED");
//      model.setViewName("manageinstitution");
  }
  return alertMessage;
  }
    
  
  
  
    @RequestMapping(value="/manageProducts",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView manageProductsPage(HttpServletRequest request,HttpSession session){
    ModelAndView model = new ModelAndView();
     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
    List<Product> getProduct = incidence.getAllProducts();
     model.addObject("getProduct", getProduct);
    model.setViewName("manageproducts");
    
    return model;
    
    }
    
    
    
    
    @ResponseBody
    @RequestMapping(value="/productmanage",method={RequestMethod.POST,RequestMethod.GET})
    public String ProductManagePage(HttpSession session, HttpServletRequest request){
        String alertMessage = "Product Not Successfully Created";
    ModelAndView model = new ModelAndView();
    
//    request.getSession().getAttribute("username");
    
    String productname = request.getParameter("productname");
        System.out.println("productname++++++++++++++++++++++++++++++++++++++++++++ " + productname);
        if (productname==null)
    productname="";
    String productdescription = request.getParameter("productdescription");
        System.out.println("productdescription+++++++++++++++++++++++++++++++++++++++++++++ " + productdescription);
         if (productdescription==null)
    productdescription="";
    int createproduct = incidence.createProducts(productname, productdescription);
    
    
    if(createproduct > 0){
         
        System.out.println("WERE ARE HERE############################################################");
        alertMessage = "Product Successfully Created";
   }
    return alertMessage;
    } 
    
    
     @ResponseBody
    @RequestMapping(value="/deleteUserInstitution",method={RequestMethod.GET,RequestMethod.POST})
     public String deleteCollectionItem(HttpServletRequest request, HttpServletRequest req, HttpServletResponse res, HttpSession session){
     
         int status = 0;
        String message = "Failed";
        
        Object user = request.getSession().getAttribute("username");
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return message;
//        }
        
        
//        Object checkSession = (String) session.getAttribute("username");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernm = auth.getName();
        System.out.println("checksession " + usernm);
       String userrole = incidence.userAssign(usernm);
       String compare = "1";
        if(userrole.equals(compare)){
            
        
        String getFilesArray = req.getParameter("array");

        String myArray[] = getFilesArray.split(",");
        
        int arry[] = incidence.deleteUserInstitution(myArray);
        System.out.println(">>>>>>>>>>>?????????????? " + arry.length);
        if(arry.length > 0){
        message = "success";
        }
        
        }else{
            
        String getFilesArrays = req.getParameter("array");

        String myArrays[] = getFilesArrays.split(",");
        
        int arrys[] = incidence.deleteUserInstitution(myArrays);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> " + arrys.length);
        if (arrys.length > 0){
        message = "success";
        }
        }
        return message;
     }
     
     
     
     
     
     @ResponseBody
    @RequestMapping(value="/deleteUserProduct",method={RequestMethod.GET,RequestMethod.POST})
     public String deleteProducts(HttpServletRequest request, HttpServletRequest req, HttpServletResponse res, HttpSession session){
     
         int status = 0;
        String message = "Failed";
        
        Object user = request.getSession().getAttribute("username");
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return message;
//        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernm = auth.getName();
        System.out.println("checksession " + usernm);
       String userrole = incidence.userAssign(usernm);
       String compare = "1";
        if(userrole.equals(compare)){
            
        
        String getFilesArray = req.getParameter("array");

        String myArray[] = getFilesArray.split(",");
        
        int arry[] = incidence.deleteProduct(myArray);
        System.out.println(">>>>>>>>>>>?????????????? " + arry.length);
        if(arry.length > 0){
        message = "success";
        }
        
        }else{
            
        String getFilesArrays = req.getParameter("array");

        String myArrays[] = getFilesArrays.split(",");
        
        int arrys[] = incidence.deleteProduct(myArrays);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> " + arrys.length);
        if (arrys.length > 0){
        message = "success";
        }
        }
        return message;
     }
     
     
     
     
     @RequestMapping(value="/superuser" , method={RequestMethod.GET,RequestMethod.POST})
     public ModelAndView SuperSoftUser(HttpServletRequest request,HttpSession session,@RequestParam(defaultValue = "1") int page_num){
     ModelAndView model = new ModelAndView();
           Object user = request.getSession().getAttribute("username");
           String username = user.toString();
           System.out.println("username++============================================== " + username);
           String alertMessage = "Not Successful";
         
                 alertMessage = "Successful";
//           
            String getInstitutionName = incidence.getIntitutionNameTwo(username);
             List<incidence> getIncident = incidence.getAllIncidenceInDatabase();
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/superuser/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.getAllIncidenceInDatabaseLimit(limit);
            pg.setLink(link);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
//            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
            model.addObject("getInstitutionName", getInstitutionName);
            model.setViewName("supersoftuser");
            return model;
     
     }
     
     
     
     
      
    @RequestMapping(value="/updateInstitutionPage/{id}/{institutionname}/{products}", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView updateAmountPage(HttpServletRequest request , HttpSession session, @PathVariable String institutionname, @PathVariable String products, @PathVariable int id){
   
        
        
        
       Object user = request.getSession().getAttribute("username");
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return model;
//        }
        
//        boolean isUserAllowed = false;
//
//        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
//
//        for (UserPages userPgs: userPages) {
//            if (userPgs.getUrlmapping() != null) {
//                if (userPgs.getUrlmapping().equals("editPartners")) {
//                    isUserAllowed = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
//
//        if (isUserAllowed == false) {
//            model.setViewName("redirect:/welcome");
//            return model;
//        }


          
          
//        model.addObject("id" + id);
        session.setAttribute("id",id);
        
        model.addObject("institutionname", institutionname);
        model.addObject("products", products);
        
        
        
        System.out.println("id " + id);
        System.out.println("institutionname: " + institutionname);
        System.out.println("products " + products);
      
        
//        Object checkSession = (String) session.getAttribute("username");
//        String usernm = checkSession.toString();
//        System.out.println("checksession " + usernm);
         model.setViewName("editInstitutionPage");
        return model;
   
    }
     
     
     @RequestMapping(value="/editinstitution",method={RequestMethod.GET,RequestMethod.POST})
     public ModelAndView EditInstitutionPage(){
     ModelAndView model = new ModelAndView();
     
     model.setViewName("editInstitutionPage");
     return model;
     }
     
     
     @RequestMapping(value="/editinstitute",method={RequestMethod.GET,RequestMethod.POST})
     public ModelAndView EditInstitutePageTwo(HttpServletRequest request,HttpSession session){
     ModelAndView model = new ModelAndView();
     Object user = session.getAttribute("id");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
     int id = (Integer) user;
         System.out.println("id!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + id);
     String institutionname = request.getParameter("institutionname");
     String product = request.getParameter("product");
     
     int updateinstitute = incidence.updateInstitution(institutionname, product, id);
         System.out.println("WERE GOT HERE THO, UPDATE COMPLETE");
          List<User> getInstitutions = incidence.getAllInstitutions();
          model.addObject("getInstitutions", getInstitutions);
     model.setViewName("manageinstitution");
     return model;
     }
     
     
     
     
        
    @RequestMapping(value="/updateProducPage/{id}/{productname}/{productdescription}", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView updateProductPage(HttpServletRequest request , HttpSession session, @PathVariable String productname, @PathVariable String productdescription, @PathVariable int id){
   
        
        
        
       Object user = request.getSession().getAttribute("username");
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return model;
//        }
        
//        boolean isUserAllowed = false;
//
//        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
//
//        for (UserPages userPgs: userPages) {
//            if (userPgs.getUrlmapping() != null) {
//                if (userPgs.getUrlmapping().equals("editPartners")) {
//                    isUserAllowed = true;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
//
//        if (isUserAllowed == false) {
//            model.setViewName("redirect:/welcome");
//            return model;
//        }


          
          
//        model.addObject("id" + id);
        session.setAttribute("id",id);
        
        model.addObject("productname", productname);
        model.addObject("productdescription", productdescription);
        
        
        
        System.out.println("id " + id);
        System.out.println("productname: " + productname);
        System.out.println("productdescription " + productdescription);
      
        
//        Object checkSession = (String) session.getAttribute("username");
//        String usernm = checkSession.toString();
//        System.out.println("checksession " + usernm);
         model.setViewName("editProductPage");
        return model;
   
    }
     
     
    @RequestMapping(value="/editproductpage",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView editProductpage(){
    ModelAndView model = new ModelAndView();
    
    model.setViewName("editProductPage");
    return model;
    }
     
    @RequestMapping(value="/edituserpage",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView UserEditPage(){
    ModelAndView model = new ModelAndView();
    model.setViewName("EditUserPage");
    return model;
    }
    
    
    
    @RequestMapping(value="/editUser",method={RequestMethod.POST})
    public ModelAndView editUserPage(HttpServletRequest request,HttpSession session){
    ModelAndView model = new ModelAndView();
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user = auth.getName();
        System.out.println("user!!!!!!!!!!!!!!" + user);
        
        String username = request.getParameter("username");
        System.out.println("username " + username);
        String institutionname = request.getParameter("institutionname");
        String phonenumber = request.getParameter("phonenumber");
        String idi = request.getParameter("firsttimelogin");
        
        Object use = request.getSession().getAttribute("userid");
        int id = (Integer) use;
        System.out.println("id!!!!!!!!!!!!!!!! " + id);
        int firsttimelogin = Integer.parseInt(idi);
        System.out.println("firsttimelogin " + firsttimelogin);
        
        int updateUser = incidence.updateUser(id, institutionname, phonenumber, firsttimelogin, username);
        
        List<UserCreate> getusers = incidence.getAllUsers();
        model.addObject("getusers", getusers);
       model.setViewName("createuser");
         return model;
    }
    
    
    
     
     @RequestMapping(value="/editproduct",method={RequestMethod.GET,RequestMethod.POST})
     public ModelAndView editProducts(HttpServletRequest request, HttpSession session){
     ModelAndView model = new ModelAndView();
     
     Object user = session.getAttribute("id");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
     int id = (Integer) user;
     
     String productname = request.getParameter("productname");
     String productdescription = request.getParameter("productdescription");
     
     int updateproduct = incidence.updateProduct(productname, productdescription, id);
         List<Product> getProduct = incidence.getAllProducts();
     model.addObject("getProduct", getProduct);
     model.setViewName("manageproducts");
     return model;
     }
     
     
     
     @RequestMapping(value="/failedLogin",method={RequestMethod.GET,RequestMethod.POST})
     public ModelAndView FailedLoginPage(){
     ModelAndView model = new ModelAndView();
     model.addObject("user", "Wrong username or password");
     model.setViewName("index");
     return model;
     }
     
     
     
     
     @RequestMapping(value="/newUser",method={RequestMethod.GET,RequestMethod.POST})
     public ModelAndView RegisterUserPage(@RequestParam(defaultValue = "1") int page_num){
     ModelAndView model = new ModelAndView();
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
     List<User> getInstitutions = incidence.getAllInstitutions();
     model.addObject("getInstitutions", getInstitutions);
     List<UserType> getUserType = incidence.userType();
     model.addObject("getUserType", getUserType);
     
     List<UserCreate> getusers = incidence.getAllUsers();
        
            int total = getusers.size();
            String link = "/IncidenceManagementReportSystem/newUser/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<UserCreate> getuserslimit = incidence.getAllUsersLimit(limit);
            pg.setLink(link);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
//            System.out.println("incidence:" + getIncident.size());
            model.addObject("getusers", getuserslimit);
           
     model.setViewName("createuser");
     return model;
     }
     
    
     
     @RequestMapping(value="/usermanagement",method={RequestMethod.GET})
     public ModelAndView usermanagementPage(){
     ModelAndView model = new ModelAndView();
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
     model.setViewName("UserManagement");
     return model;
     
     }
     
     @RequestMapping(value="/downloaderror",method={RequestMethod.GET,RequestMethod.POST})
     public ModelAndView downloadErrorPage(){
     ModelAndView model = new ModelAndView();
     model.setViewName("downloaderrorpage");
     return model;
     
     }
     
     @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            System.out.println("DEBUGGING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            String username = auth.getName();
            System.out.println("USERNAME!!!!!!!!!!!!!!!!!!!!!" + username);
           
        }

        return "redirect:/?logout";
    }

    
    
    @RequestMapping(value="/UpdateUser/{id}/{username}/{institutionname}/{phonenumber}/{firsttimelogin}", method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView updateUserPage(HttpServletRequest request , HttpSession session, @PathVariable String username, @PathVariable String institutionname, @PathVariable String phonenumber, @PathVariable int firsttimelogin,@PathVariable int id){
    ModelAndView model = new ModelAndView();
     Object user = request.getSession().getAttribute("username");
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       auth.getName();
    
       session.setAttribute("userid", id);
       model.addObject("id", id);
        model.addObject("username", username);
        model.addObject("institutionname", institutionname);
         model.addObject("phonenumber", phonenumber);
         model.addObject("firsttimelogin", firsttimelogin);
        System.out.println("WE ARE HERE!");
         List<UserCreate> getusers = incidence.getAllUsers();
        model.addObject("getusers", getusers);
    model.setViewName("EditUserPage");
    return model;
    }
     
    
    
    
    
    
    
    
    
    
    
    
    @ResponseBody
    @RequestMapping(value="/newUser/deleteUsers",method={RequestMethod.GET,RequestMethod.POST})
     public String deleteSearchUser(HttpServletRequest request, HttpServletRequest req, HttpServletResponse res, HttpSession session){
     
         int status = 0;
        String message = "Failed";
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernm = auth.getName();
//        Object user = request.getSession().getAttribute("username");
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return message;
//        }
        
    
        System.out.println("checksession " + usernm);
       String userrole = incidence.userAssign(usernm);
       String compare = "1";
        if(userrole.equals(compare)){
            
        
        String getFilesArray = req.getParameter("array");

        String myArray[] = getFilesArray.split(",");
        
        int arry[] = incidence.deleteUser(myArray);
        System.out.println(">>>>>>>>>>>?????????????? " + arry.length);
        if(arry.length > 0){
        message = "success";
        }
        
        }else{
            
        String getFilesArrays = req.getParameter("array");

        String myArrays[] = getFilesArrays.split(",");
        
        int arrys[] = incidence.deleteUser(myArrays);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> " + arrys.length);
        if (arrys.length > 0){
        message = "success";
        }
        }
        
    return "redirect:/deleteUsers";
     }
    
    
    
    
    
    
      
     @ResponseBody
    @RequestMapping(value="/deleteUsers",method={RequestMethod.GET,RequestMethod.POST})
     public String deleteUser(HttpServletRequest request, HttpServletRequest req, HttpServletResponse res, HttpSession session){
     
         int status = 0;
        String message = "Failed";
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usernm = auth.getName();
//        Object user = request.getSession().getAttribute("username");
        ModelAndView model = new ModelAndView();
//        if (user == null) {
//            model.setViewName("index");
//            return message;
//        }
        
    
        System.out.println("checksession " + usernm);
       String userrole = incidence.userAssign(usernm);
       String compare = "1";
        if(userrole.equals(compare)){
            
        
        String getFilesArray = req.getParameter("array");

        String myArray[] = getFilesArray.split(",");
        
        int arry[] = incidence.deleteUser(myArray);
        System.out.println(">>>>>>>>>>>?????????????? " + arry.length);
        if(arry.length > 0){
        message = "success";
        }
        
        }else{
            
        String getFilesArrays = req.getParameter("array");

        String myArrays[] = getFilesArrays.split(",");
        
        int arrys[] = incidence.deleteUser(myArrays);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> " + arrys.length);
        if (arrys.length > 0){
        message = "success";
        }
        }
        return message;
     }
    
     
//     
//    @RequestMapping(value={"/fileattach/{id}"}, method={RequestMethod.GET,RequestMethod.POST})
//     public String AttactLink(@PathVariable int id, HttpServletRequest request, HttpSession session){
//     ModelAndView model = new ModelAndView();
//     
//     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
//        
//        System.out.println("USERNAME IS " + username);
//        System.out.println("ID IS +++++++++++++++++++++++ " + id);
//        model.addObject("userid", id);
//     request.getSession().setAttribute("newfileid", id);
//     
//     return "redirect:/uploadfile";
//     }
    
    
     
     
     
     @RequestMapping(value="/uploadfile",method={RequestMethod.POST,RequestMethod.GET})  
     public ModelAndView upload(@RequestParam CommonsMultipartFile file,HttpSession session,HttpServletRequest request, @RequestParam(defaultValue = "1") int page_num){  
         
//         String oldid = request.getParameter("id");
//        int id = Integer.parseInt(oldid);

        
         
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
//         System.out.println("USERNAME FOR INSERTFILE == " + username);
//          Object getid = request.getSession().getAttribute("newfileid");
//          System.out.println("GETID##################### " + getid);
//             int id = (Integer) getid;
//              String getid = request.getParameter("id");
//              int id = (Integer) getid;
//         System.out.println("INTEGER IS " + id);


        String getid = request.getParameter("userid");
         System.out.println("GETID IS " + getid);
        int id = Integer.parseInt(getid);
         System.out.println("ID FOR UPLOAD IS " + id);

        ModelAndView model = new ModelAndView();
        ServletContext context = session.getServletContext();  
        String path = context.getRealPath(UPLOAD_DIRECTORY);  
         System.out.println("PATH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + path);
        String filename = file.getOriginalFilename(); 
        Object filebody = file.getFileItem();
        request.getSession().setAttribute("filebody", filebody);
         System.out.println("FILEBODY ###### " + filebody);
         System.out.println("FILENAME == " + filename);
       
        System.out.println(path+" "+filename);  
        try{  
        byte barr[]=file.getBytes();  
          
        BufferedOutputStream bout = new BufferedOutputStream(  new FileOutputStream(path+" "+filename));  
        bout.write(barr);  
        bout.flush();  
        bout.close();  
        
         System.out.println("FILE IS DROPPING");
            
         String filepath = path+" "+filename;
         
            System.out.println("filepath========== " + filepath);
            
        int fileupload = incidence.InsertFile(filename,filepath,id);
       
        System.out.println("FILE HAS BEEN DROPPED INTO THE DATABASE");
        
          String CheckUserId = incidence.userAssign(username);
        System.out.println("CheckUserId:" + CheckUserId);
        
             String getInstitutionName = incidence.getIntitutionNameTwo(username);
            System.out.println("getInstitutionName =============================== " + getInstitutionName);
            
            
             if (CheckUserId.equalsIgnoreCase("2")){
             List<incidence> getIncident = incidence.getAllIncidenceInDatabase();
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.getAllIncidenceInDatabaseLimit(limit);
            pg.setLink(link);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
//            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);
            model.setViewName("supersoftuser");
            
             }else if (CheckUserId.equalsIgnoreCase("3")){
          List<incidence> getIncident = incidence.getLoggedIncidenceForFamiliarInstituteForFileInsert(getInstitutionName);
           
            int total = getIncident.size();
            String link = "/IncidenceManagementReportSystem/incidentreport/" + "?page_num=";
            System.out.println("page:" + page_num);
            com.util.Pagination pg = new com.util.Pagination(page_num, total);
            String limit = pg.getLimit();
            List<incidence> getIncidence = incidence.getLoggedIncidenceForFamiliarInstituteForFileInsertLimit(getInstitutionName, limit);
            pg.setLink(link);
            List<User> getInstitutions = incidence.getAllInstitutions();
            System.out.println("size!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + getInstitutions.size());
            model.addObject("getInstitutions", getInstitutions);
            String pages = pg.getControls();
            System.out.println("pages:" + pages);
            model.addObject("pagination", pages);
            System.out.println("incidence:" + getIncident.size());
            model.addObject("getIncidence", getIncidence);

                
         model.addObject("institutionname", getInstitutionName);
         model.addObject("username", username);
        model.setViewName("incidentReport");
             }
        }catch(Exception e){System.out.println(e);}  
        
        return model;
    }  
     
     
     
     
//     @RequestMapping("/downloadfile")
//     public ModelAndView showFiles(){
//     ModelAndView model = new ModelAndView();
//     File folder = new File(UPLOAD_DIRECTORY);
//     File[] listoffiles = folder.listFiles();
//     model.addObject("files", listoffiles);
//     model.setViewName("incidentReport");
//     return model;
//     }
//     
     
     
   
     
     
     
     
     
     
     
     
     @ResponseBody
     @RequestMapping(value={"/filedownload/{id}/{filename}"})
     public void ShowFile(HttpServletResponse response,HttpSession session,HttpServletRequest request,@PathVariable int id){
         
           
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         String username = auth.getName();

         ServletContext context = session.getServletContext();

//      String format = "none";
//         int index = filename.lastIndexOf(".");
//         if (index > 0) {
//             format = filename.substring(index + 1);
//             format = format.toLowerCase();
//         }
         
//             System.out.println("NEW FORMAT = " + format);
//     String path = "C:\\Users\\oreoluwa\\Desktop\\IncidenceManagementReportSystem\\build\\web";


         String filename = incidence.SelectFilename(id);
          
         String nofile = "No file attached";
         if (filename.equalsIgnoreCase(nofile)){
             System.out.println("STOP");
         }else{
         
         System.out.println("FILENAME " + filename);
//         String filenewext = filename + ".pdf";
//         System.out.println("NEW FILENAME == " + filenewext);
         String path = incidence.SelectFilepath(filename);

//        Object filebody = request.getSession().getAttribute("")
        
         System.out.println("FILEPATH == " + path);
          
         System.out.println("DEBUG!!!!!!!!!!!!");
       
       
         
         
         if (filename.indexOf(".doc")>-1) response.setContentType("application/msword");
         if (filename.indexOf(".docx")>-1) response.setContentType("application/msword");
         if (filename.indexOf(".xls")>-1) response.setContentType("application/vnd.ms-excel");
         if (filename.indexOf(".csv")>-1) response.setContentType("application/vnd.ms-excel");
          if (filename.indexOf(".ppt")>-1) response.setContentType("application/ppt");
           if (filename.contains(".pdf")) response.setContentType("application/pdf");
            if (filename.indexOf(".zip")>-1) response.setContentType("application/zip");
           
            response.setHeader("Content-Disposition", "attachment; filename=" +filename);
            response.setHeader("Content-Transfer-Encoding", "binary");
            
            try{
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream(path);
            int len;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) > 0){
            bos.write(buf, 0, len);
            }
            bos.close();
            response.flushBuffer();
            }catch(IOException e){
            e.printStackTrace();
            }}
     }
     
     
     
     
     
     @RequestMapping(value={"/editincidence/{id}/{title}"},method={RequestMethod.GET,RequestMethod.POST})
     public ModelAndView EditIncidencePage(HttpSession session, HttpServletRequest request, @PathVariable int id){
     ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
         String getInstitutionName = incidence.getIntitutionNameTwo(username);
         model.addObject("institutionname", getInstitutionName);
         System.out.println("ID FOR EDITINCIDENCE " + id);
         model.addObject("username", username);
         model.addObject("userid", id);
     model.setViewName("editIncidence");
     return model;
     }
     
     
     
//      
//     @ResponseBody
//    @RequestMapping(value="/newUser/deleteUsers",method={RequestMethod.GET,RequestMethod.POST})
//     public String deleteSearchUser(HttpServletRequest request, HttpServletRequest req, HttpServletResponse res, HttpSession session){
//     
//         int status = 0;
//        String message = "Failed";
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String usernm = auth.getName();
////        Object user = request.getSession().getAttribute("username");
//        ModelAndView model = new ModelAndView();
////        if (user == null) {
////            model.setViewName("index");
////            return message;
////        }
//        
//    
//        System.out.println("checksession " + usernm);
//       String userrole = incidence.userAssign(usernm);
//       String compare = "1";
//        if(userrole.equals(compare)){
//            
//        
//        String getFilesArray = req.getParameter("array");
//
//        String myArray[] = getFilesArray.split(",");
//        
//        int arry[] = incidence.deleteUser(myArray);
//        System.out.println(">>>>>>>>>>>?????????????? " + arry.length);
//        if(arry.length > 0){
//        message = "success";
//        }
//        
//        }else{
//            
//        String getFilesArrays = req.getParameter("array");
//
//        String myArrays[] = getFilesArrays.split(",");
//        
//        int arrys[] = incidence.deleteUser(myArrays);
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> " + arrys.length);
//        if (arrys.length > 0){
//        message = "success";
//        }
//        }
//        return message;
//     }
     
//     
//     
//     @RequestMapping(value="/uploadfile",method=RequestMethod.POST)  
//      public ModelAndView upload(@ModelAttribute("formupload") FileUpload fileupload, BindingResult result,HttpSession session) throws IOException{  
//       
//          filevalidator.validate("fileupload", result);
//          
//          if (result.hasErrors()){
//           return new ModelAndView("incidentReport");   
//          }
// 
//          return new ModelAndView("success","filenames",processUpload(fileupload));
//             
//      }
//
//        private List<String> processUpload(FileUpload files) throws IOException{
//           List<String> filenames = new ArrayList<String>();
//           
//           CommonsMultipartFile[] commonsmultipartfiles = files.getFile();
//           for (CommonsMultipartFile multipartfile : commonsmultipartfiles){
//           FileCopyUtils.copy(multipartfile.getBytes(), new File("C:\\uploads\\" + multipartfile.getOriginalFilename()));
//           filenames.add(multipartfile.getOriginalFilename());
//           }
//           
//           return filenames;
//        }


//     
//     @RequestMapping(value="/uploadfile",method={RequestMethod.GET,RequestMethod.POST})
//     public ModelAndView UploadFilePage(@RequestParam("file") MultipartFile file){
//     ModelAndView model = new ModelAndView();
//     model.addObject("file", file);
//         System.out.println("file: " + file);
//         System.out.println("################################### FILE UPLOADED ###################################");
//     model.setViewName("incidentReport");
//     return model;
//     }


//     
//      @RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
//    public ModelAndView uploadFiles(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//        Object user = session.getAttribute("username");
//            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
//        ModelAndView model1 = new ModelAndView();
//        if (user == null) {
//            model1.setViewName("login");
//            return model1;
//        }
//        
////        boolean isUserAllowed = false;
////
////        List<UserPages> userPages = simpleDbService.getSpecificUserPages((String) user);
////
////        for (UserPages userPgs: userPages) {
////            if (userPgs.getUrlmapping() != null) {
////                if (userPgs.getUrlmapping().equals("uploadFiles")) {
////                    isUserAllowed = true;
////                    break;
////                }
////            }
////        }
////
////        System.out.println(">>>>>>>>>>>>>> isUserAllowed: " + isUserAllowed);
////
////        if (isUserAllowed == false) {
////            model1.setViewName("redirect:/welcome");
////            return model1;
////        }
//
////        String username = (String) user;
//
//        StringBuilder sb = new StringBuilder();
//
//        try {
//            response.setContentType("text/html");
//            PrintWriter out = response.getWriter();
//
//            String appPath = request.getServletContext().getRealPath("");
//            appPath = appPath.replace('\\', '/');
//
//            if (appPath.endsWith("/")) {
//                fullSavePath = appPath + SAVE_DIRECTORY;
//            } else {
//                fullSavePath = appPath + "/" + SAVE_DIRECTORY;
//            }
//
//            File fileSaveDir = new File(fullSavePath);
//            if (!fileSaveDir.exists()) {
//                fileSaveDir.mkdir();
//            }
//
//            MultipartRequest m = new MultipartRequest(request, fullSavePath, 4000000);
//
//            Enumeration uploadedFile = (Enumeration) m.getFileNames();
//            String name_filee = "";
//
//            while (uploadedFile.hasMoreElements()) {
//                String name_file = (String) uploadedFile.nextElement();
//                name_filee = m.getFilesystemName(name_file);
//                sb.append(SAVE_DIRECTORY).append("/").append(name_filee).append("EOF");
//            }
//
//            String imgFilePath = sb.toString();
//            session.setAttribute("filepath", imgFilePath);
//            sb.setLength(0);
//
//            fullSavePath = fullSavePath.replace("//", "\\\\");
//            fullSavePath = fullSavePath.concat("/").concat(name_filee);
//
////            String walletInst = (String) session.getAttribute("walletInst");
//
////            int maxLevel = simpleDbService.getMaxApprovalLevel(walletInst);
//
//            String sql = "INSERT INTO tbl_filenames (file_name, location, uploaded_by, date_uploaded, financialinstitutioncode, approval_status) VALUES (?,?,?,now(), ?, ?)";
//
//            int file_id = pService.insertFilename(sql, name_filee, fullSavePath, username);
//
//            BigDecimal totAmt = readXLSFile(fullSavePath, username, file_id, name_filee);
//
////            pService.updatePayment(file_id, totAmt.toString());
//
//        } catch (Exception ex) {
//            System.out.println("CoreController :: uploadImage :: Error occurred...");
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public BigDecimal readXLSFile(String path, String uploaded_by, int fileid, String exactFileName) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        ArrayList<String[]> batchList = new ArrayList<String[]>();
//        BigDecimal totAmt = new BigDecimal(0);
//
//        String inputFilename = new File(path).getName();
//        switch (inputFilename.substring(inputFilename.lastIndexOf(".") + 1, inputFilename.length())) {
//            case "csv":
//                CSVReader reader = null;
//                reader = new CSVReader(new FileReader(path));
//                String[] line;
//
//                int j = 0;
//
//                sb.append("INSERT INTO tbl_payments (ACCOUNT_NUMBERS,BANK_CODE, AMOUNT,ACCOUNT_NAME,NARRATIONS, FILE_ID, FILE_NAME, phonenumber, DATE_UPLOADED) VALUES ");
//
//                while ((line = reader.readNext()) != null) {
//                    String[] arrBatchList = new String[8];
//
//                    if (line[2].length() < 3) {
//                        line[2] = "0" + line[2];
//                    }
//
////                    line[2] = getMappedBankCode(line[2]);
//
//                    if (line[5].length() > 160) {
//                        line[5] = line[5].substring(0, 160);
//                    }
//
//                    if (line[4].indexOf("'") > 0) {
//                        line[4] = line[4].replace("'", "\\'");
//                    }
//
//                    if (line[5].indexOf("'") > 0) {
//                        line[5] = line[5].replace("'", "\\'");
//                    }
//
//                    arrBatchList[0] = line[1];
//                    arrBatchList[1] = line[2];
//                    arrBatchList[2] = line[3];
//
//                    if (j > 0) {
//                        System.out.println("CoreController :: uploadImage :: line[3]: " + line[3]);
//
//                        if (new BigDecimal (line[3]).compareTo(new BigDecimal(1)) < 1)
//                            continue;
//
//                        totAmt = totAmt.add(new BigDecimal(line[3]));
//                    }
//
//                    arrBatchList[3] = line[4];
//                    arrBatchList[4] = line[5];
//                    arrBatchList[5] = String.valueOf(fileid);
//                    arrBatchList[6] = exactFileName;
//
//                    if (line[6] != null) {
//                        if (line[6].length() == 11) {
//                            arrBatchList[7] = "+234" + line[6].substring(1);
//                        } else if (line[6].length() == 10) {
//                            arrBatchList[7] = "+234" + line[6];
//                        } else {
//                            arrBatchList[7] = "";
//                        }
//                    }
//
//                    batchList.add(arrBatchList);
//
//                    j++;
//                }

//                int maxNumberOfRecords = 5000;
//
//                if (batchList.size() < maxNumberOfRecords) {
//                    int i = 0;
//
//                    for (String[] arrBatchList : batchList) {
//                        if (i > 0) {
//                            sb.append("(");
//                            sb.append("'").append(arrBatchList[0]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[1]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[2]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[3]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[4]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[5]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[6]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[7]).append("'");
//                            sb.append(", now()),\n");
//                        }
//
//                        i++;
//                    }
//
////                    pService.paymentUploads(sb.toString().substring(0, sb.toString().length() - 2));
//                } else {
//                    int howMany900s = batchList.size() / maxNumberOfRecords;
//                    int remainder = batchList.size() % maxNumberOfRecords;
//
//                    int c = 0;
//
//                    for (int i = 1; i < howMany900s + 1; i++) {
//                        for (int b = 0; b < maxNumberOfRecords; b++) {
//                            String[] arrBatchList = batchList.get(c);
//
//                            if (c > 0) {
//                                sb.append("(");
//                                sb.append("'").append(arrBatchList[0]).append("'").append(",");
//                                sb.append("'").append(arrBatchList[1]).append("'").append(",");
//                                sb.append("'").append(arrBatchList[2]).append("'").append(",");
//                                sb.append("'").append(arrBatchList[3]).append("'").append(",");
//                                sb.append("'").append(arrBatchList[4]).append("'").append(",");
//                                sb.append("'").append(arrBatchList[5]).append("'").append(",");
//                                sb.append("'").append(arrBatchList[6]).append("'").append(",");
//                                sb.append("'").append(arrBatchList[7]).append("'");
//                                sb.append(", now()),\n");
//                            }
//
//                            c++;
//                        }
//
////                        pService.paymentUploads(sb.toString().substring(0, sb.toString().length() - 2));
//
//                        sb.setLength(0);
//
//                        sb.append("INSERT INTO tbl_payments (ACCOUNT_NUMBERS,BANK_CODE, AMOUNT,ACCOUNT_NAME,NARRATIONS, FILE_ID, FILE_NAME, phonenumber, date_uploaded) VALUES ");
//                    }
//
//                    if (remainder != 0) {
//                        for (int b = 0; b < remainder; b++) {
//                            String[] arrBatchList = batchList.get(c);
//
//                            c++;
//
//                            sb.append("(");
//                            sb.append("'").append(arrBatchList[0]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[1]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[2]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[3]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[4]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[5]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[6]).append("'").append(",");
//                            sb.append("'").append(arrBatchList[7]).append("'");
//                            sb.append(", now()),");
//                        }
//
//                        pService.paymentUploads(sb.toString().substring(0, sb.toString().length() - 1));
//                    }
////                }
//
//                break;
//            default:
//                System.out.println("Incorrect file");
//        }
//
//        return totAmt;
//    }

     
     
     
     
     
     
     
     
//     JAVA DOWNLOAD FILE FROM URL----------------------------------------------------------------------------------------------------------
//       String url = "https://www.journaldev.com/sitemap.xml";
//        
//        try {
//            downloadUsingNIO(url, "/Users/pankaj/sitemap.xml");
//            
//            downloadUsingStream(url, "/Users/pankaj/sitemap_stream.xml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void downloadUsingStream(String urlStr, String file) throws IOException{
//        URL url = new URL(urlStr);
//        BufferedInputStream bis = new BufferedInputStream(url.openStream());
//        FileOutputStream fis = new FileOutputStream(file);
//        byte[] buffer = new byte[1024];
//        int count=0;
//        while((count = bis.read(buffer,0,1024)) != -1)
//        {
//            fis.write(buffer, 0, count);
//        }
//        fis.close();
//        bis.close();
//    }
//
//    private static void downloadUsingNIO(String urlStr, String file) throws IOException {
//        URL url = new URL(urlStr);
//        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//        fos.close();
//        rbc.close();
//    }

     
     
     
     
     
     
}
     
