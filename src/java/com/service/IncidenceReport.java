/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Product;
import com.model.User;
import com.model.UserCreate;
import com.model.UserType;
import com.model.incidence;
import java.util.List;

/**
 *
 * @author Supersoft
 */
public interface IncidenceReport {
    
    
    
//    public int UserSign(String username,String password);
//      public int logNewIncidence(int trackingnumber,String messagebody,String products,String title);
//    public int logNewIncidence(int trackingnumber);
//    public List<incidence> searchIncidence(String table_name, String search_by,  String limit, boolean superUserFlag);
    public String SelectFilepath(String filename);
     public String SelectFilename(int id);
       public int InsertFile(String filename,String filepath,int id);
          public List<incidence> getLoggedIncidenceForFamiliarInstituteForFileInsert(String institutionname);
          public List<incidence> getLoggedIncidenceForFamiliarInstituteForFileInsertLimit(String institutionname,String limit);
   public int updateUser(int id, String institutionname, String phonenumber,int firsttimelogin,String username);
     public int[] deleteUser(String array[]);
      public List<UserCreate> searchUserDetails(String table_name, String search_by, String search_details);
     public List<UserCreate> searchUserDetailsLimit(String table_name, String search_by, String search_details,String limit);
    public List<UserCreate> getAllUsers();
     public List<UserCreate> getAllUsersLimit(String limit);
    public int updateProduct(String productname, String productdescription, int id);
     public int updateInstitution(String institutionname, String products, int id);
    public int[] deleteProduct(String array[]);
    public int[] deleteUserInstitution(String array[]);
    public List<Product> getAllProducts();
    public int createProducts(String productname,String productdescription);
    public List<incidence> getAllIncidenceInDatabaseLimit(String limit);
     public List<incidence> getAllIncidenceInDatabase();
   public int InsertInstitutionName(String institutionname,String sent_by);
    public String getIntitutionNameTwo(String username);
   public List<incidence> getLoggedIncidenceForFamiliarInstituteLimit(String institutionname,String limit);
  public List<incidence> getLoggedIncidenceForFamiliarInstitute(String institutionname);
     public List<User> getAllInstitutions();
    public boolean CreateInstitution(String institutionname,String products);
    public List<incidence> getAllInstituteIncidenceLimit(String financialinstitutionname , String limit);
    public List<incidence> getAllInstituteIncidence(String financialinstitutionname);
    public int updateFirstLogin(String username);
    public String checkToken(String username);
     public String checkFirstLogin(String username);
     public String userAssign(String username);
    public int userMapping(String username,int usertypeid);
    public String userType(String usertypename);
    public int userRoles(String role,String username);
     public boolean insertEmail (String username, String subject, String emailBody, String toEmailAddress);
    public boolean registerUser(String firstname,String lastname,String phonenumber,String institutionname,String username, String password,int enabled,int firsttimelogin);
    public List<incidence> getIncidenceType();
    public String getFinancialInstitutionName (String username);
    public List<incidence> getAllIncidence();
    public String getInstFromWallets (String username);
    public List<incidence> getAllIncidenceByLimit(String limit);
    public List<incidence> getIncidence (String financialinstitutioncode);
    public long logIncidence(incidence incidence, String trackingnumber);
      public String User(String username);
       public int closeIncidence(String id,String username);
       public List<incidence> getIncidencebyID (int id);
        public List<incidence> getResponsebyID (int id);
        public boolean incidenceReply (String id, String message,String username,String financialinstitutioncode);
//         public List<incidence> searchIncidence(String table_name,String table_name2, String search_by, String search_details, String limit, String username, boolean superUserFlag);
         public String getInstCode (String username);
          public List<incidence> getIntitutionName();
           public List<incidence> searchIncidence(String table_name, String search_by, String search_details);
            public List<incidence> searchIncidenceLimit(String table_name, String search_by, String search_details,String limit);
            public List<incidence> searchIncidenceLimitByUser(String table_name, String search_by, String search_details,String institutionname,String limit);
            public List<incidence> searchIncidenceByUser(String table_name, String search_by, String search_details,String institutionname);
             public List<UserType> userType();
              public int userMappingForAdminCreation(String username,String usertype);
                 public String GetFile(String trackingnumber);
}
