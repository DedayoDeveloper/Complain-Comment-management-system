/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

//import com.model.ProductWallets;
//import com.model.Transactions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.text.NumberFormat;
import org.springframework.jdbc.core.RowMapper;
import java.util.HashMap;

/**
 *
 * @author Ayomide
 */
@Service
public class ReportService {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<Map<String, Object>> getRecords(String sql, String[] placeHolders) {
        List<Map<String, Object>> reports;
        reports = jdbcTemplate.queryForList(sql, (Object[]) placeHolders);
        if (reports != null) {
            ArrayList<String> getMapKeysToBeFormatted = checkForThisKeys(reports.get(0));

            if (getMapKeysToBeFormatted.size() > 0) {
                int myLength = getMapKeysToBeFormatted.size();

                for (int i = 0; i < myLength; i++) {
                    for (Map<String, Object> mapToBeFormatted : reports) {
                        String myKey = getMapKeysToBeFormatted.get(i);
                        String str = mapToBeFormatted.get(myKey).toString();
                        str = getFormattedValue(str);
                        mapToBeFormatted.put(myKey, str);
                    }
                }
            }
        }

        return reports;
    }
    
    public List<Map<String, Object>> getRecords(String sql, int noOfCols, String[] placeHolders) {
        List<Map<String, Object>> reports = null;

        try {
            reports = jdbcTemplate.queryForList(sql, (Object[]) placeHolders);
        } catch (DataAccessException ee) {
            System.out.println("Exception on insertion:" + ee.getMessage());

        }

        return reports;
    }

    public List<Map<String, Object>> getRecordsForAll(String sql) {
        List<Map<String, Object>> reports;
        reports = jdbcTemplate.queryForList(sql);

        if (reports != null) {
            ArrayList<String> getMapKeysToBeFormatted = checkForThisKeys(reports.get(0));

            if (getMapKeysToBeFormatted.size() > 0) {
                int myLength = getMapKeysToBeFormatted.size();

                for (int i = 0; i < myLength; i++) {
                    for (Map<String, Object> mapToBeFormatted : reports) {
                        String myKey = getMapKeysToBeFormatted.get(i);
                        String str = mapToBeFormatted.get(myKey).toString();
                        str = getFormattedValue(str);
                        mapToBeFormatted.put(myKey, str);
                    }
                }
            }
        }

        return reports;
    }

    public List<String> getBankcodes() {
        List<String> bankCode;

        String sql = "SELECT bankcode FROM tbl_financialinstitutions";

        bankCode = jdbcTemplate.query(sql, (ResultSet resultSet, int i) -> resultSet.getString("bankcode"));
        return bankCode;
    }

//    public List<Transactions> getWalletTransactionRecordsw(String sql, String walletnumber, String[] placeHolders) {
//
//        List<Transactions> getRecords = null;
//
//        try {
//            getRecords = jdbcTemplate.query(sql, placeHolders, new ReportService.TransactionsRowMapper(walletnumber));
//
//        } catch (DataAccessException ex) {
//            System.out.println("Error:" + ex.getMessage());
//        }
//        return getRecords;
//    }

//    public List<Transactions> getTransactionsByParameters(String sql, String walletnumber, String[] placeHolders, String limit) {
//        List<Transactions> getRecords = null;
//        try {
//            if ("".equals(limit)) {
//                getRecords = jdbcTemplate.query(sql, placeHolders, new ReportService.TransactionsRowMapper(walletnumber));
//            } else {
//                sql = sql + " " + limit;
//                getRecords = jdbcTemplate.query(sql, placeHolders, new ReportService.TransactionsRowMapper(walletnumber));
//            }
//
//        } catch (DataAccessException ex) {
//            System.out.println("Error:" + ex.getMessage());
//        }
//        return getRecords;
//    }

//    public List<Transactions> getAllTransactionsByParameters(String sql, String limit) {
//        List<Transactions> getRecords = null;
//        try {
//            if ("".equals(limit)) {
//                getRecords = jdbcTemplate.query(sql, new ReportService.AllTransactionsRowMapper());
//            } else {
//                sql = sql + " " + limit;
//                getRecords = jdbcTemplate.query(sql, new ReportService.AllTransactionsRowMapper());
//            }
//        } catch (DataAccessException ex) {
//            System.out.println("Error:" + ex.getMessage());
//        }
//        return getRecords;
//    }
    
//    class TransactionsRowMapper implements RowMapper {
//        String walletNumber;
//         public TransactionsRowMapper (String walletNumber) {
//            this.walletNumber = walletNumber;
//        }
//        @Override
//        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Transactions transactions = new Transactions();
//                transactions.setSessionID(rs.getString("Id"));
//                transactions.setNarration(rs.getString("narration"));
//                transactions.setDestAccountName(rs.getString("Beneficiary"));
//                transactions.setTransactionDate(rs.getString("Date"));
//                 if (rs.getString("srcAccountNumber").equals(walletNumber))
//                    transactions.setDebit(getFormattedValue(rs.getString("debit")));
//                if (rs.getString("destAccountNumber").equals(walletNumber))
//                    transactions.setCredit(getFormattedValue(rs.getString("credit")));
//
//            return transactions;
//        }
//    }

//    class AllTransactionsRowMapper implements RowMapper {
//        @Override
//        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Transactions transactions = new Transactions();
//                transactions.setSessionID(rs.getString("Id"));
//                transactions.setSrcAccountName(rs.getString("Source"));
//                transactions.setDestAccountName(rs.getString("Destination"));
//                transactions.setAmount(getFormattedValue(rs.getString("Amount")));
//                transactions.setNarration(rs.getString("narration"));
//                transactions.setTransactionDate(rs.getString("Date"));
//
//            return transactions;
//        }
//    }

    public String getFormattedValue(String value) {
        // Get number and currency formatter
        NumberFormat nf = NumberFormat.getInstance();
        double thisValue = Double.parseDouble(value);
       
        String doubleValue = nf.format(thisValue);
        
        return doubleValue;
    }
    
    public ArrayList<String> checkForThisKeys(Map <String, Object> mapToBeChecked){
         ArrayList <String> getMapKeysToBeFormatted = new ArrayList();
         
         // Get the keys in the Map that should be formatted as a number
         for(String str: mapToBeChecked.keySet()){
             if(str.equalsIgnoreCase("Amount") || str.equalsIgnoreCase("Credit") || str.equalsIgnoreCase("Debit")){
                 getMapKeysToBeFormatted.add(str);
                 System.out.println("mmstr:"+str);
             }
         }
         return getMapKeysToBeFormatted;
    }
    
    public int checkToken(String username, String token) {

        String sql = "SELECT COUNT(*) FROM tbl_user_profile WHERE username = ? AND token = ? ";
        
        String placeHolders [] = {username, token};
//        final PreparedStatementCreator psc = (final Connection connection) -> {
//            final PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, username);
//            ps.setString(2, token);
//
//            return ps;
//        };

        int status = this.jdbcTemplate.queryForObject(sql, placeHolders, Integer.class );

        return status;
    }
//    
//    public List<Transactions> getTransactionsByParametersForFirstFive(String sql, String walletnumber, String placeholders[]) {
//        List<Transactions> getRecords = null;
//
//        try {
//            getRecords = jdbcTemplate.query(sql, placeholders, new ReportService.TransactionsRowMapper(walletnumber));
//        } catch (DataAccessException ex) {
//            System.out.println("Error:" + ex.getMessage());
//        }
//
//        return getRecords;
//    }

//    public List<Transactions> getAllTransactionsByParameters(String sql, String placeholders[], String limit) {
//        List<Transactions> getRecords = null;
//        try {
//            if("".equals(limit)){
//                getRecords = jdbcTemplate.query(sql, placeholders, new ReportService.AllTransactionsRowMapper());
//            }else{
//                sql +=  limit;
//                getRecords = jdbcTemplate.query(sql, placeholders, new ReportService.AllTransactionsRowMapper());
//            }
//        } catch (DataAccessException ex) {
//            System.out.println("Error:" + ex.getMessage());
//        }
//        return getRecords;
//    }

//    public List<Transactions> getTransactionsByParameters2(String sql, String walletnumber, String[] placeHolders, String limit) {
//        List<Transactions> getRecords = null;
//        try {
//            if("".equals(limit)){
//                getRecords = jdbcTemplate.query(sql, placeHolders, new ReportService.TransactionsRowMapper(walletnumber));
//                System.out.println("sqlll:"+sql);
//            }else{
//                sql = sql + " "+ limit;
//                System.out.println("theAppendedSql:"+sql);
//                getRecords = jdbcTemplate.query(sql, placeHolders, new ReportService.TransactionsRowMapper(walletnumber));
//            }
//
//        } catch (DataAccessException ex) {
//            System.out.println("Error:" + ex.getMessage());
//        }
//        return getRecords;
//    }

//    public List<Transactions> getAllTransactionsByParameters2(String sql, String placeholders[], String limit) {
//        List<Transactions> getRecords = null;
//
//        try {
//            if("".equals(limit)){
//                getRecords = jdbcTemplate.query(sql, placeholders, new ReportService.AllTransactionsRowMapper());
//            }else{
//                sql += " " + limit;
//                getRecords = jdbcTemplate.query(sql, placeholders, new ReportService.AllTransactionsRowMapper());
//            }
//        } catch (DataAccessException ex) {
//            System.out.println("Error:" + ex.getMessage());
//        }
//
//        return getRecords;
//    }
    
    public Map<String, Object> getTransactionsBySessionId(String sessionId) {
        Map<String, Object> getRecords = new HashMap();
        String sql = "SELECT id,srcSessionid,srcAccountNumber,srcAccountName,srcKycLevel,srcBvn,srcAmount,srcInstitutioncode,destSessionId,"
                + "srcResponsecode,destAccountNumber,destAccountName,"
                + "destKycLevel,destBvn,destAmount,destInstitutioncode,"
                + "destResponseCode,narration,transactiondate,username FROM tbl_combinedtransactions WHERE srcSessionid = ? LIMIT 1";

        try {
            getRecords = jdbcTemplate.queryForMap(sql, new Object[]{sessionId});
        } catch (DataAccessException ex) {
            System.out.println("Error Occurred...");
            ex.printStackTrace();
        }
        return getRecords;
    }

//    public List<ProductWallets> getProductByParameters(String sql, String productName, String[] placeHolders, String limit) {
//        List<ProductWallets> getRecords = null;
//        try {
//            if ("".equals(limit)) {
//                getRecords = jdbcTemplate.query(sql, placeHolders, new ReportService.productRowMapper(productName));
//            } else {
//                sql = sql + " " + limit;
//                getRecords = jdbcTemplate.query(sql, placeHolders, new ReportService.productRowMapper(productName));
//            }
//
//        } catch (DataAccessException ex) {
//            System.out.println("Error:" + ex.getMessage());
//        }
//        return getRecords;
//    }

//    class productRowMapper implements RowMapper {
//        String productName;
//
//        public productRowMapper(String productName) {
//            this.productName = productName;
//        }

//        @Override
//        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//            ProductWallets products = new ProductWallets();
//            products.setAmount(getFormattedValue(rs.getString("srcAmount")));
//            products.setBeneficiary(rs.getString("destAccountName"));
//            products.setDate(rs.getString("transactiondate"));
//            products.setNarration(rs.getString("narration"));
//            products.setProductName(rs.getString("productname"));
//            products.setWalletName(rs.getString("srcAccountName"));
//            products.setWalletNumber(rs.getString("srcAccountNumber"));
//            return products;
//        }
//    }

//    public List<Transactions> getCreditTransaction(String sql, String status, String[] placeHolders, String limit) {
//        List<Transactions> getRecords = null;
//        try {
//            if ("".equals(limit)) {
//                getRecords = jdbcTemplate.query(sql, placeHolders, new ReportService.creditTransactionRowMapper(status));
//            } else {
//                sql = sql + " " + limit;
//                getRecords = jdbcTemplate.query(sql, placeHolders, new ReportService.creditTransactionRowMapper(status));
//            }
//
//        } catch (DataAccessException ex) {
//            System.out.println("Error:" + ex.getMessage());
//        }
//        return getRecords;
//    }
//
//    class creditTransactionRowMapper implements RowMapper {
//
//        String status;
//
//        public creditTransactionRowMapper(String status) {
//            this.status = status;
//        }
//
//        @Override
//        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Transactions transactions = new Transactions();
//            transactions.setId(rs.getInt("id"));
//            transactions.setFromBank(rs.getString("institutionname"));
//            transactions.setAccount_numbers(rs.getString("account_numbers"));
//            transactions.setAccount_name(rs.getString("account_name"));
//            transactions.setNarration(rs.getString("narrations"));
//            transactions.setAmount(rs.getString("amount"));
//            transactions.setSessionID(rs.getString("session_id"));
//            transactions.setResponsecode2ndLeg(rs.getString("responsecode"));
//            transactions.setCreationdate(rs.getString("transaction_date"));
//            return transactions;
//        }
//    }
}