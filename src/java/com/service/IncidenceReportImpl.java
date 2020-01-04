/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Filenames;
import com.model.User;
import com.model.incidence;
import com.model.Product;
import com.model.UserCreate;
import com.model.UserType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Supersoft
 */
@Service
public class IncidenceReportImpl implements IncidenceReport{
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    
    
    
    
    
    
    
    
    
    
      @Override
     public int[] deleteProduct(String array[]){
     String sql = "DELETE FROM tbl_products WHERE id =";
     
        String query[] = new String[array.length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {

            sb.append(sql).append("");
            sb.append("'").append(array[i]).append("'");
            query[i] = sb.toString();
            System.out.println("sqllll:" + sb.toString());
            sb.setLength(0);
        }
      int[] result = jdbcTemplate.batchUpdate(query);
        System.out.println("");

        System.out.println("deleted:" + result.length);

        return result;
     }
    
     
     
     
       @Override
     public int[] deleteUser(String array[]){
     String sql = "DELETE FROM tbl_users WHERE id =";
     
        String query[] = new String[array.length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {

            sb.append(sql).append("");
            sb.append("'").append(array[i]).append("'");
            query[i] = sb.toString();
            System.out.println("sqllll:" + sb.toString());
            sb.setLength(0);
        }
      int[] result = jdbcTemplate.batchUpdate(query);
        System.out.println("");

        System.out.println("deleted:" + result.length);

        return result;
     }
    
     
     
     
     
     
        class EditUserTypeMapper implements RowMapper<UserCreate> {
        public UserCreate mapRow(ResultSet rs, int arg1) throws SQLException {
           UserCreate incidence = new UserCreate();
           incidence.setId(rs.getInt("id"));
            incidence.setInstitutionname(rs.getString("institutionname"));
            incidence.setUsername(rs.getString("username"));
            incidence.setPhonenumber(rs.getString("phonenumber"));
            incidence.setFirsttimelogin(rs.getInt("firsttimelogin"));
            return incidence;
        }
    }
        
        
        
          @Override
        public int updateUser(int id, String institutionname, String phonenumber,int firsttimelogin,String username){
        String sql = "UPDATE tbl_users set phonenumber = ?, institutionname = ?, username = ?, firsttimelogin = ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, new Object[]{phonenumber,institutionname,username,firsttimelogin,id});
              System.out.println("UPDATE " + update);
            System.out.println("UPDATE SUCCESSFULLY TO TBL_USERS");
            return update;
        }
        
        
        
        
        @Override
        public List<UserCreate> getAllUsers(){
        String sql = " select id,username,institutionname,phonenumber,firsttimelogin from tbl_users";
        List<UserCreate> getusers = jdbcTemplate.query(sql, new EditUserTypeMapper());
        System.out.println("ALL USERS SELECTED");
        return getusers;
        }
    
        
        
         @Override
        public List<UserCreate> getAllUsersLimit(String limit){
        String sql = " select id,username,institutionname,phonenumber,firsttimelogin from tbl_users " + limit;
        List<UserCreate> getusers = jdbcTemplate.query(sql, new EditUserTypeMapper());
        System.out.println("ALL USERS SELECTED");
        return getusers;
        }
     
     
     
    
       class UserTypeMapper implements RowMapper<UserType> {
        public UserType mapRow(ResultSet rs, int arg1) throws SQLException {
           UserType incidence = new UserType();
             incidence.setId(rs.getInt("id"));
             incidence.setUsertypename(rs.getString("usertypename"));
            return incidence;
        }
    }
    
    
     
     @Override
     public List<UserType> userType(){
     String sql = "select id,usertypename from tbl_usertypes";
     List<UserType> getUserTypeNames = jdbcTemplate.query(sql, new UserTypeMapper());
     return getUserTypeNames;
     }
    
    
   
    
    
         @Override
     public int[] deleteUserInstitution(String array[]){
     String sql = "DELETE FROM tbl_company WHERE id =";
     
        String query[] = new String[array.length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {

            sb.append(sql).append("");
            sb.append("'").append(array[i]).append("'");
            query[i] = sb.toString();
            System.out.println("sqllll:" + sb.toString());
            sb.setLength(0);
        }
      int[] result = jdbcTemplate.batchUpdate(query);
        System.out.println("");

        System.out.println("deleted:" + result.length);

        return result;
     }
    
    
    
    
    
    @Override
    public int createProducts(String productname,String productdescription){
    String sql = "INSERT INTO tbl_products (productname,productdescription,datecreated) VALUES (?,?,now())";
    int product = jdbcTemplate.update(sql, new Object[]{productname,productdescription});
    return product;
    }
    
    
    
    
    @Override
    public String checkFirstLogin(String username) {
        String sql = "SELECT firsttimelogin FROM tbl_users WHERE username = ?";
        Object[] inputs = new Object[]{username};
        String check;
        System.out.println("1. >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> user: " + username);

        try {
            check = jdbcTemplate.queryForObject(sql, inputs, String.class);
        } catch (Exception ex) {
            System.out.println("error:" + ex.getMessage());
            check = "-1";
        }

        System.out.println("2. >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> check: " + check);

        return check;
    }
    
    
    @Override
    public int userMapping(String username,int usertypeid){
    String sql = "insert into tbl_usertype_mapping(username,usertypeid,datecreation) values (?,?,now())";
    int usermap = jdbcTemplate.update(sql, new Object[]{username,usertypeid});
    return usermap;
    }
    
    
     
    @Override
    public int userMappingForAdminCreation(String username,String usertype){
    String sql = "insert into tbl_usertype_mapping(username,usertypeid,datecreation) values (?,?,now())";
    int usermap = jdbcTemplate.update(sql, new Object[]{username,usertype});
    return usermap;
    }
    
    
    
    @Override
    public int userRoles(String role,String username){
    
    String sql = "insert into tbl_user_roles (username,role) values (?,?)";
    
    int userrole = jdbcTemplate.update(sql, new Object[]{role,username});
        return userrole;
    
    }
    
    
    
    @Override
    public String userType(String usertypename){
    String sql = "select id from tbl_usertypes where usertypename = ?";
    String user = (String) jdbcTemplate.queryForObject(
        sql, new Object[] {usertypename}, String.class);
        System.out.println("Usertype selected");
        return user;
    }
    
    
    
    
    @Override
    public String userAssign(String username){
    String sql = "select usertypeid from tbl_usertype_mapping where username = ?";
    String userid =(String) jdbcTemplate.queryForObject(
        sql, new Object[] {username}, String.class);
        System.out.println("Usertype selected");
        return userid;
    }
    
    
    
//    public int updateUser(){
//    String sql = "update tbl_usertype_mapping set usertypeid = ?"
//    
//    }
    
    
    
     @Override
    public boolean insertEmail (String username, String subject, String emailBody, String toEmailAddress) {
        boolean isInserted = false;

        try {
            String sql = "INSERT INTO tbl_email_alerts (subject, emailbody, toemailaddress, maildate, issent, username)"
                    + " VALUES (?,?,?,now(), 0, ?)";

            if (emailBody.length() > 500)
                emailBody.substring(0, 500);

            Object[] transObj = {subject, emailBody, toEmailAddress, username};

            int retVal = jdbcTemplate.update(sql, transObj);

            System.out.println("retVal: " + retVal);

            if (retVal > 0)
                isInserted = true;

        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return isInserted;
    }
    
    @Override
    public String checkToken(String username){
    String sql = "select emailbody from tbl_email_alerts where username = ?";
        System.out.println("sql!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + sql);
    String token =(String) jdbcTemplate.queryForObject(
        sql, new Object[] {username}, String.class);
        System.out.println("token ################################ " + token);
        System.out.println("token selected");
        return token;
   }
    
    
    
    
    
    @Override
    public int updateFirstLogin(String username){
//        boolean isChanged = false;
    String sql = "update tbl_users set firsttimelogin = 1 where username = ?";
    int loginUpdate = jdbcTemplate.update(sql, new Object[]{username});
    return loginUpdate;
    
    }
    
    
    
    @Override
    public boolean registerUser(String firstname,String lastname,String phonenumber,String institutionname, String username, String password,int enabled,int firsttimelogin){
    boolean isChanged = false;
        
    String sql;
    sql = "INSERT INTO tbl_users (firstname,lastname,phonenumber,institutionname,username,password,enabled,firsttimelogin,datecreation) VALUES (?,?,?,?,?,?,?,?,now())";
    String nEncryptedPassword = passwordEncoder.encode(password);
    
    int retval = jdbcTemplate.update(sql, new Object[]{firstname,lastname,phonenumber,institutionname,username,nEncryptedPassword,enabled,firsttimelogin});
    System.out.println("I GOT HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    isChanged = true;
    
    return isChanged;
    }
    
    
    
    
    
    
    @Override
    public String User(String username){
    String sql = "select username from tbl_users where username = ?";
    String user = (String) jdbcTemplate.queryForObject(
        sql, new Object[] {username}, String.class);
        System.out.println("DATABASES IS WORKING");
    
    return user;
    }
    
       @Override
    public List<incidence> getIntitutionName() {
        String sql = "SELECT id,institutionname FROM tbl_partnerinstitutions";
        List<incidence> incidence = jdbcTemplate.query(sql, new AllIncidenceMapper());
        return incidence;
    }
   
        @Override
    public String getIntitutionNameTwo(String username) {
        String sql = "SELECT institutionname FROM tbl_users where username = ?";
        String incidence = (String)jdbcTemplate.queryForObject(
        sql, new Object[] {username}, String.class);
        return incidence;
    }
  
    
       
    @Override
    public long logIncidence(incidence incidence,String trackingnumber) {
        long inc = 1;
        
        
        String financialinstitutioncode = "010";
        System.out.println("trackingnumber ========================== " + trackingnumber);
        String sql = "INSERT INTO tbl_incidences (sent_by,title,message_body,institutionname,financialinstitutioncode,trackingnumber,datelogged,products) VALUES (?,?,?,?,?,?,now(),?)";
        System.out.println("incidence.getSender(): " + incidence.getSender());
        System.out.println("incidence.getType_id(): "+ incidence.getType_id());     
      
        
        inc = jdbcTemplate.update(sql, incidence.getSender(),incidence.getTitle(),incidence.getMessageBody(),incidence.getInstitutionname(),financialinstitutioncode,trackingnumber,incidence.getProducts());
        return inc;
    }
    
    
    
    
    
//    @Override
//    public int logNewIncidence(int trackingnumber,String messagebody,String products,String title){
//        String sql = "INSERT INTO tbl_trackincidence (trackingnumber,products,title,messagebody) VALUE (?,?,?,?)";
//        int tracker = jdbcTemplate.update(sql, new Object[]{trackingnumber,messagebody,products,title});
//        return tracker;
//    }
    
    @Override
    public int InsertInstitutionName(String institutionname,String sent_by){
    String sql = "update tbl_incidences set institutionname = ? where sent_by = ?";
    int updateusername = jdbcTemplate.update(sql, new Object[]{institutionname,sent_by});
    return updateusername;
    }
    
    
    
    @Override
    public List<incidence> getLoggedIncidenceForFamiliarInstitute(String institutionname){
    String sql = "select id,trackingnumber,title,status,datelogged,filename from tbl_incidences where institutionname = ? ORDER BY datelogged DESC";
    
      final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, institutionname);
            return ps;
        };
    
     return jdbcTemplate.query(psc, (ResultSet rs, int rownumber) -> {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
          incidence.setTitle(rs.getString("title"));
          incidence.setDatelogged(rs.getString("datelogged"));
          incidence.setTrackingnumber(rs.getLong("trackingnumber"));
          incidence.setStatus(rs.getInt("status"));
          incidence.setFilename(rs.getString("filename"));
            return incidence;
        });
    }
    
    
    
    
        @Override
    public List<incidence> getLoggedIncidenceForFamiliarInstituteForFileInsert(String institutionname){
    String sql = "select id,trackingnumber,title,status,datelogged,filename from tbl_incidences where institutionname = ? ORDER BY datelogged DESC";
    
      final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, institutionname);
            return ps;
        };
    
     return jdbcTemplate.query(psc, (ResultSet rs, int rownumber) -> {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
          incidence.setTitle(rs.getString("title"));
          incidence.setDatelogged(rs.getString("datelogged"));
          incidence.setTrackingnumber(rs.getLong("trackingnumber"));
          incidence.setStatus(rs.getInt("status"));
          incidence.setFilename(rs.getString("filename"));
            return incidence;
        });
    }
    
    
    
    @Override
    public List<incidence> getLoggedIncidenceForFamiliarInstituteLimit(String institutionname,String limit){
    String sql = "select id,trackingnumber,title,status,datelogged,filename from tbl_incidences where institutionname = ? ORDER BY datelogged DESC " + limit;
    
      final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, institutionname);
            return ps;
        };
    
    
      return jdbcTemplate.query(psc, (ResultSet rs, int rownumber) -> {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
          incidence.setTitle(rs.getString("title"));
          incidence.setDatelogged(rs.getString("datelogged"));
          incidence.setTrackingnumber(rs.getLong("trackingnumber"));
          incidence.setStatus(rs.getInt("status"));
           incidence.setFilename(rs.getString("filename"));
            return incidence;
        });
    }
    
    
    
    
    
     @Override
    public List<incidence> getLoggedIncidenceForFamiliarInstituteForFileInsertLimit(String institutionname,String limit){
    String sql = "select id,trackingnumber,title,status,datelogged,filename from tbl_incidences where institutionname = ? ORDER BY datelogged DESC " + limit;
    
      final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, institutionname);
            return ps;
        };
    
    
      return jdbcTemplate.query(psc, (ResultSet rs, int rownumber) -> {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
          incidence.setTitle(rs.getString("title"));
          incidence.setDatelogged(rs.getString("datelogged"));
          incidence.setTrackingnumber(rs.getLong("trackingnumber"));
          incidence.setStatus(rs.getInt("status"));
           incidence.setFilename(rs.getString("filename"));
            return incidence;
        });
    }
    
    
        @Override
        public List<User> getAllInstitutions() {
        String sql = "SELECT id,institutionname,products from tbl_company order by datecreation desc";
        List<User> userAccounts = jdbcTemplate.query(sql, new userInstitutionMapper());
        return userAccounts;
    }
    
        @Override
        public int updateInstitution(String institutionname, String products, int id){
        String sql = "update tbl_company set institutionname = ? , products = ? where id = ?";
        int update = jdbcTemplate.update(sql, new Object[]{institutionname,products,id});
            System.out.println("UPDATE SUCCESSFULLY TO TBL_COMPANY");
            return update;
        }
        
        
        
        @Override
        public int updateProduct(String productname, String productdescription, int id){
        String sql = "update tbl_products set productname = ? , productdescription = ? where id = ?";
        int update = jdbcTemplate.update(sql, new Object[]{productname,productdescription,id});
            System.out.println("UPDATE SUCCESSFULLY TO TBL_PRODUCTS");
            return update;
        }
        
        
        
        
    
      @Override
        public List<Product> getAllProducts() {
        String sql = "SELECT id,productname,productdescription from tbl_products order by datecreated desc";
        List<Product> userProducts = jdbcTemplate.query(sql, new productMapper());
        return userProducts;
    }
    
    
    
    class userInstitutionMapper implements RowMapper<User>{
    public User mapRow(ResultSet rs, int arg1) throws SQLException{
      User user = new User();
      user.setId(rs.getInt("id"));
      user.setInstitutionname(rs.getString("institutionname"));
      user.setProducts(rs.getString("products"));
     
      return user;
    }
    
    }
    
    
    
    
     
    class productMapper implements RowMapper<Product>{
    public Product mapRow(ResultSet rs, int arg1) throws SQLException{
      Product user = new Product();
      user.setId(rs.getInt("id"));
      user.setProductname(rs.getString("productname"));
      user.setProductdescription(rs.getString("productdescription"));
     
      return user;
    }
    
    }

    
    class incidenceTypeMapper implements RowMapper<incidence> {
        public incidence mapRow(ResultSet rs, int arg1) throws SQLException {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
            incidence.setType(rs.getString("incidence_type"));
//            incidence.setFinancialinstitutionname(rs.getString("financialinstitutionname"));
            return incidence;
        }
    }
    
    
     
    @Override
    public List<incidence> getIncidenceType() {
        String sql = "SELECT id,incidence_type FROM tbl_incidencetypes";
        List<incidence> incidence = jdbcTemplate.query(sql, new incidenceTypeMapper());
        return incidence;
    }
    
    
     @Override
    public String getFinancialInstitutionName (String username){
//        String sql = "Select financialinstitutioncode from tbl_customer_accounts where username=?";
          String sql = "SELECT tbl_partnerinstitutions.institutionname\n" +
                        "FROM tbl_customer_accounts,tbl_partnerinstitutions\n" +
                        "WHERE tbl_customer_accounts.financialinstitutioncode = tbl_partnerinstitutions.institutioncode AND tbl_customer_accounts.username=? LIMIT 1";
        Object[] inputs = new Object[]{username};
        String check;
        System.out.println("user:"+username);
      
        try{
            check = jdbcTemplate.queryForObject(sql, inputs, String.class);
        }catch (DataAccessException ex){
            System.out.println("error:"+ex.getMessage());
            check = "-1";
        }
        return check;
    }
    
    
    
    class AllIncidenceMapper implements RowMapper<incidence> {
        public incidence mapRow(ResultSet rs, int arg1) throws SQLException {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
            incidence.setSender(rs.getString("sent_by"));
            incidence.setType(rs.getString("incidence_type"));
            incidence.setTitle(rs.getString("title"));
            incidence.setMessageBody(rs.getString("message_body"));
            incidence.setStatus(rs.getInt("status"));
            incidence.setDatelogged(rs.getString("datelogged"));
            incidence.setDate_closed(rs.getString("date_closed"));
           incidence.setFinancialinstitutioncode(rs.getString("financialinstitutioncode"));
            incidence.setInstitutionname(rs.getString("institutionname"));
            incidence.setClosed_by(rs.getString("closed_by"));
            incidence.setTrackingnumber(rs.getLong("trackingnumber"));
            incidence.setProducts(rs.getString("products"));
            return incidence;
        }
    }
    
       @Override
    public List<incidence> getAllIncidence() {
        String sql = "SELECT tbl_incidences.id,tbl_incidences.sent_by,tbl_incidences.financialinstitutioncode, tbl_incidences.title,"
                + "tbl_incidences.message_body,tbl_incidences.status,tbl_incidences.datelogged, tbl_incidences.institutionname,tbl_incidences.trackingnumber,"
                + "tbl_incidences.closed_by,tbl_incidencetypes.incidence_type,tbl_incidences.date_closed FROM tbl_incidences,tbl_incidencetypes "
                + "WHERE tbl_incidences.type = tbl_incidencetypes.id ORDER BY datelogged DESC";

        System.out.println("getAllIncidence :: sql: " + sql);

        List<incidence> Incidence = jdbcTemplate.query(sql, new AllIncidenceMapper());

        return Incidence;
    }
    
    
    
     
    class AllMapper implements RowMapper<incidence> {
        public incidence mapRow(ResultSet rs, int arg1) throws SQLException {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
//            incidence.setSender(rs.getString("sent_by"));
//            incidence.setType(rs.getString("incidence_type"));
            incidence.setTitle(rs.getString("title"));
//            incidence.setMessageBody(rs.getString("message_body"));
            incidence.setStatus(rs.getInt("status"));
            incidence.setDatelogged(rs.getString("datelogged"));
//            incidence.setDate_closed(rs.getString("date_closed"));
//           incidence.setFinancialinstitutioncode(rs.getString("financialinstitutioncode"));
//            incidence.setInstitutionname(rs.getString("institutionname"));
//            incidence.setClosed_by(rs.getString("closed_by"));
            incidence.setTrackingnumber(rs.getLong("trackingnumber"));
            incidence.setFilename(rs.getString("filename"));
            return incidence;
        }
    }
    
    
    
    
    
    
    

    
    
    
    @Override
    public List<incidence> getAllIncidenceInDatabase(){
    String sql = "select id,trackingnumber,title,status,datelogged,filename from tbl_incidences order by datelogged DESC";
    List<incidence> allvalues = jdbcTemplate.query(sql, new AllMapper());
    return allvalues;
    }
    
    
   
    
     @Override
    public List<incidence> getAllIncidenceInDatabaseLimit(String limit){
    String sql = "select id,trackingnumber,title,status,datelogged,filename from tbl_incidences order by datelogged DESC " + limit;
    List<incidence> allvalues = jdbcTemplate.query(sql, new AllMapper());
    return allvalues;
    }
    
    
    
    
    
    
    
    
    
    @Override
    public String getInstFromWallets (String username) {
        String sql = "SELECT financialinstitutioncode FROM tbl_customer_accounts WHERE username = ?";
        String retInst = "";

        final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            return ps;
        };

        List<incidence> approver = jdbcTemplate.query(psc, new BeanPropertyRowMapper(incidence.class));

        if (approver != null && approver.size() > 0) {
            retInst = approver.get(0).getFinancialinstitutioncode();
        }

        return retInst;
    }
    
       @Override
    public List<incidence> getAllIncidenceByLimit(String limit) {
//         String sql = "SELECT tbl_incidences.id,tbl_incidences.sent_by,tbl_incidences.financialinstitutioncode,\n" +
//                "tbl_incidences.title,tbl_incidences.message_body,tbl_incidences.status,tbl_incidences.datelogged,\n" +
//                "tbl_incidences.financialinstitutionname,tbl_incidences.closed_by,tbl_incidencetypes.incidence_type,tbl_incidences.date_closed,\n" +
//                "FROM tbl_incidences,tbl_incidencetypes WHERE tbl_incidences.type = tbl_incidencetypes.id ORDER BY datelogged DESC ORDER BY datelogged DESC " + limit;

        String sql = "SELECT tbl_incidences.id,tbl_incidences.sent_by,tbl_incidences.financialinstitutioncode, tbl_incidences.title,"
                + "tbl_incidences.message_body,tbl_incidences.status,tbl_incidences.datelogged, tbl_incidences.institutionname,tbl_incidences.trackingnumber,"
                + "tbl_incidences.closed_by,tbl_incidencetypes.incidence_type,tbl_incidences.date_closed FROM tbl_incidences,tbl_incidencetypes "
                + "WHERE tbl_incidences.type = tbl_incidencetypes.id ORDER BY datelogged DESC " + limit;

        System.out.println("sql:" + sql);
        List<incidence> incidence = jdbcTemplate.query(sql, new AllIncidenceMapper());
        return incidence;
    }
    
    
    @Override
    public List<incidence> getIncidence (String financialinstitutioncode) {
        String sql = "SELECT tbl_incidences.id,tbl_incidences.sent_by,tbl_incidences.financialinstitutioncode,"
                + "tbl_incidences.title,tbl_incidences.message_body,tbl_incidences.status,tbl_incidences.datelogged,"
                + "tbl_incidences.institutionname,tbl_incidences.trackingnumber,tbl_incidences.closed_by,tbl_incidences.date_closed,"
                + "tbl_incidencetypes.incidence_type\n" +
        "FROM tbl_incidences,tbl_incidencetypes WHERE tbl_incidences.type = tbl_incidencetypes.id AND \n" +
            "tbl_incidences.financialinstitutioncode=? ORDER BY datelogged DESC";
        final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, financialinstitutioncode);

            System.out.println("getIncidence :: sql: " + ps.toString());
            return ps;
        };

    return jdbcTemplate.query(psc, (ResultSet rs, int rownumber) -> {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
            incidence.setSender(rs.getString("sent_by"));
            incidence.setType(rs.getString("incidence_type"));
            incidence.setTitle(rs.getString("title"));
            incidence.setMessageBody(rs.getString("message_body"));
            incidence.setStatus(rs.getInt("status"));
            incidence.setDatelogged(rs.getString("datelogged"));
            incidence.setDate_closed(rs.getString("date_closed"));
            incidence.setFinancialinstitutioncode(rs.getString("financialinstitutioncode"));
            incidence.setInstitutionname(rs.getString("institutionname"));
            incidence.setClosed_by(rs.getString("closed_by"));
            return incidence;
        });
    }
    
    
     public List<incidence> getIncidenceByLimit(String financialinstitutioncode, String limit) {
          String sql = "SELECT tbl_incidences.id,tbl_incidences.sent_by,tbl_incidences.financialinstitutioncode,"
                  + "tbl_incidences.title,tbl_incidences.message_body,tbl_incidences.status,tbl_incidences.datelogged,"
                  + "tbl_incidences.institutionname,tbl_incidences.trackingnumber,tbl_incidences.closed_by,tbl_incidences.date_closed,"
                  + "tbl_incidencetypes.incidence_type\n" +
        "FROM tbl_incidences,tbl_incidencetypes WHERE tbl_incidences.type = tbl_incidencetypes.id AND \n" +
            "tbl_incidences.financialinstitutioncode=? ORDER BY datelogged DESC " + limit;
        System.out.println("sql:" + sql);
        final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, financialinstitutioncode);
            return ps;
        };

        return jdbcTemplate.query(psc, (ResultSet rs, int rownumber) -> {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
            incidence.setSender(rs.getString("sent_by"));
            incidence.setType(rs.getString("incidence_type"));
            incidence.setTitle(rs.getString("title"));
            incidence.setMessageBody(rs.getString("message_body"));
            incidence.setStatus(rs.getInt("status"));
            incidence.setDatelogged(rs.getString("datelogged"));
            incidence.setDate_closed(rs.getString("date_closed"));
            incidence.setFinancialinstitutioncode(rs.getString("financialinstitutioncode"));
            incidence.setInstitutionname(rs.getString("institutionname"));
            incidence.setClosed_by(rs.getString("closed_by"));
            return incidence;
        });

    }
     
     
         @Override
    public int closeIncidence(String id,String username) {
        String sql = "UPDATE tbl_incidences SET status = 0, closed_by = ?, date_closed=now() WHERE id = ? ";
//        final PreparedStatementCreator psc = (final Connection connection) -> {
//            final PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, username);
//            ps.setInt(2, id);
//
//            return ps;
//        };
        int status = jdbcTemplate.update(sql, new Object[]{username,id});
             System.out.println("id !!!!!============== " + id);
             System.out.println("status !!!!!!!============ " + status);
        return status;
    }
    
    
     @Override
    public List<incidence> getIncidencebyID (int id) {
        String sql = "SELECT id,sent_by,financialinstitutioncode,title,message_body,status,datelogged,institutionname,closed_by,date_closed\n" +
            "FROM tbl_incidences WHERE tbl_incidences.id=?";
        final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            return ps;
        };

    return jdbcTemplate.query(psc, (ResultSet rs, int rownumber) -> {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
            incidence.setSender(rs.getString("sent_by"));
            incidence.setTitle(rs.getString("title"));
            incidence.setMessageBody(rs.getString("message_body"));
            incidence.setStatus(rs.getInt("status"));
            incidence.setDatelogged(rs.getString("datelogged"));
            incidence.setFinancialinstitutioncode(rs.getString("financialinstitutioncode"));
            incidence.setClosed_by(rs.getString("closed_by"));
            return incidence;
        });
    }
    
    
       
    @Override
    public List<incidence> getResponsebyID (int id) {
        String sql = "SELECT tbl_incidence_responses.incidence_id,tbl_incidence_responses.response,tbl_incidence_responses.replied_by,tbl_incidence_responses.date_replied,tbl_incidence_responses.financialinstitutioncode \n" +
"    FROM tbl_incidences \n" +
"    LEFT JOIN tbl_incidence_responses ON tbl_incidences.id=tbl_incidence_responses.incidence_id \n" +
"    WHERE  tbl_incidence_responses.incidence_id=?";
        final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            return ps;
        };

    return jdbcTemplate.query(psc, (ResultSet rs, int rownumber) -> {
            incidence response = new incidence();
            response.setDate_replied(rs.getString("date_replied"));
            response.setResponse(rs.getString("response"));
            response.setReplied_by(rs.getString("replied_by"));
            response.setFinancialinstitutioncode(rs.getString("financialinstitutioncode"));
            return response;
        });
    }
    
     
    @Override
    public boolean incidenceReply (String id, String message,String username,String financialinstitutioncode) {
        boolean isInserted = false;
        try {
            String sql = "INSERT INTO tbl_incidence_responses (incidence_id,response,date_replied,replied_by,financialinstitutioncode) VALUES (?,?,now(),?,?)";

//            Object[] transObj = {id, message,username,financialinstitutioncode}; 

            int retVal = jdbcTemplate.update(sql, new Object[]{id, message,username,financialinstitutioncode});

            System.out.println("retVal: " + retVal);

            if (retVal > 0)
                isInserted = true;

        } catch (Exception ee) {
            ee.printStackTrace();
        }
        return isInserted;
    }

    
     
    class SearchMapper implements RowMapper<incidence> {
        public incidence mapRow(ResultSet rs, int arg1) throws SQLException {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
//            incidence.setSender(rs.getString("sent_by"));
//            incidence.setType(rs.getString("incidence_type"));
            incidence.setTitle(rs.getString("title"));
//            incidence.setMessageBody(rs.getString("message_body"));
            incidence.setStatus(rs.getInt("status"));
            incidence.setDatelogged(rs.getString("datelogged"));
//            incidence.setDate_closed(rs.getString("date_closed"));
//           incidence.setFinancialinstitutioncode(rs.getString("financialinstitutioncode"));
//            incidence.setInstitutionname(rs.getString("institutionname"));
//            incidence.setClosed_by(rs.getString("closed_by"));
            incidence.setTrackingnumber(rs.getLong("trackingnumber"));
            incidence.setFilename(rs.getString("filename"));
            return incidence;
        }
    }
    
      @Override 
    public List<UserCreate> searchUserDetails(String table_name, String search_by, String search_details) {
        String sql = "";
       sql=  "SELECT id,username,institutionname,phonenumber,firsttimelogin FROM " + table_name  + " WHERE " + search_by + " LIKE '%" + search_details + "%' ";
         
        System.out.println("sql:"+sql);

        List<UserCreate> searchUser = jdbcTemplate.query(sql, new EditUserTypeMapper());
      
          return searchUser;
    }
    
    
    
       @Override 
    public List<UserCreate> searchUserDetailsLimit(String table_name, String search_by, String search_details,String limit) {
        String sql = "";
       sql=  "SELECT id,username,institutionname,phonenumber,firsttimelogin FROM " + table_name  + " WHERE " + search_by + " LIKE '%" + search_details + "%' " + limit;
         
        System.out.println("sql:"+sql);

        List<UserCreate> searchUsers = jdbcTemplate.query(sql, new EditUserTypeMapper());
      
          return searchUsers;
    }
    
    
    
    
    
    
    
    
    
   @Override 
    public List<incidence> searchIncidence(String table_name, String search_by, String search_details) {
        String sql = "";
       sql=  "SELECT id,trackingnumber,title,datelogged,status FROM " + table_name  + " WHERE " + search_by + " LIKE '%" + search_details + "%' ";
         
        System.out.println("sql:"+sql);

        List<incidence> searchIncidence = jdbcTemplate.query(sql, new SearchMapper());
      
          return searchIncidence;
    }

    @Override 
    public List<incidence> searchIncidenceLimit(String table_name, String search_by, String search_details,String limit) {
        String sql = "";
       sql=  "SELECT id,trackingnumber,title,datelogged,status FROM " + table_name  + " WHERE " + search_by + " LIKE '%" + search_details + "%' " + limit;
         
        System.out.println("sql:"+sql);

        List<incidence> searchIncidence = jdbcTemplate.query(sql, new SearchMapper());
      
          return searchIncidence;
    }
    
    
    
    
     @Override 
    public List<incidence> searchIncidenceByUser(String table_name, String search_by, String search_details,String institutionname) {
        String sql = "";
       sql=  "SELECT id,trackingnumber,title,datelogged,status,filename FROM " + table_name  + " WHERE " + search_by + " LIKE '%" + search_details + "%' and institutionname = ?";
         
        System.out.println("sql:"+sql);
      

        List<incidence> searchIncidence = jdbcTemplate.query(sql, new Object[]{institutionname}, new SearchMapper());
      
          return searchIncidence;
    }
    
    
      @Override 
    public List<incidence> searchIncidenceLimitByUser(String table_name, String search_by, String search_details,String institutionname,String limit) {
        String sql = "";
       sql=  "SELECT id,trackingnumber,title,datelogged,status,filename FROM " + table_name  + " WHERE " + search_by + " LIKE '%" + search_details + "%' and institutionname = ? " + limit;
         
        System.out.println("sql:"+sql);

        List<incidence> searchIncidence = jdbcTemplate.query(sql, new Object[]{institutionname}, new SearchMapper());
      
          return searchIncidence;
    }
    
    
    
    
    
      @Override
    public String getInstCode (String username) {
        String sql = "SELECT financialinstitutioncode FROM tbl_customer_accounts WHERE username = ?";
        String retInst = "";

        final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            return ps;
        };

        List<incidence> approver = jdbcTemplate.query(psc, new BeanPropertyRowMapper(incidence.class));

        if (approver != null && approver.size() > 0) {
            retInst = approver.get(0).getFinancialinstitutioncode();
        }

        return retInst;
    }
    
    
    
         @Override
    public List<incidence> getAllInstituteIncidence(String institutionname) {
        String sql = "SELECT id,sent_by,financialinstitutioncode,title,"
                + "message_body,status,datelogged,institutionname,trackingnumber,"
                + "closed_by,incidence_type,date_closed FROM tbl_incidences,"
                + "WHERE institutionname = ? ORDER BY datelogged DESC";

        System.out.println("getAllIncidence :: sql: " + sql);

         final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, institutionname);

            System.out.println("getIncidence :: sql: " + ps.toString());
            return ps;
        };

    return jdbcTemplate.query(psc, (ResultSet rs, int rownumber) -> {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
            incidence.setSender(rs.getString("sent_by"));
            incidence.setType(rs.getString("incidence_type"));
            incidence.setTitle(rs.getString("title"));
            incidence.setMessageBody(rs.getString("message_body"));
            incidence.setStatus(rs.getInt("status"));
            incidence.setDatelogged(rs.getString("datelogged"));
            incidence.setDate_closed(rs.getString("date_closed"));
            incidence.setFinancialinstitutioncode(rs.getString("financialinstitutioncode"));
            incidence.setInstitutionname(rs.getString("institutionname"));
            incidence.setClosed_by(rs.getString("closed_by"));
            return incidence;
        });
    }
    
    
            @Override
    public List<incidence> getAllInstituteIncidenceLimit(String institutionname , String limit) {
        String sql = "SELECT id,sent_by,financialinstitutioncode,title,"
                + "message_body,status,datelogged,institutionname,trackingnumber,"
                + "closed_by,incidence_type,date_closed FROM tbl_incidences,"
                + "WHERE institutionname = ? ORDER BY datelogged DESC " + limit;

        System.out.println("getAllIncidence :: sql: " + sql);
  final PreparedStatementCreator psc = (final Connection connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, institutionname);
            return ps;
        };

        return jdbcTemplate.query(psc, (ResultSet rs, int rownumber) -> {
            incidence incidence = new incidence();
            incidence.setId(rs.getInt("id"));
            incidence.setSender(rs.getString("sent_by"));
            incidence.setType(rs.getString("incidence_type"));
            incidence.setTitle(rs.getString("title"));
            incidence.setMessageBody(rs.getString("message_body"));
            incidence.setStatus(rs.getInt("status"));
            incidence.setDatelogged(rs.getString("datelogged"));
            incidence.setDate_closed(rs.getString("date_closed"));
            incidence.setFinancialinstitutioncode(rs.getString("financialinstitutioncode"));
            incidence.setInstitutionname(rs.getString("institutionname"));
            incidence.setClosed_by(rs.getString("closed_by"));
            return incidence;
        });

    }
    
    
    @Override
    public boolean CreateInstitution(String institutionname,String products){
    String sql = "insert into tbl_company (institutionname,products,datecreation) values (?,?,now())";
    boolean isChanged = false;
    int insertcompanyname = jdbcTemplate.update(sql, new Object[]{institutionname,products});
    if (insertcompanyname > 0){
    isChanged = true;
        System.out.println("INSTITUTION ADDED SUCCESSFULLY");
    }
    return isChanged;
    }
    
    
    
    
     class FileNameMapper implements RowMapper<Filenames> {

        NumberFormat numberFormatter = new DecimalFormat("###,###.00");

        public Filenames mapRow(ResultSet rs, int arg1) throws SQLException {
            Filenames filenames = new Filenames();
            int appStatusInt = Integer.parseInt(rs.getString("approval_status"));

            filenames.setApproval_status(appStatusInt);
            filenames.setApproved_by(rs.getString("approved_by"));
            filenames.setDate_uploaded(rs.getString("date_uploaded"));
            filenames.setFile_name(rs.getString("file_name"));
            filenames.setId(Integer.parseInt(rs.getString("id")));
            filenames.setLocation(rs.getString("location"));
            filenames.setUploaded_by(rs.getString("uploaded_by"));
            filenames.setTotalAmount("N" + numberFormatter.format(Double.parseDouble(rs.getString("totalamount"))));

            if (appStatusInt == 1) {
                filenames.setFileStatus("Approved");
            } else if (appStatusInt == -1) {
                filenames.setFileStatus("Rejected");
            } else if (appStatusInt > 1) {
                filenames.setFileStatus("Pending Approval");
            }

            return filenames;
        }
    }

    
       
   
     
     
//    class UserMapper implements RowMapper<User> {
//        public User mapRow(ResultSet rs, int arg1) throws SQLException {
//           User user = new User();
//            user.setUsername(rs.getString("username"));
////            incidence.setSender(rs.getString("sent_by"));
////            incidence.setType(rs.getString("incidence_type"));
////            incidence.setTitle(rs.getString("title"));
////            incidence.setMessageBody(rs.getString("message_body"));
////            incidence.setStatus(rs.getInt("status"));
////            incidence.setDatelogged(rs.getString("datelogged"));
////            incidence.setDate_closed(rs.getString("date_closed"));
////           incidence.setFinancialinstitutioncode(rs.getString("financialinstitutioncode"));
////            incidence.setInstitutionname(rs.getString("institutionname"));
////            incidence.setClosed_by(rs.getString("closed_by"));
////            incidence.setTrackingnumber(rs.getLong("trackingnumber"));
//            return user;
//        }
//    }
//    
//    
    
     
     @Override
     public int InsertFile(String filename,String filepath,int id){
     String sql = "update tbl_incidences set filename = ? , filepath = ? where id = ?";
     int fileupload = jdbcTemplate.update(sql, new Object[]{filename,filepath,id});
     return fileupload;
     }

     @Override
     public String GetFile(String trackingnumber){
     String sql = "select filename,filepath from tbl_files where trackingnumber = ?";
     String getfile = (String)jdbcTemplate.queryForObject(
        sql, new Object[] {trackingnumber}, String.class);
         System.out.println("WE GOT HERE TO TEST");
     return getfile;
  }
     
     
     
     
     @Override
     public String SelectFilepath(String filename){
     String sql = "select filepath from tbl_incidences where filename = ?";
     String file = (String)jdbcTemplate.queryForObject(
        sql, new Object[] {filename}, String.class);
         System.out.println("FILEPATH FROM DATABASE = " + file);
     return file;
     }
     
     @Override
     public String SelectFilename(int id){
     String sql = "select filename from tbl_incidences where id = ?";
     String filename = (String)jdbcTemplate.queryForObject(
        sql, new Object[] {id}, String.class);
         System.out.println("FILEName FROM DATABASE = " + filename);
         return filename;
     }
     
//     
//     public String GetId(String trackingnumber){
//     String sql = "select id from tbl_incidences where trackingnumber = ?";
//     String getIdfromDb = (String)jdbcTemplate.queryForObject(
//        sql, new Object[] {trackingnumber}, String.class);
//         System.out.println("DATABASE GOTTEN!!!!!!!!!!!!!!!!!!!!!!");
//     return getIdfromDb;
//     }
}