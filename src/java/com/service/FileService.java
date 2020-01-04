/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Supersoft
 */
@Service
public class FileService {
    
    @Autowired
    JdbcTemplate jdbcTemplate;    
    
      public int insertFilename(String sql, String filename, String path, String username) {
        final String uu = username;
        final String pp = path;
        final String ff = filename;
        
//        System.out.println("airtime upload filename :: " + filename);
//        System.out.println("airtime upload path :: " + path);
//        System.out.println("airtime upload username :: " + username);
//        System.out.println("airtime upload sourceInstitutionCode :: " + sourceInstitutionCode);
//        System.out.println("airtime upload maxLevel :: " + maxLevel);

        KeyHolder keyHolder = new GeneratedKeyHolder();

//        System.out.println("airtime upload sql :: " + sql);

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID"});
            ps.setString(1, ff);
            ps.setString(2, pp);
            ps.setString(3, uu);
//            ps.setString(4, sourceInstitutionCode);
//            ps.setInt(5, maxLevel);
            
//            System.out.println("airtime upload ps.toString() :: " + ps.toString());

            return ps;
            }, keyHolder);

        Number key = keyHolder.getKey();

        long me = key.longValue();
        
//        System.out.println("airtime upload me :: " + me);

        return (int) me;
    }
      
      
      
      
      
      
      
      
      
      
}
